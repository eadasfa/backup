/* ��Ҫ����3��
 * 
 * ִ�д��� ɾ�� �������ݿ�  ���� 
 * 
 *
 */
package com.qi.jdbc;
import java.sql.*;
public class test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	//PreparedStatement[�����]
	PreparedStatement ps=null;
	Connection ct=null;
	//ResultSet rs =null;
	try{
		//��ʼ������
		//1.��������
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		
		//2.�õ�����
		ct=DriverManager.getConnection
				("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=aaaaaa","sa","       ");
		//ִ�С���������ӣ�ɾ�����޸�executeUpdate�����������ѯexecuteQuery������
		
		//���
		//ps=ct.prepareStatement("INSERT INTO works VALUES('E016','CP5',99999)");
		
		//ɾ��
		//ps=ct.prepareStatement("delete works where salary = 99999");
		
		//�޸�
		ps=ct.prepareStatement("update works set salary=9 where salary = 99999");
		
		//ɾ�����ݿ�
		//ps=ct.prepareStatement("drop database bbbv ");
		
		//�ָ����ݿ�
		//ps=ct.prepareStatement("restore database aaa from disk = 'D://��Ѷ//�½��ļ���//1319468035//FileRecv//dis.bak'");
		
		//�������ݿ�
		//ps=ct.prepareStatement("backup database aaa to disk = 'D://��Ѷ//�½��ļ���//1319468035//FileRecv//dis.bak'");
		
		//ps=ct.prepareStatement("create database bbbv");
		//boolean i= ps.execute();
		
		int i = ps.executeUpdate();
		
		System.out.println(i);
		
		//ѭ����ȡ��
//		while(rs.next())
//		{
//			String name = rs.getString(1);
//			String C = rs.getString(2);
//			int sal=rs.getInt(3);
//			System.out.println(name+""+C+""+sal);
//			
//		}
//		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try {
		//	rs.close();
			ct.close();
			//ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	}
}
