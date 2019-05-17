/*
 * ��Ҫ����3��  jdbc
 * 
 * PreparedStatement �������ִ��Ч�ʣ���Ϊ����Ԥ���빦�ܣ�
 * PreparedStatement ���Է�ֹSQLע�룬����Ҫ���ã���ֵ�ķ�ʽ
 * 
 * ��ѯ
 */
package com.qi.jdbc;
import java.sql.*;
public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	//PreparedStatement[�����]
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs =null;
	try{
		//��ʼ������
		//1.��������
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		
		//2.�õ�����
		//127.0.0.1��ʾ��Ҫ�������ݿ��IP��ַ
		//1433 ��ʾsql server ��Ĭ�϶˿�
		ct=DriverManager.getConnection
				("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=aaaaaa","sa","       ");
		ps=ct.prepareStatement("select * from works where salary = ? " +
								"and company_name = ? or employee_name = 'E009'");
		//���ʺŸ�ֵ
		
		ps.setInt(1, 3500);
		ps.setString(2, "CP1");
		//ִ�С���������ӣ�ɾ�����޸�executeUpdate�����������ѯexecuteQuery������
		
		rs = ps.executeQuery();
		
		//ѭ����ȡ��
		while(rs.next())
		{
			String name = rs.getString(1);
			String C = rs.getString(2);
			int sal=rs.getInt(3);
			System.out.println(name+""+C+""+sal);
			
		}
		
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		//�ر���Դ
		//˭�󴴽���˭�ȹر�
		try {
			rs.close();
			ps.close();
			ct.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	}
}
