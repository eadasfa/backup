package test4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SqlHelper {
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs =null;
	String driver =  "com.microsoft.jdbc.sqlserver.SQLServerDriver" ;
	String url =  "jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=aaaaaa";
	String user = "sa";
	String passwd = "       ";
	public SqlHelper()
	{
		
	}
	
	public boolean updExecute(String sql, Vector seek)
	{
		boolean b=true;
		this.sqlLink();
		try{
			ps = ct.prepareStatement(sql);
			for(int i=0;i<seek.size();i++)
			{
				ps.setString(i+1, seek.get(i).toString());
			}
			if(ps.executeUpdate()==0)
			{
				b=false;
			}
			
		}catch(Exception e){
			b=false;
			e.printStackTrace();
		}finally{
			this.sqlClose();
		}
		return b;
	}
	public ResultSet Query(String sql, Vector seek)
	{
		boolean b=true;
		this.sqlLink();
		try {
			ps = ct.prepareStatement(sql);
			if(seek == null||seek.size()==0)
			{
				rs=ps.executeQuery();
			}else if(seek.size()==1){
				ps.setString(1, seek.get(0).toString());
				rs=ps.executeQuery();
			}	
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//连接数据库
	public void sqlLink()
	{
		try {
			Class.forName(driver);
			ct=DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//关闭资源
	public void sqlClose()
	{
		try {
			if(rs!=null)  rs.close();
			if(ps!=null)  ps.close();
			if(ct!=null)  ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
