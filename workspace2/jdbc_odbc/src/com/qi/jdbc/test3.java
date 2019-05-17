/* 需要引入3个
 * 
 * 执行创建 删除 备份数据库  返回 
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

	
	//PreparedStatement[火箭车]
	PreparedStatement ps=null;
	Connection ct=null;
	//ResultSet rs =null;
	try{
		//初始化对象
		//1.加载驱动
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		
		//2.得到连接
		ct=DriverManager.getConnection
				("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=aaaaaa","sa","       ");
		//执行【如果是增加，删除，修改executeUpdate（），如果查询executeQuery（）】
		
		//添加
		//ps=ct.prepareStatement("INSERT INTO works VALUES('E016','CP5',99999)");
		
		//删除
		//ps=ct.prepareStatement("delete works where salary = 99999");
		
		//修改
		ps=ct.prepareStatement("update works set salary=9 where salary = 99999");
		
		//删除数据库
		//ps=ct.prepareStatement("drop database bbbv ");
		
		//恢复数据库
		//ps=ct.prepareStatement("restore database aaa from disk = 'D://腾讯//新建文件夹//1319468035//FileRecv//dis.bak'");
		
		//备份数据库
		//ps=ct.prepareStatement("backup database aaa to disk = 'D://腾讯//新建文件夹//1319468035//FileRecv//dis.bak'");
		
		//ps=ct.prepareStatement("create database bbbv");
		//boolean i= ps.execute();
		
		int i = ps.executeUpdate();
		
		System.out.println(i);
		
		//循环的取出
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
