/**
 * ����һ������ͻ��������������ͨѶ���߳���
 */
package com.qq.client.tools;
import java.util.*;
public class ManageClientConServerThread {
	
	public static HashMap hm = new HashMap<String, ClientConServerThread>();

	//�Ѵ����õ�ManageClientConServerThread����hm
	
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId,ccst);
	}
	public static ClientConServerThread getClientConServerThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}
}
