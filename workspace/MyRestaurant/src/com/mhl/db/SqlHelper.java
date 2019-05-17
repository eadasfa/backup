/**
 * 对数据库操作
 */
package com.mhl.db;

import java.sql.*;
import java.util.Vector;

public class SqlHelper {
	
	//定义需要的对象
	PreparedStatement ps =null;
	ResultSet rs = null;
	Connection ct = null;
	String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url = "jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=restaurant";
	String user="sa";
	String passwd = "       ";
	//
	public SqlHelper()
	{
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean exeUpdate(String sql,String []paras)
	{
		boolean b =true;
		try {
			ps = ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			if(ps.executeUpdate()==1){
				b=false;
			}
		} catch (Exception e){
			b=false;
			e.printStackTrace();
		}
		return b;
	}
	public ResultSet query(String sql,String [] paras)
	{
		try {
			ps = ct.prepareStatement(sql);
			for(int i=0;i<paras.length;i++)
			{
				ps.setString(i+1, paras[i]);
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
