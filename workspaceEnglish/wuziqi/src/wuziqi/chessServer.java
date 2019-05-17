package wuziqi;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class MessageServer extends Panel //implements ActionListener
{
 TextArea messageBoard=new TextArea("",22,50,TextArea.SCROLLBARS_VERTICAL_ONLY);
 Label statusLabel=new Label("��ǰ������:",Label.LEFT);
    Panel boardPanel=new Panel();
    Panel statusPanel=new Panel();

 MessageServer()
 {
  setSize(350,300);
  setBackground(Color.pink);
  setLayout(new BorderLayout());
  boardPanel.setLayout(new FlowLayout());
  boardPanel.setSize(210,210);
  statusPanel.setLayout(new BorderLayout());
  statusPanel.setSize(210,50);
  boardPanel.add(messageBoard);
     statusPanel.add(statusLabel,BorderLayout.WEST);
  add(boardPanel,BorderLayout.CENTER);
  add(statusPanel,BorderLayout.NORTH);
 }
}


class ServerThread extends Thread
{
 Socket clientSocket;
 Hashtable clientDataHash;
 Hashtable clientNameHash;
 Hashtable chessPeerHash;
 MessageServer server;

 boolean isClientClosed=false;

 ServerThread(Socket clientSocket,Hashtable clientDataHash,Hashtable 

clientNameHash,Hashtable chessPeerHash,MessageServer server)
 {
  this.clientSocket=clientSocket;
  this.clientDataHash=clientDataHash;
  this.clientNameHash=clientNameHash;
  this.chessPeerHash=chessPeerHash;
  this.server=server;
 }

 

 public void messageTransfer(String message)
 {
  String clientName,peerName;

  if(message.startsWith("/"))
  {

   if(message.startsWith("/changename "))
   {
    clientName=message.substring(12);
    if( clientName.length()<=0             || clientName.length()>20                   

||
     clientName.startsWith("/")         || clientNameHash.containsValue(clientName) ||
        clientName.startsWith("changename")|| clientName.startsWith("list")            

||
        clientName.startsWith("[inchess]") || clientName.startsWith("creatgame")       

||
        clientName.startsWith("joingame")  || clientName.startsWith("yourname")        

||
        clientName.startsWith("userlist")  || clientName.startsWith("chess")           

||
        clientName.startsWith("OK")        || clientName.startsWith("reject")          

||
        clientName.startsWith("peer")      || clientName.startsWith("peername")        

||
        clientName.startsWith("giveup")    || clientName.startsWith("youwin")          

||
        clientName.startsWith("������"))
    {
     message="��Ч����";
     Feedback(message);
    }
    else
    {
     if(clientNameHash.containsValue(("[inchess]"+(String)clientNameHash.get

(clientSocket))))
     {
      synchronized(clientNameHash)
      {
       clientNameHash.put((Socket)getHashKey(clientNameHash,

("[inchess]"+clientNameHash.get(clientSocket))),
            ("[inchess]"+clientName));
       chessPeerTalk(("[inchess]"+clientName),("/yourname "+("[inchess]"+clientName)));
      }
     }
     else if(chessPeerHash.containsKey(clientNameHash.get(clientSocket)))
     {
      //��Ϸ�ͻ��˸�����
      synchronized(clientNameHash)
      {
       clientNameHash.put((Socket)getHashKey(clientNameHash,

("[inchess]"+clientNameHash.get(clientSocket))),
            ("[inchess]"+clientName));
      }

      synchronized(chessPeerHash)
      {
       //chessPeerHash���������ӳ��
       chessPeerHash.put(clientName,chessPeerHash.get(clientNameHash.get

(clientSocket)));
       //chessPeerHashɾ����ӳ��
       chessPeerHash.remove(clientNameHash.get(clientSocket));
      }
      //����Ϸ�ͻ��˷���������
      chessPeerTalk(("[inchess]"+clientName),("/yourname "+("[inchess]"+clientName)));
      //��peer��Ϸ�ͻ��˷���
      chessPeerTalk((String)chessPeerHash.get(clientName),("/peer "+"[inchess]"+clientName));

     }
     else if(chessPeerHash.containsValue(clientNameHash.get(clientSocket)))
     {
      synchronized(clientNameHash)
      {
       //��Ϸ�ͻ��˸�����
       clientNameHash.put((Socket)getHashKey(clientNameHash,

("[inchess]"+clientNameHash.get(clientSocket))),
                  ("[inchess]"+clientName));
      }
      synchronized(chessPeerHash)
      {
       //chessPeerHash����ӳ��
       chessPeerHash.put((String)getHashKey(chessPeerHash,clientNameHash.get

(clientSocket)),clientName);
       //����Ϸ�ͻ��˷���������
       chessPeerTalk(("[inchess]"+clientName),("/yourname "+("[inchess]"+clientName)));
      }
      //��peer��Ϸ�ͻ��˷���
      chessPeerTalk((String)getHashKey(chessPeerHash,clientName),("/peer "+"[inchess]"+clientName));

     }

     message=clientNameHash.get(clientSocket)+"����Ϊ:"+clientName;
     synchronized(clientNameHash)
     {
      clientNameHash.put(clientSocket,clientName);
     }
     publicTalk(message);
     Feedback("/yourname "+(String)clientNameHash.get(clientSocket));
     publicTalk(getUserList());

    }

   }
   else if(message.equals("/list"))
   {
    Feedback(getUserList());
          }
          else if(message.startsWith("/creatgame [inchess]"))
          {
    String chessServerName=message.substring(20);
    synchronized(clientNameHash)
    {
     clientNameHash.put(clientSocket,message.substring(11));
    }
    synchronized(chessPeerHash)
    {
     chessPeerHash.put(chessServerName,"wait");
    }
    Feedback("/yourname "+clientNameHash.get(clientSocket));
    chessPeerTalk(chessServerName,"/OK");
    publicTalk(getUserList());
   }
   else if(message.startsWith("/joingame "))
   {
    StringTokenizer userToken=new StringTokenizer(message," ");
    String getUserToken,serverName,selfName;
    String[] chessNameOpt={"0","0"};
    int getOptNum=0;

    while(userToken.hasMoreTokens())
    {
     getUserToken=(String)userToken.nextToken(" ");
     if(getOptNum>=1 && getOptNum<=2)
     {
      chessNameOpt[getOptNum-1]=getUserToken;
     }
     getOptNum++;
    }
    serverName=chessNameOpt[0];
    selfName=chessNameOpt[1];

    if(chessPeerHash.containsKey(serverName) && chessPeerHash.get(serverName).equals

("wait"))
    {
     synchronized(clientNameHash)
     {
      clientNameHash.put(clientSocket,("[inchess]"+selfName));
     }
     synchronized(chessPeerHash)
     {
      chessPeerHash.put(serverName,selfName);
     }
     publicTalk(getUserList());
     chessPeerTalk(selfName,("/peer "+"[inchess]"+serverName));
     chessPeerTalk(serverName,("/peer "+"[inchess]"+selfName));
    }
    else
    {
     chessPeerTalk(selfName,"/reject");
     try
     {
      clientClose();
     }
     catch(Exception ez)
     {
     }
    }
   }
   else if(message.startsWith("/[inchess]"))
   {
    int firstLocation=0,lastLocation;

                lastLocation=message.indexOf(" ",0);

    peerName=message.substring((firstLocation+1),lastLocation);
       message=message.substring((lastLocation+1));
    if(chessPeerTalk(peerName,message))
    {
     Feedback("/error");
    }
   }
   else if(message.startsWith("/giveup "))
   {
    String chessClientName=message.substring(8);
    if(chessPeerHash.containsKey(chessClientName) && !((String)chessPeerHash.get

(chessClientName)).equals("wait"))
    {
     chessPeerTalk((String)chessPeerHash.get(chessClientName),"/youwin");
     synchronized(chessPeerHash)
     {
      chessPeerHash.remove(chessClientName);
     }
    }
    if(chessPeerHash.containsValue(chessClientName))
    {
     chessPeerTalk((String)getHashKey(chessPeerHash,chessClientName),"/youwin");
     synchronized(chessPeerHash)
     {
      chessPeerHash.remove((String)getHashKey(chessPeerHash,chessClientName));
     }
    }
   }
   else
   {

    int firstLocation=0,lastLocation;

                lastLocation=message.indexOf(" ",0);
    if(lastLocation==-1)
    {
     Feedback("��Ч����");
     return;
    }
    else
    {
     peerName=message.substring((firstLocation+1),lastLocation);
     message=message.substring((lastLocation+1));
     message=(String)clientNameHash.get(clientSocket)+">"+message;
     if(peerTalk(peerName,message))
     {
      Feedback("û������û�:"+peerName+"\n");
     }
    }

            }

  }

  else
  {
   message=clientNameHash.get(clientSocket)+">"+message;
   server.messageBoard.append(message+"\n");
   publicTalk(message);
   server.messageBoard.setCaretPosition(server.messageBoard.getText().length());
  }

 


 }


 public void publicTalk(String publicTalkMessage)
 {

  synchronized(clientDataHash)
  {
   for(Enumeration enu=clientDataHash.elements();enu.hasMoreElements();)
   {
    DataOutputStream outData=(DataOutputStream)enu.nextElement();
    try
    {
     outData.writeUTF(publicTalkMessage);
    }
    catch(IOException es)
    {
     es.printStackTrace();
    }
   }
  }

 }

 

    public boolean peerTalk(String peerTalk,String talkMessage)
    {

        for(Enumeration enu=clientDataHash.keys();enu.hasMoreElements();)
        {
   Socket userClient=(Socket)enu.nextElement();

            if(peerTalk.equals((String)clientNameHash.get(userClient)) && !

peerTalk.equals((String)clientNameHash.get(clientSocket)))
            {
                synchronized(clientDataHash)
                {
     DataOutputStream peerOutData=(DataOutputStream)clientDataHash.get(userClient);
     try
     {
      peerOutData.writeUTF(talkMessage);
     }
     catch(IOException es)
     {
      es.printStackTrace();
     }
    }
    Feedback(talkMessage);
    return(false);
       }
       else if(peerTalk.equals((String)clientNameHash.get(clientSocket)))
       {
    Feedback(talkMessage);
    return(false);
    }
      }


      return(true);

    }


    public boolean chessPeerTalk(String chessPeerTalk,String chessTalkMessage)
    {

        for(Enumeration enu=clientDataHash.keys();enu.hasMoreElements();)
        {
   Socket userClient=(Socket)enu.nextElement();

            if(chessPeerTalk.equals((String)clientNameHash.get(userClient)) && !

chessPeerTalk.equals((String)clientNameHash.get(clientSocket)))
            {
                synchronized(clientDataHash)
                {
     DataOutputStream peerOutData=(DataOutputStream)clientDataHash.get(userClient);
     try
     {
      peerOutData.writeUTF(chessTalkMessage);
     }
     catch(IOException es)
     {
      es.printStackTrace();
     }
    }
    return(false);
       }
   }
      return(true);
    }


    public void Feedback(String feedbackString)
    {
  synchronized(clientDataHash)
  {
   DataOutputStream outData=(DataOutputStream)clientDataHash.get(clientSocket);
   try
   {
    outData.writeUTF(feedbackString);
   }
   catch(Exception eb)
   {
    eb.printStackTrace();
   }
  }

 }

 

    public String getUserList()
    {
  String userList="/userlist";

  for(Enumeration enu=clientNameHash.elements();enu.hasMoreElements();)
  {
   userList=userList+" "+(String)enu.nextElement();
  }
  return(userList);
 }


 public Object getHashKey(Hashtable targetHash,Object hashValue)
 {
  Object hashKey;
  for(Enumeration enu=targetHash.keys();enu.hasMoreElements();)
  {
   hashKey=(Object)enu.nextElement();
   if(hashValue.equals((Object)targetHash.get(hashKey)))
    return(hashKey);
     }
     return(null);
 }

 public void firstCome()
 {
  publicTalk(getUserList());
  Feedback("/yourname "+(String)clientNameHash.get(clientSocket));
  Feedback("Java����������ͻ���");
  Feedback("/changename <�������>  --��������");
  Feedback("/list --�����û��б�");
  Feedback("/<�û���> <Ҫ˵�Ļ�>  --˽��");
  Feedback("ע�⣺�������ʱ���Ȱ�̸���Ķ���Ϊ������");
 }

 

 public void clientClose()
 {
  server.messageBoard.append("�û��Ͽ�:"+clientSocket+"\n");
  //�������Ϸ�ͻ�������
  synchronized(chessPeerHash)
  {
   if(chessPeerHash.containsKey(clientNameHash.get(clientSocket)))
   {
    chessPeerHash.remove((String)clientNameHash.get(clientSocket));
   }
   if(chessPeerHash.containsValue(clientNameHash.get(clientSocket)))
   {
    chessPeerHash.put((String)getHashKey(chessPeerHash,(String)clientNameHash.get

(clientSocket)),"tobeclosed");
   }
  }
  synchronized(clientDataHash)
  {
   clientDataHash.remove(clientSocket);
  }
  synchronized(clientNameHash)
  {
   clientNameHash.remove(clientSocket);
  }
  publicTalk(getUserList());
  server.statusLabel.setText("��ǰ������:"+clientDataHash.size());
  try
  {
   clientSocket.close();
  }
  catch(IOException exx)
  {
  }

  isClientClosed=true;

 }


 public void run()
 {
  DataInputStream inData;
        synchronized(clientDataHash)
        {
   server.statusLabel.setText("��ǰ������:"+clientDataHash.size());
  }
  try
  {
   inData=new DataInputStream(clientSocket.getInputStream());
            firstCome();
   while(true)
   {
    String message=inData.readUTF();
    messageTransfer(message);
   }
  }
  catch(IOException esx)
  {
  }
  finally
  {
   if(!isClientClosed)
   {
    clientClose();
   }
  }
 }


}

 

 


public class chessServer extends Frame implements ActionListener
{

 Button messageClearButton=new Button("�����ʾ");
 Button serverStatusButton=new Button("������״̬");
 Button serverOffButton=new Button("�رշ�����");
 Panel buttonPanel=new Panel();

 MessageServer server=new MessageServer();
 ServerSocket serverSocket;
    Hashtable clientDataHash=new Hashtable(50);
    Hashtable clientNameHash=new Hashtable(50);
    Hashtable chessPeerHash=new Hashtable(50);

 chessServer()
 {
  super("Java�����������");
  setBackground(Color.pink);


  buttonPanel.setLayout(new FlowLayout());
  messageClearButton.setSize(60,25);
  buttonPanel.add(messageClearButton);
  messageClearButton.addActionListener(this);
  serverStatusButton.setSize(75,25);
  buttonPanel.add(serverStatusButton);
  serverStatusButton.addActionListener(this);
  serverOffButton.setSize(75,25);
     buttonPanel.add(serverOffButton);
     serverOffButton.addActionListener(this);

  add(server,BorderLayout.CENTER);
  add(buttonPanel,BorderLayout.SOUTH);

  addWindowListener(new WindowAdapter()
  {
   public void windowClosing(WindowEvent e)
   {
    System.exit(0);
   }
  });
  pack();
  setVisible(true);
  setSize(400,450);
  setResizable(false);
  validate();
  try
  {
   makeMessageServer(4331,server);
  }
  catch(Exception e)
  {
   System.out.println("e");
  }
 }

 public void makeMessageServer(int port,MessageServer server) throws IOException
 {
  Socket clientSocket;
  long clientAccessNumber=1;
  this.server=server;

  try
  {
   serverSocket=new ServerSocket(port);
   server.messageBoard.setText("��������ʼ��:"+serverSocket.getInetAddress

().getLocalHost()+":"+serverSocket.getLocalPort()+"\n");

   while(true)
   {
    clientSocket=serverSocket.accept();
         server.messageBoard.append("�û�����:"+clientSocket+"\n");

    DataOutputStream outData=new DataOutputStream(clientSocket.getOutputStream());

    clientDataHash.put(clientSocket,outData);
    clientNameHash.put(clientSocket,("������"+clientAccessNumber++));

    ServerThread thread=new ServerThread

(clientSocket,clientDataHash,clientNameHash,chessPeerHash,server);

    thread.start();
   }
  }
  catch(IOException ex)
  {
   System.out.println("�Ѿ��з�����������. \n");
  }


}

 public void actionPerformed(ActionEvent e)
 {
  if(e.getSource()==messageClearButton)
  {
   server.messageBoard.setText("");
  }
  if(e.getSource()==serverStatusButton)
  {
   try
   {
    server.messageBoard.append("��������Ϣ:"+serverSocket.getInetAddress

().getLocalHost()+":"+serverSocket.getLocalPort()+"\n");
      }
      catch(Exception ee)
      {
    System.out.println("serverSocket.getInetAddress().getLocalHost() error \n");
   }
  }
  if(e.getSource()==serverOffButton)
  {
   System.exit(0);
  }
 }

 public static void main(String args[])
 {
  chessServer chessServer=new chessServer();
 }
}
