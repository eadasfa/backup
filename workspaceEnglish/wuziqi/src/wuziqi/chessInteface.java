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
   userList.add(i+"."+"û���û�");
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
 Button connectButton=new Button("��������");
 Button creatGameButton=new Button("������Ϸ");
 Button joinGameButton=new Button("������Ϸ");
 Button cancelGameButton=new Button("������Ϸ");
 Button exitGameButton=new Button("�رճ���");

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
   userChoice.addItem(i+"."+"û���û�");
  }
  userChoice.setSize(60,24);
  add(userChoice);
  add(inputWords);
 }
}
