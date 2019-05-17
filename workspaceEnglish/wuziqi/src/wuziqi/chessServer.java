package wuziqi;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class MessageServer extends Panel //implements ActionListener
{
 TextArea messageBoard=new TextArea("",22,50,TextArea.SCROLLBARS_VERTICAL_ONLY);
 Label statusLabel=new Label("当前连接数:",Label.LEFT);
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
        clientName.startsWith("所有人"))
    {
     message="无效命令";
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
      //游戏客户端改名字
      synchronized(clientNameHash)
      {
       clientNameHash.put((Socket)getHashKey(clientNameHash,

("[inchess]"+clientNameHash.get(clientSocket))),
            ("[inchess]"+clientName));
      }

      synchronized(chessPeerHash)
      {
       //chessPeerHash添加新名字映射
       chessPeerHash.put(clientName,chessPeerHash.get(clientNameHash.get

(clientSocket)));
       //chessPeerHash删除旧映射
       chessPeerHash.remove(clientNameHash.get(clientSocket));
      }
      //向游戏客户端发送新名字
      chessPeerTalk(("[inchess]"+clientName),("/yourname "+("[inchess]"+clientName)));
      //向peer游戏客户端发送
      chessPeerTalk((String)chessPeerHash.get(clientName),("/peer "+"[inchess]"+clientName));

     }
     else if(chessPeerHash.containsValue(clientNameHash.get(clientSocket)))
     {
      synchronized(clientNameHash)
      {
       //游戏客户端改名字
       clientNameHash.put((Socket)getHashKey(clientNameHash,

("[inchess]"+clientNameHash.get(clientSocket))),
                  ("[inchess]"+clientName));
      }
      synchronized(chessPeerHash)
      {
       //chessPeerHash重新映射
       chessPeerHash.put((String)getHashKey(chessPeerHash,clientNameHash.get

(clientSocket)),clientName);
       //向游戏客户端发送新名字
       chessPeerTalk(("[inchess]"+clientName),("/yourname "+("[inchess]"+clientName)));
      }
      //向peer游戏客户端发送
      chessPeerTalk((String)getHashKey(chessPeerHash,clientName),("/peer "+"[inchess]"+clientName));

     }

     message=clientNameHash.get(clientSocket)+"改名为:"+clientName;
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
     Feedback("无效命令");
     return;
    }
    else
    {
     peerName=message.substring((firstLocation+1),lastLocation);
     message=message.substring((lastLocation+1));
     message=(String)clientNameHash.get(clientSocket)+">"+message;
     if(peerTalk(peerName,message))
     {
      Feedback("没有这个用户:"+peerName+"\n");
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
  Feedback("Java五子棋聊天客户端");
  Feedback("/changename <你的名字>  --更改名字");
  Feedback("/list --更新用户列表");
  Feedback("/<用户名> <要说的话>  --私聊");
  Feedback("注意：用命令的时候，先把谈话的对象定为所有人");
 }

 

 public void clientClose()
 {
  server.messageBoard.append("用户断开:"+clientSocket+"\n");
  //如果是游戏客户端主机
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
  server.statusLabel.setText("当前连接数:"+clientDataHash.size());
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
   server.statusLabel.setText("当前连接数:"+clientDataHash.size());
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

 Button messageClearButton=new Button("清除显示");
 Button serverStatusButton=new Button("服务器状态");
 Button serverOffButton=new Button("关闭服务器");
 Panel buttonPanel=new Panel();

 MessageServer server=new MessageServer();
 ServerSocket serverSocket;
    Hashtable clientDataHash=new Hashtable(50);
    Hashtable clientNameHash=new Hashtable(50);
    Hashtable chessPeerHash=new Hashtable(50);

 chessServer()
 {
  super("Java五子棋服务器");
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
   server.messageBoard.setText("服务器开始于:"+serverSocket.getInetAddress

().getLocalHost()+":"+serverSocket.getLocalPort()+"\n");

   while(true)
   {
    clientSocket=serverSocket.accept();
         server.messageBoard.append("用户连接:"+clientSocket+"\n");

    DataOutputStream outData=new DataOutputStream(clientSocket.getOutputStream());

    clientDataHash.put(clientSocket,outData);
    clientNameHash.put(clientSocket,("新来客"+clientAccessNumber++));

    ServerThread thread=new ServerThread

(clientSocket,clientDataHash,clientNameHash,chessPeerHash,server);

    thread.start();
   }
  }
  catch(IOException ex)
  {
   System.out.println("已经有服务器在运行. \n");
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
    server.messageBoard.append("服务器信息:"+serverSocket.getInetAddress

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
