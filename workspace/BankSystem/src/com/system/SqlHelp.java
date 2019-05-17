package com.system;
import java.sql.*;
import java.util.Vector;
public class SqlHelp {
	
	String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String url = "jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=BANKING";
	String user = "sa";
	String passwd = "       ";
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	public SqlHelp() {
		// TODO Auto-generated constructor stub
	}
	public ResultSet sqlQuery(String sql, Vector v)
	{
		this.sqlOpen();
		try {
			ps = ct.prepareStatement(sql);
			if(v!=null){
				for(int i=0;i<v.size();i++)
				{
					ps.setString(i+1, v.get(i).toString());
				}
			}
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public Boolean sqlUpdate(String sql,Vector v)
	{
		Boolean b = true;
		this.sqlOpen();
		try {
			ps = ct.prepareStatement(sql);
			if(v!=null){
			
				for(int i=0;i<v.size();i++)
				{
					ps.setString(i+1, v.get(i).toString());
				}
			}
			if(ps.executeUpdate()==0)
			{
				b = false;
			}
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}finally{
			this.sqlClose();
		}
		return b;
	}
	
	public void sqlOpen()
	{
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url,user,passwd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sqlClose()
	{
		
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
