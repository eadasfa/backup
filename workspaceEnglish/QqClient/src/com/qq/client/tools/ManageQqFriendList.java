/**
 * 管理好友、黑名单。。。界面
 */


package com.qq.client.tools;
import java.io.*;
import java.util.*;

import com.qq.client.view.QqFriendList;

public class ManageQqFriendList {
	private static HashMap hm = new HashMap<String,QqFriendList>();
	public static void addQqFriendList(String qqid,QqFriendList qqlist)
	{
		hm.put(qqid, qqlist);
	}
	public static QqFriendList getQqFriendList(String qqid)
	{
		return (QqFriendList)hm.get(qqid);
	}
}
