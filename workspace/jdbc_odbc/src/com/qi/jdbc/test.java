/*
 * ��ʾʹ��jdbc����odbc��ʹ��
 * 1.��������Դ
 * 2.�ڳ�����ȥ��������Դ
*/
package com.qi.jdbc;
import java.sql.*;
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection ct=null;
		Statement sm=null;
		try{
			//1.��������(����Ҫ��������������ڴ�)
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//2.�õ�����(ָ�����ӵ��ĸ�����Դ,�û���������)
			//Connection ��Դ   ��Ҫ�ر�
			ct=DriverManager.getConnection("jdbc:odbc:mytest","sa","       ");
			//3.����Statement����PreparedStatement
			//Statement��Ҫ���ڷ���sql���
			sm = ct.createStatement();
			//ִ�У�crud���������ݿ⣬�������ݿ⣬ɾ�����ݿ⡣����
			//1.��ʾ���һ�����ݵ�
			//executeUpdate ����ִ��cud���  ɾ��  �޸�
			
			int i=sm.executeUpdate("INSERT INTO works VALUES('E000','CP1',12000)");
			if(i==1){
				System.out.println("OK");
			}else{
				System.out.println("error");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//�ر���Դ
			//˳��˭�󴴽���˭�ȹر�
			try {
				//Ϊ�˳���׳
				if(sm!=null){
				sm.close();
				}
				if(sm!=null){
					ct.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}



