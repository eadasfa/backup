/*
 * 需要引入3个  jdbc
 * 
 * PreparedStatement 可以提高执行效率（因为他有预编译功能）
 * PreparedStatement 可以防止SQL注入，但是要求用？赋值的方式
 * 
 * 查询
 */
package com.qi.jdbc;
import java.sql.*;
public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	//PreparedStatement[火箭车]
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs =null;
	try{
		//初始化对象
		//1.加载驱动
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		
		//2.得到连接
		//127.0.0.1表示你要连的数据库的IP地址
		//1433 表示sql server 的默认端口
		ct=DriverManager.getConnection
				("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=aaaaaa","sa","       ");
		ps=ct.prepareStatement("select * from works where salary = ? " +
								"and company_name = ? or employee_name = 'E009'");
		//给问号赋值
		
		ps.setInt(1, 3500);
		ps.setString(2, "CP1");
		//执行【如果是增加，删除，修改executeUpdate（），如果查询executeQuery（）】
		
		rs = ps.executeQuery();
		
		//循环的取出
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
		//关闭资源
		//谁后创建，谁先关闭
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
