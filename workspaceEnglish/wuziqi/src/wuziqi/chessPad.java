package wuziqi;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

class chessThread extends Thread
{
 chessPad chesspad;

 chessThread(chessPad chesspad)
 {
  this.chesspad=chesspad;
 }

 public void sendMessage(String sndMessage)
 {
  try
  {
   chesspad.outData.writeUTF(sndMessage);
  }
  catch(Exception ea)
  {
   System.out.println("chessThread.sendMessage:"+ea);
  }
 }


 public void acceptMessage(String recMessage)
 {
  if(recMessage.startsWith("/chess "))
  {
   StringTokenizer userToken=new StringTokenizer(recMessage," ");
   String chessToken;
   String[] chessOpt={"-1","-1","0"};
   int chessOptNum=0;

   while(userToken.hasMoreTokens())
   {
    chessToken=(String)userToken.nextToken(" ");
    if(chessOptNum>=1 && chessOptNum<=3)
    {
     chessOpt[chessOptNum-1]=chessToken;

    }
    chessOptNum++;
   }
   chesspad.netChessPaint(Integer.parseInt(chessOpt[0]),Integer.parseInt(chessOpt

[1]),Integer.parseInt(chessOpt[2]));

  }
  else if(recMessage.startsWith("/yourname "))
  {
   chesspad.chessSelfName=recMessage.substring(10);
  }
  else if(recMessage.equals("/error"))
  {
   chesspad.statusText.setText("错误:没有这个用户源码天空，请退出程序，重新加入");
  }
  else
  {
   //System.out.println(recMessage);
  }
 }


 public void run()
 {
     String message="";
     try
     {
   while(true)
   {
    message=chesspad.inData.readUTF();
    acceptMessage(message);
   }
  }
  catch(IOException es)
  {
  }
 }

}

 


class chessPad extends Panel implements MouseListener,ActionListener
{
 int chessPoint_x=-1,chessPoint_y=-1,chessColor=1;
 int chessBlack_x[]=new int[200];
 int chessBlack_y[]=new int[200];
 int chessWhite_x[]=new int[200];
 int chessWhite_y[]=new int[200];
 int chessBlackCount=0,chessWhiteCount=0;
 int chessBlackWin=0,chessWhiteWin=0;
 boolean isMouseEnabled=false,isWin=false,isInGame=false;
 TextField statusText=new TextField("请先连接服务器");

    Socket chessSocket;
 DataInputStream inData;
 DataOutputStream outData;

 String chessSelfName=null;
 String chessPeerName=null;
 String host=null;
 int port=4331;
 chessThread chessthread=new chessThread(this);

    chessPad()
    {
  setSize(440,440);
     setLayout(null);
     setBackground(Color.pink);
     addMouseListener(this);
     add(statusText);
     statusText.setBounds(40,5,360,24);
     statusText.setEditable(false);
    }


  public boolean connectServer(String ServerIP,int ServerPort) throws Exception
  {
   try
   {
    chessSocket=new Socket(ServerIP,ServerPort);
    inData=new DataInputStream(chessSocket.getInputStream());
    outData=new DataOutputStream(chessSocket.getOutputStream());
     chessthread.start();
    return true;
   }
   catch(IOException ex)
   {
    statusText.setText("chessPad:connectServer:无法连接 \n");
   }
   return false;
  }


     public void chessVictory(int chessColorWin)
     {
    this.removeAll();
     for(int i=0;i<=chessBlackCount;i++)
    {
     chessBlack_x[i]=0;
          chessBlack_y[i]=0;
    }
    for(int i=0;i<=chessWhiteCount;i++)
    {
     chessWhite_x[i]=0;
          chessWhite_y[i]=0;
    }
    chessBlackCount=0;
    chessWhiteCount=0;
    add(statusText);
    statusText.setBounds(40,5,360,24);

         if(chessColorWin==1)
   { chessBlackWin++;
    statusText.setText("黑棋胜,黑:白为"+chessBlackWin+":"+chessWhiteWin+",重新开局,等待白棋下子...");
   }
      else if(chessColorWin==-1)
      {
    chessWhiteWin++;
    statusText.setText("白棋胜,黑:白为"+chessBlackWin+":"+chessWhiteWin+",重新开局,等待黑棋下子...");
   }
  }

 

     public void getLocation(int a,int b,int color)
     {

   if(color==1)
   {
    chessBlack_x[chessBlackCount]=a*20;
    chessBlack_y[chessBlackCount]=b*20;
    chessBlackCount++;
   }
   else if(color==-1)
   {
    chessWhite_x[chessWhiteCount]=a*20;
    chessWhite_y[chessWhiteCount]=b*20;
    chessWhiteCount++;
   }
  }


     public boolean checkWin(int a,int b,int checkColor)
     {
   int step=1,chessLink=1,chessLinkTest=1,chessCompare=0;
   if(checkColor==1)
   {
    chessLink=1;
    for(step=1;step<=4;step++)
    {
     for(chessCompare=0;chessCompare<=chessBlackCount;chessCompare++)
     {
      if(((a+step)*20==chessBlack_x[chessCompare]) && ((b*20)==chessBlack_y

[chessCompare]))
      {
       chessLink=chessLink+1;
       if(chessLink==5)
       {
        return(true);
       }
      }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
     }
    for(step=1;step<=4;step++)
    {
     for(chessCompare=0;chessCompare<=chessBlackCount;chessCompare++)
     {
      if(((a-step)*20==chessBlack_x[chessCompare]) && (b*20==chessBlack_y

[chessCompare]))
      {
       chessLink++;
       if(chessLink==5)
       {
        return(true);
       }
       }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
     }
    chessLink=1;
    chessLinkTest=1;
    for(step=1;step<=4;step++)
    {
     for(chessCompare=0;chessCompare<=chessBlackCount;chessCompare++)
     {
      if((a*20==chessBlack_x[chessCompare]) && ((b+step)*20==chessBlack_y

[chessCompare]))
      {
       chessLink++;
       if(chessLink==5)
       {
        return(true);
       }
       }
         }
         if(chessLink==(chessLinkTest+1))
         chessLinkTest++;
         else
         break;
     }
    for(step=1;step<=4;step++)
    {
     for(chessCompare=0;chessCompare<=chessBlackCount;chessCompare++)
     {
      if((a*20==chessBlack_x[chessCompare]) && ((b-step)*20==chessBlack_y

[chessCompare]))
      {
       chessLink++;
       if(chessLink==5)
       {
        return(true);
       }
       }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
     }
      chessLink=1;
      chessLinkTest=1;
      for(step=1;step<=4;step++)
      {
       for(chessCompare=0;chessCompare<=chessBlackCount;chessCompare++)
       {
        if(((a-step)*20==chessBlack_x[chessCompare]) && ((b+step)*20==chessBlack_y

[chessCompare]))
        {
         chessLink++;
       if(chessLink==5)
       {
        return(true);
        }
         }
     }
     if(chessLink==(chessLinkTest+1))
     chessLinkTest++;
     else
     break;
      }
      for(step=1;step<=4;step++)
      {
       for(chessCompare=0;chessCompare<=chessBlackCount;chessCompare++)
       {
        if(((a+step)*20==chessBlack_x[chessCompare]) && ((b-step)*20==chessBlack_y

[chessCompare]))
        {
         chessLink++;
       if(chessLink==5)
       {
        return(true);
        }
         }
        }
        if(chessLink==(chessLinkTest+1))
        chessLinkTest++;
        else
        break;
       }
      chessLink=1;
      chessLinkTest=1;
      for(step=1;step<=4;step++)
      {
       for(chessCompare=0;chessCompare<=chessBlackCount;chessCompare++)
       {
        if(((a+step)*20==chessBlack_x[chessCompare]) && ((b+step)*20==chessBlack_y

[chessCompare]))
        {
         chessLink++;
       if(chessLink==5)
       {
        return(true);
        }
         }
        }
        if(chessLink==(chessLinkTest+1))
        chessLinkTest++;
        else
        break;
      }
      for(step=1;step<=4;step++)
      {
       for(chessCompare=0;chessCompare<=chessBlackCount;chessCompare++)
       {
        if(((a-step)*20==chessBlack_x[chessCompare]) && ((b-step)*20==chessBlack_y

[chessCompare]))
        {
         chessLink++;
         if(chessLink==5)
         {
          return(true);
          }
      }
     }
        if(chessLink==(chessLinkTest+1))
        chessLinkTest++;
        else
        break;
       }
   }
     else if(checkColor==-1)
     {
    chessLink=1;
    for(step=1;step<=4;step++)
    {
     for(chessCompare=0;chessCompare<=chessWhiteCount;chessCompare++)
     {
      if(((a+step)*20==chessWhite_x[chessCompare]) && (b*20==chessWhite_y

[chessCompare]))
      {
       chessLink++;
       if(chessLink==5)
       {
        return(true);
       }
       }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
     }
    for(step=1;step<=4;step++)
    {
     for(chessCompare=0;chessCompare<=chessWhiteCount;chessCompare++)
     {
      if(((a-step)*20==chessWhite_x[chessCompare]) && (b*20==chessWhite_y

[chessCompare]))
      {
       chessLink++;
       if(chessLink==5)
       {
        return(true);
       }
       }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
     }
    chessLink=1;
    chessLinkTest=1;
    for(step=1;step<=4;step++)
    {
     for(chessCompare=0;chessCompare<=chessWhiteCount;chessCompare++)
     {
      if((a*20==chessWhite_x[chessCompare]) && ((b+step)*20==chessWhite_y

[chessCompare]))
      {
       chessLink++;
       if(chessLink==5)
       {
        return(true);
       }
       }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
     }
    for(step=1;step<=4;step++)
    {
     for(chessCompare=0;chessCompare<=chessWhiteCount;chessCompare++)
     {
      if((a*20==chessWhite_x[chessCompare]) && ((b-step)*20==chessWhite_y

[chessCompare]))
      {
       chessLink++;
       if(chessLink==5)
       {
        return(true);
       }
       }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
      }
      chessLink=1;
      chessLinkTest=1;
      for(step=1;step<=4;step++)
      {
       for(chessCompare=0;chessCompare<=chessWhiteCount;chessCompare++)
       {
        if(((a-step)*20==chessWhite_x[chessCompare]) && ((b+step)*20==chessWhite_y

[chessCompare]))
        {
         chessLink++;
       if(chessLink==5)
       {
        return(true);
       }
       }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
      }
      for(step=1;step<=4;step++)
      {
       for(chessCompare=0;chessCompare<=chessWhiteCount;chessCompare++)
       {
        if(((a+step)*20==chessWhite_x[chessCompare]) && ((b-step)*20==chessWhite_y

[chessCompare]))
        {
         chessLink++;
         if(chessLink==5)
         {
          return(true);
       }
         }
        }
        if(chessLink==(chessLinkTest+1))
        chessLinkTest++;
        else
        break;
      }
      chessLink=1;
      chessLinkTest=1;
      for(step=1;step<=4;step++)
      {
       for(chessCompare=0;chessCompare<=chessWhiteCount;chessCompare++)
       {
        if(((a+step)*20==chessWhite_x[chessCompare]) && ((b+step)*20==chessWhite_y

[chessCompare]))
        {
         chessLink++;
         if(chessLink==5)
         {
          return(true);
          }
       }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
      }
      for(step=1;step<=4;step++)
      {
       for(chessCompare=0;chessCompare<=chessWhiteCount;chessCompare++)
       {
        if(((a-step)*20==chessWhite_x[chessCompare]) && ((b-step)*20==chessWhite_y

[chessCompare]))
        {
         chessLink++;
         if(chessLink==5)
         {
          return(true);
          }
         }
      }
      if(chessLink==(chessLinkTest+1))
      chessLinkTest++;
      else
      break;
      }
     }
           return(false);
    }

 

 

     public void paint(Graphics g)
     {
   for (int i=40;i<=380;i=i+20)
      {
    g.drawLine(40,i,400,i);
   }
   g.drawLine(40,400,400,400);
         for(int j=40;j<=380;j=j+20)
         {
    g.drawLine(j,40,j,400);
   }
         g.drawLine(400,40,400,400);
         g.fillOval(97,97,6,6);
         g.fillOval(337,97,6,6);
         g.fillOval(97,337,6,6);
         g.fillOval(337,337,6,6);
         g.fillOval(217,217,6,6);
      }


      public void chessPaint(int chessPoint_a,int chessPoint_b,int color)
      {
    chessPoint_black chesspoint_black=new chessPoint_black(this);
    chessPoint_white chesspoint_white=new chessPoint_white(this);

    if(color==1 && isMouseEnabled)
    {
     getLocation(chessPoint_a,chessPoint_b,color);
     isWin=checkWin(chessPoint_a,chessPoint_b,color);
     if(isWin==false)
     {
      chessthread.sendMessage("/"+chessPeerName+" /chess "+chessPoint_a+" "+chessPoint_b+" "+color);
      this.add(chesspoint_black);
      chesspoint_black.setBounds(chessPoint_a*20-7,chessPoint_b*20-7,16,16);
      statusText.setText("黑(第"+chessBlackCount+"步)"+chessPoint_a+" "+chessPoint_b+",请白棋下子");
      isMouseEnabled=false;
     }
     else
     {
      chessthread.sendMessage("/"+chessPeerName+" /chess "+chessPoint_a+" "+chessPoint_b+" "+color);
      this.add(chesspoint_black);
      chesspoint_black.setBounds(chessPoint_a*20-7,chessPoint_b*20-7,16,16);
      chessVictory(1);
      isMouseEnabled=false;
     }
    }
    else if(color==-1 && isMouseEnabled)
    {
     getLocation(chessPoint_a,chessPoint_b,color);
     isWin=checkWin(chessPoint_a,chessPoint_b,color);
     if(isWin==false)
     {
      chessthread.sendMessage("/"+chessPeerName+" /chess "+chessPoint_a+" "+chessPoint_b+" "+color);
      this.add(chesspoint_white);
      chesspoint_white.setBounds(chessPoint_a*20-7,chessPoint_b*20-7,16,16);
      statusText.setText("白(第"+chessWhiteCount+"步)"+chessPoint_a+" "+chessPoint_b+",请黑棋下子");
      isMouseEnabled=false;
     }
     else
     {
      chessthread.sendMessage("/"+chessPeerName+" /chess "+chessPoint_a+" "+chessPoint_b+" "+color);
      this.add(chesspoint_white);
      chesspoint_white.setBounds(chessPoint_a*20-7,chessPoint_b*20-7,16,16);
      chessVictory(-1);
      isMouseEnabled=false;
     }
    }
   }


      public void netChessPaint(int chessPoint_a,int chessPoint_b,int color)
      {
    chessPoint_black chesspoint_black=new chessPoint_black(this);
    chessPoint_white chesspoint_white=new chessPoint_white(this);
    getLocation(chessPoint_a,chessPoint_b,color);
    if(color==1)
    {
     isWin=checkWin(chessPoint_a,chessPoint_b,color);
     if(isWin==false)
     {

      this.add(chesspoint_black);
      chesspoint_black.setBounds(chessPoint_a*20-7,chessPoint_b*20-7,16,16);
      statusText.setText("黑(第"+chessBlackCount+"步)"+chessPoint_a+" "+chessPoint_b+",请白棋下子");
      isMouseEnabled=true;
     }
     else
     {
      this.add(chesspoint_black);
      chesspoint_black.setBounds(chessPoint_a*20-7,chessPoint_b*20-7,16,16);
      chessVictory(1);
      isMouseEnabled=true;
     }
    }
    else if(color==-1)
    {
     isWin=checkWin(chessPoint_a,chessPoint_b,color);
     if(isWin==false)
     {
      this.add(chesspoint_white);
      chesspoint_white.setBounds(chessPoint_a*20-7,chessPoint_b*20-7,16,16);
      statusText.setText("白(第"+chessWhiteCount+"步)"+chessPoint_a+" "+chessPoint_b+",请黑棋下子");
      isMouseEnabled=true;
     }
     else
     {
      chessthread.sendMessage("/"+chessPeerName+" /victory "+color);
      this.add(chesspoint_white);
      chesspoint_white.setBounds(chessPoint_a*20-7,chessPoint_b*20-7,16,16);
      chessVictory(-1);
      isMouseEnabled=true;
     }
    }
   }


      public void mousePressed(MouseEvent e)
      {
    if (e.getModifiers()==InputEvent.BUTTON1_MASK)
    {
     chessPoint_x=(int)e.getX();
     chessPoint_y=(int)e.getY();
     int a=(chessPoint_x+10)/20,b=(chessPoint_y+10)/20;
     if(chessPoint_x/20<2||chessPoint_y/20<2||chessPoint_x/20>19||chessPoint_y/20>19)
     {}
     else
     {
      chessPaint(a,b,chessColor);
     }
   }
  }

  public void mouseReleased(MouseEvent e){}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}

  public void actionPerformed(ActionEvent e)
  {

  }
}


class chessPoint_black extends Canvas implements MouseListener
{
 chessPad chesspad=null;
 chessPoint_black(chessPad p)
 {
  setSize(20,20);
  chesspad=p;
  addMouseListener(this);
 }

 public void paint(Graphics g)
 {
  g.setColor(Color.black);
  g.fillOval(0,0,14,14);
 }

 public void mousePressed(MouseEvent e)
 {
//  if(e.getModifiers()==InputEvent.BUTTON3_MASK)
//  {
//   chesspad.remove(this);
//   chesspad.chessColor=1;
//   chesspad.text_2.setText("");
//   chesspad.text_1.setText("请黑棋下子");
//  }
 }
 public void mouseReleased(MouseEvent e){}
 public void mouseEntered(MouseEvent e) {}
 public void mouseExited(MouseEvent e) {}
 public void mouseClicked(MouseEvent e) {}
}


class chessPoint_white extends Canvas implements MouseListener
{
 chessPad chesspad=null;
 chessPoint_white(chessPad p)
 {
  setSize(20,20);
  addMouseListener(this);
  chesspad=p;
 }

 public void paint(Graphics g)
 {
  g.setColor(Color.white);
  g.fillOval(0,0,14,14);
 }

 public void mousePressed(MouseEvent e)
 {
//  if(e.getModifiers()==InputEvent.BUTTON3_MASK)
//  {
//   chesspad.remove(this);
//   chesspad.chessColor=-1;
//   chesspad.text_2.setText("请白旗下子");
//   chesspad.text_1.setText("");
//  }
 }
 public void mouseReleased(MouseEvent e){}
 public void mouseEntered(MouseEvent e) {}
 public void mouseExited(MouseEvent e) {}
 public void mouseClicked(MouseEvent e)
 {
//  if(e.getClickCount()>=2)
//  chesspad.remove(this);
 }
}
