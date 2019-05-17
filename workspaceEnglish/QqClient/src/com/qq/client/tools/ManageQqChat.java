package com.qq.client.tools;

import java.util.HashMap;

import com.qq.client.view.QqChat;
public class ManageQqChat {
	private static HashMap hm = new HashMap<String,QqChat>();
	//º”»Î
	public static void addQqChat(String loginIdAndFriendiD,QqChat qqChat)
	{
		hm.put(loginIdAndFriendiD, qqChat);
	}
	public static QqChat getQqChat(String loginIdAndFriendiD)
	{
		return (QqChat)hm.get(loginIdAndFriendiD);
				
	}
}
