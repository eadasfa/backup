package com.system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Check {

	public Check() {
		// TODO Auto-generated constructor stub
	}
	public Boolean pass(Vector v)
	{
		Boolean b =true;
		SqlHelp sp = new SqlHelp();
		ResultSet rs = null;
		try {
			String sql = "select * from login where account_number = ? and passwd = ?";
			rs = sp.sqlQuery(sql, v);
			if(rs.next())
			{
				return b;
			}else 
			{
				b=false;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			b=false;
		}finally{
			try {
				rs.close();
				sp.sqlClose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		return b;
	}
}
