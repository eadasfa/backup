/**
 * 数据库操作的类
 */
package com.qq.server.db;
import java.sql.*;
public class SqlHelper {
	String driver ="com.microsoft.jdbc.sqlserver.SQLServerDriver";

	String url = "jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=QqChat";
	//用户名
	String user = "sa";
	//密码
	String passwd = "       ";
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs = null;
	public SqlHelper() {
		try {
			//加载驱动
			Class.forName(driver);
			//建立连接
			ct = DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
	}
	public void close()
	{
		if(rs!=null)
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("数据库关闭失败");
				e.printStackTrace();
			}
		
	}
	//增、删、改
	public boolean exeUpdate(String sql,String []paras)
	{
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			if(ps.executeUpdate()==0)
			{
				 b= false;
			}
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}finally
		{
			this.close();
		}
		return b;
	}
	public ResultSet Query(String sql,String []paras)
	{
		try {
			ps = ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++)
			{
				
				ps.setString(i+1, paras[i]);
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
	
	
}
