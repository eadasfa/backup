package com.qq.client.model;

import com.qq.common.*;
import com.qq.common.*;
public class QqClientUser {
	
	public boolean checkUser(User u)
	{
		return new QqClientConServer().SendLoginInfoToServer(u);
	}
	public String sendRegisterMessage(Message m)
	{
		return new QqClientConServer().SendRegisterMessageToServer(m);
	}
}
