/*
 * 演示使用jdbc——odbc桥使用
 * 1.配置数据源
 * 2.在程序中去连接数据源
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
			//1.加载驱动(把需要的驱动程序加入内存)
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//2.得到连接(指定连接到哪个数据源,用户名和密码)
			//Connection 资源   需要关闭
			ct=DriverManager.getConnection("jdbc:odbc:mytest","sa","       ");
			//3.创建Statement或者PreparedStatement
			//Statement主要用于发送sql语句
			sm = ct.createStatement();
			//执行（crud，创建数据库，备份数据库，删除数据库。。）
			//1.演示添加一条数据到
			//executeUpdate 可以执行cud添加  删除  修改
			
			int i=sm.executeUpdate("INSERT INTO works VALUES('E000','CP1',12000)");
			if(i==1){
				System.out.println("OK");
			}else{
				System.out.println("error");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭资源
			//顺序：谁后创建，谁先关闭
			try {
				//为了程序健壮
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



