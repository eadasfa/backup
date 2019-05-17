/**
 * �����û����ݱ�����ģ�ͣ�������ɶ��û����ֲ���
 * ��Ҫ��дҵ�����
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
	 * @param uid �û����		
	 * @param p �û�����
	 * @return �û�ְλ��������û������� �򷵻ؿ�
	 */
	public String checkUser(String uid,String p)
	{
		
		SqlHelper sp=null;
		String zhiwei = "";
		try {
			//��֯sql���Ͳ����б�
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
