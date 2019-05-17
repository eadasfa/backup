package wuziqi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

class clientThread extends Thread
{
 chessClient chessclient;

 clientThread(chessClient chessclient)
 {
  this.chessclient=chessclient;
 }

 public void acceptMessage(String recMessage)
 {
  if(recMessage.startsWith("/userlist "))
  {
   StringTokenizer userToken=new StringTokenizer(recMessage," ");
   int userNumber=0;

   chessclient.userpad.userList.removeAll();
   chessclient.inputpad.userChoice.removeAll();
   chessclient.inputpad.userChoice.addItem("所有人");
   while(userToken.hasMoreTokens())
   {
    String user=(String)userToken.nextToken(" ");
    if(userNumber>0 && !user.startsWith("[inchess]"))
    {
     chessclient.userpad.userList.add(user);
     chessclient.inputpad.userChoice.addItem(user);
    }

    userNumber++;
   }
   chessclient.inputpad.userChoice.select("所有人");
  }
  else if(recMessage.startsWith("/yourname "))
  {
   chessclient.chessClientName=recMessage.substring(10);
   chessclient.setTitle("Java五子棋客户端   "+"用户名:"+chessclient.chessClientName);
  }
  else if(recMessage.equals("/reject"))
  {
   try
   {
    chessclient.chesspad.statusText.setText("不能加入游戏");
    chessclient.controlpad.cancelGameButton.setEnabled(false);
    chessclient.controlpad.joinGameButton.setEnabled(true);
    chessclient.controlpad.creatGameButton.setEnabled(true);
   }
   catch(Exception ef)
   {
    chessclient.chatpad.chatLineArea.setText("chessclient.chesspad.chessSocket.close无法关闭");
   }
   chessclient.controlpad.joinGameButton.setEnabled(true);
  }
  else if(recMessage.startsWith("/peer "))
  {
   chessclient.chesspad.chessPeerName=recMessage.substring(6);
   if(chessclient.isServer)
   {
    chessclient.chesspad.chessColor=1;
    chessclient.chesspad.isMouseEnabled=true;
    chessclient.chesspad.statusText.setText("请黑棋下子");
   }
   else if(chessclient.isClient)
   {
    chessclient.chesspad.chessColor=-1;
    chessclient.chesspad.statusText.setText("已加入游戏，等待对方下子...");
   }

  }
  else if(recMessage.equals("/youwin"))
  {
   chessclient.isOnChess=false;
   chessclient.chesspad.chessVictory(chessclient.chesspad.chessColor);
   chessclient.chesspad.statusText.setText("对方退出，请点放弃游戏退出连接");
   chessclient.chesspad.isMouseEnabled=false;
  }
  else if(recMessage.equals("/OK"))
  {
   chessclient.chesspad.statusText.setText("创建游戏成功，等待别人加入...");
  }
  else if(recMessage.equals("/error"))
  {
   chessclient.chatpad.chatLineArea.append("传输错误：请退出程序，重新加入 \n");
  }
  else
  {
   chessclient.chatpad.chatLineArea.append(recMessage+"\n");
   chessclient.chatpad.chatLineArea.setCaretPosition(
    chessclient.chatpad.chatLineArea.getText().length());
  }
 }


 public void run()
 {
     String message="";
     try
     {
   while(true)
   {
    message=chessclient.in.readUTF();
    acceptMessage(message);
   }
  }
  catch(IOException es)
  {
  }
 }

}

 

 


public class chessClient extends Frame implements ActionListener,KeyListener
{
 userPad userpad=new userPad();
 chatPad chatpad=new chatPad();
 controlPad controlpad=new controlPad();
 chessPad chesspad=new chessPad();
 inputPad inputpad=new inputPad();


 Socket chatSocket;
 DataInputStream in;
 DataOutputStream out;
 String chessClientName=null;
 String host=null;
 int port=4331;

 boolean isOnChat=false;  //在聊天？
 boolean isOnChess=false; //在下棋？
 boolean isGameConnected=false; //下棋的客户端连接？
 boolean isServer=false; //如果是下棋的主机
 boolean isClient=false; //如果是下棋的客户端

 Panel southPanel=new Panel();
 Panel northPanel=new Panel();
 Panel centerPanel=new Panel();
 Panel westPanel=new Panel();
 Panel eastPanel=new Panel();

 chessClient()
 {
  super("Java五子棋客户端");
  setLayout(new BorderLayout());
  host=controlpad.inputIP.getText();

  westPanel.setLayout(new BorderLayout());
  westPanel.add(userpad,BorderLayout.NORTH);
  westPanel.add(chatpad,BorderLayout.CENTER);
  westPanel.setBackground(Color.pink);

  inputpad.inputWords.addKeyListener(this);
  chesspad.host=controlpad.inputIP.getText();

  centerPanel.add(chesspad,BorderLayout.CENTER);
  centerPanel.add(inputpad,BorderLayout.SOUTH);
  centerPanel.setBackground(Color.pink);

  controlpad.connectButton.addActionListener(this);
  controlpad.creatGameButton.addActionListener(this);
  controlpad.joinGameButton.addActionListener(this);
  controlpad.cancelGameButton.addActionListener(this);
  controlpad.exitGameButton.addActionListener(this);

  controlpad.creatGameButton.setEnabled(false);
  controlpad.joinGameButton.setEnabled(false);
  controlpad.cancelGameButton.setEnabled(false);

  southPanel.add(controlpad,BorderLayout.CENTER);
  southPanel.setBackground(Color.pink);


  addWindowListener(new WindowAdapter()
  {
   public void windowClosing(WindowEvent e)
   {
     if(isOnChat)
     {
      try
      {
       chatSocket.close();
      }
      catch(Exception ed)
      {
      }
     }
     if(isOnChess || isGameConnected)
     {
      try
      {
       chesspad.chessSocket.close();
      }
      catch(Exception ee)
      {
      }
     }
    System.exit(0);
   }
   public void windowActivated(WindowEvent ea)
   {

   }
  });

       add(westPanel,BorderLayout.WEST);
      add(centerPanel,BorderLayout.CENTER);
      add(southPanel,BorderLayout.SOUTH);

  pack();
  setSize(670,548);
  setVisible(true);
  setResizable(false);
  validate();
 }

 

 public void setTitle(String string) {
	// TODO 自动生成的方法存根
	
}



public boolean connectServer(String serverIP,int serverPort) throws Exception
 {
  try
  {
   chatSocket=new Socket(serverIP,serverPort);
   in=new DataInputStream(chatSocket.getInputStream());
   out=new DataOutputStream(chatSocket.getOutputStream());

   clientThread clientthread=new clientThread(this);
   clientthread.start();
   isOnChat=true;
   return true;
  }
  catch(IOException ex)
  {
   chatpad.chatLineArea.setText("chessClient:connectServer:无法连接,建议重新启动程序 \n");
  }
  return false;
 }


  public void actionPerformed(ActionEvent e)
  {
   if(e.getSource()==controlpad.connectButton)
   {
    host=chesspad.host=controlpad.inputIP.getText();
    try
    {
     if(connectServer(host,port))
     {
      chatpad.chatLineArea.setText("");
      controlpad.connectButton.setEnabled(false);
      controlpad.creatGameButton.setEnabled(true);
      controlpad.joinGameButton.setEnabled(true);
      chesspad.statusText.setText("连接成功，请创建游戏或加入游戏");
     }


    }
    catch(Exception ei)
    {
     chatpad.chatLineArea.setText("controlpad.connectButton:无法连接,建议重新启动程序 \n");
    }
    }
    if(e.getSource()==controlpad.exitGameButton)
    {
     if(isOnChat)
     {
      try
      {
       chatSocket.close();
      }
      catch(Exception ed)
      {
      }
     }
     if(isOnChess || isGameConnected)
     {
      try
      {
       chesspad.chessSocket.close();
      }
      catch(Exception ee)
      {
      }
     }
     System.exit(0);

    }
    if(e.getSource()==controlpad.joinGameButton)
    {
     String selectedUser=userpad.userList.getSelectedItem();
     if(selectedUser==null || selectedUser.startsWith("[inchess]") ||
       selectedUser.equals(chessClientName))
     {
      chesspad.statusText.setText("必须先选定一个有效用户");
     }
     else
     {
      try
      {
       if(!isGameConnected)
       {
        if(chesspad.connectServer(chesspad.host,chesspad.port))
        {
         isGameConnected=true;
         isOnChess=true;
         isClient=true;
         controlpad.creatGameButton.setEnabled(false);
         controlpad.joinGameButton.setEnabled(false);
         controlpad.cancelGameButton.setEnabled(true);
         chesspad.chessthread.sendMessage("/joingame "+userpad.userList.getSelectedItem()+" "+chessClientName);
        }
       }
       else
       {
        isOnChess=true;
        isClient=true;
        controlpad.creatGameButton.setEnabled(false);
        controlpad.joinGameButton.setEnabled(false);
        controlpad.cancelGameButton.setEnabled(true);
        chesspad.chessthread.sendMessage("/joingame "+userpad.userList.getSelectedItem

()+" "+chessClientName);
       }


      }
      catch(Exception ee)
      {
       isGameConnected=false;
       isOnChess=false;
       isClient=false;
       controlpad.creatGameButton.setEnabled(true);
       controlpad.joinGameButton.setEnabled(true);
       controlpad.cancelGameButton.setEnabled(false);
       chatpad.chatLineArea.setText("chesspad.connectServer无法连接 \n"+ee);
      }

     }
    }
    if(e.getSource()==controlpad.creatGameButton)
    {
     try
     {
      if(!isGameConnected)
      {
       if(chesspad.connectServer(chesspad.host,chesspad.port))
       {
        isGameConnected=true;
        isOnChess=true;
        isServer=true;
        controlpad.creatGameButton.setEnabled(false);
        controlpad.joinGameButton.setEnabled(false);
        controlpad.cancelGameButton.setEnabled(true);
        chesspad.chessthread.sendMessage("/creatgame "+"[inchess]"+chessClientName);
       }
      }
      else
      {
       isOnChess=true;
       isServer=true;
       controlpad.creatGameButton.setEnabled(false);
       controlpad.joinGameButton.setEnabled(false);
       controlpad.cancelGameButton.setEnabled(true);
       chesspad.chessthread.sendMessage("/creatgame "+"[inchess]"+chessClientName);
      }
     }
     catch(Exception ec)
     {
      isGameConnected=false;
      isOnChess=false;
      isServer=false;
      controlpad.creatGameButton.setEnabled(true);
      controlpad.joinGameButton.setEnabled(true);
      controlpad.cancelGameButton.setEnabled(false);
      ec.printStackTrace();
      chatpad.chatLineArea.setText("chesspad.connectServer无法连接 \n"+ec);
     }

    }
    if(e.getSource()==controlpad.cancelGameButton)
    {
        if(isOnChess)
        {
      chesspad.chessthread.sendMessage("/giveup "+chessClientName);
      chesspad.chessVictory(-1*chesspad.chessColor);
      controlpad.creatGameButton.setEnabled(true);
      controlpad.joinGameButton.setEnabled(true);
      controlpad.cancelGameButton.setEnabled(false);
      chesspad.statusText.setText("请建立游戏或者加入游戏");
     }
     if(!isOnChess)
     {
      controlpad.creatGameButton.setEnabled(true);
      controlpad.joinGameButton.setEnabled(true);
      controlpad.cancelGameButton.setEnabled(false);
      chesspad.statusText.setText("请建立游戏或者加入游戏");
     }
     isClient=isServer=false;
     }

   }

 

   public void keyPressed(KeyEvent e)
   {
    TextField inputWords=(TextField)e.getSource();


    if(e.getKeyCode()==KeyEvent.VK_ENTER)
    {
     if(inputpad.userChoice.getSelectedItem().equals("所有人"))
     {
      try
      {
       out.writeUTF(inputWords.getText());
       inputWords.setText("");
      }
      catch(Exception ea)
      {
       chatpad.chatLineArea.setText("chessClient:KeyPressed无法连接,建议重新连接 \n");
       userpad.userList.removeAll();
       inputpad.userChoice.removeAll();
       inputWords.setText("");
       controlpad.connectButton.setEnabled(true);
      }
     }
     else
     {
      try
      {
       out.writeUTF("/"+inputpad.userChoice.getSelectedItem()+" "+inputWords.getText

());
       inputWords.setText("");
      }
      catch(Exception ea)
      {
       chatpad.chatLineArea.setText("chessClient:KeyPressed无法连接,建议重新连接 \n");
       userpad.userList.removeAll();
       inputpad.userChoice.removeAll();
       inputWords.setText("");
       controlpad.connectButton.setEnabled(true);
      }
     }
      }

   }

   public void keyTyped(KeyEvent e)
   {
   }
   public void keyReleased(KeyEvent e)
   {
   }

 

   public static void main(String args[])
   {
    chessClient chessClient=new chessClient();
   }
}