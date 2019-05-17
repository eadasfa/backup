package com.qq.server.model;

import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.qq.server.db.SqlHelper;

public class UserModel extends AbstractTableModel{

	Vector colums=null;
	Vector rows=null;
	SqlHelper sh =null;
	
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean addQqUser(String []paras)
	{
		sh = new SqlHelper();
		String sql = "EXECUTE AddUser ?,?,?,?,?,?,?,?,?,?";
		return sh.exeUpdate(sql, paras);
	}
	/**
	 * 
	 * @param paras
	 * @return 如果存在 返回true 否则返回false
	 */
	public boolean queryQqExist(String []paras)
	{
		boolean b = false;
		try {
			sh = new SqlHelper();
			String sql = "select * from QqLogin where qqNumber = ? ";
			ResultSet rs = sh.Query(sql, paras);
			if(rs.next()){
				b=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}finally
		{
			sh.close();
		}
		return b;
		
	}
	public Vector<String> getQqFriend(String []paras)
	{
		Vector<String> qq = new Vector<String>();
		try {
			sh = new SqlHelper();
			String sql = "select qqFriendNumber from QqFriendList where qqNumber = ? ";
			ResultSet rs = sh.Query(sql, paras);
			while(rs.next()){
				qq.add(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			sh.close();
		}
		return qq;
		
	}
	public boolean queryLoginCheck(String []paras)
	{
		boolean b = false;
		try {
			sh = new SqlHelper();
			String sql = "select * from QqLogin where qqNumber = ? and qqPasswd = ? ";
			ResultSet rs = sh.Query(sql, paras);
			if(rs.next()){
				b=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
			e.printStackTrace();
		}finally{
			sh.close();
		}
		return b;
		
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colums.size();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)rows.get(rowIndex)).get(columnIndex);
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return this.colums.get(column).toString();
	}
	
}