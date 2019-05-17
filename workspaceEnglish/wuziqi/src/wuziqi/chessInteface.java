package wuziqi;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class userPad extends Panel
{
 List userList=new List(10);

 userPad()
 {
  setLayout(new BorderLayout());

  for(int i=0;i<50;i++)
  {
   userList.add(i+"."+"没有用户");
  }
  add(userList,BorderLayout.CENTER);

 }

}

class chatPad extends Panel
{
 TextArea chatLineArea=new TextArea("",18,30,TextArea.SCROLLBARS_VERTICAL_ONLY);

 chatPad()
 {
  setLayout(new BorderLayout());

  add(chatLineArea,BorderLayout.CENTER);
 }

}

 

class controlPad extends Panel
{
 Label IPlabel=new Label("IP",Label.LEFT);
 TextField inputIP=new TextField("localhost",10);
 Button connectButton=new Button("连接主机");
 Button creatGameButton=new Button("建立游戏");
 Button joinGameButton=new Button("加入游戏");
 Button cancelGameButton=new Button("放弃游戏");
 Button exitGameButton=new Button("关闭程序");

 controlPad()
 {
  setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.pink);

  add(IPlabel);
  add(inputIP);
  add(connectButton);
  add(creatGameButton);
  add(joinGameButton);
  add(cancelGameButton);
  add(exitGameButton);
 }

}

class inputPad extends Panel
{
 TextField inputWords=new TextField("",40);
 Choice userChoice=new Choice();

 inputPad()
 {
  setLayout(new FlowLayout(FlowLayout.LEFT));
  for(int i=0;i<50;i++)
  {
   userChoice.addItem(i+"."+"没有用户");
  }
  userChoice.setSize(60,24);
  add(userChoice);
  add(inputWords);
 }
}
