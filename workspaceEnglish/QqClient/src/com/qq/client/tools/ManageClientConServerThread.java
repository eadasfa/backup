/**
 * 这是一个管理客户端与服务器保持通讯的线程类
 */
package com.qq.client.tools;
import java.util.*;
public class ManageClientConServerThread {
	
	public static HashMap hm = new HashMap<String, ClientConServerThread>();

	//把创建好的ManageClientConServerThread放入hm
	
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId,ccst);
	}
	public static ClientConServerThread getClientConServerThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}
}
