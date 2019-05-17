/**
 * 这是用户数据表数据模型，用他完成对用户各种操作
 * 主要编写业务操作
 */
package com.mhl.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mhl.db.*;

public class UserModel {
	
	public UserModel() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param uid 用户编号		
	 * @param p 用户密码
	 * @return 用户职位，如果该用户不存在 则返回空
	 */
	public String checkUser(String uid,String p)
	{
		
		SqlHelper sp=null;
		String zhiwei = "";
		try {
			//组织sql语句和参数列表
			String sql = "select rszl.zhiwei from login,rszl where  " +
					"login.empid = rszl.empid and login.empid = ?  " +
					"and login.passwd = ?";
			String paras[]={uid,p};
			sp = new SqlHelper();
			ResultSet rs = sp.query(sql, paras);
			if(rs.next())
			{
				zhiwei = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			
			try {
				sp.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return zhiwei;
	}

}
