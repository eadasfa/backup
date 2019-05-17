/*
 * 这是我的stu表模型
 */
package com.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class StuModel extends AbstractTableModel{

	//rowData用来存放数据
	//columnNames 存放列名
	Vector rowData,columnNames;
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs =null;
	public void init(String aim,String sql)
	{
		//rowData用来存放数据
		//columnNames 存放列名
		rowData = new Vector();
	    columnNames = new Vector();
	    
	    columnNames.add("学号");
	    columnNames.add("姓名");
	    columnNames.add("性别");
	    columnNames.add("年龄");
	    columnNames.add("籍贯");
	    columnNames.add("院系");	

		try{
			
			//加载驱动
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			//得到连接
			ct=DriverManager.getConnection
					("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=aaaaaa","sa","       ");
			//判断目的
			if(aim.equals("AddStu")||aim.equals("Update"))
			{
				ps = ct.prepareStatement(sql);
				ps.executeUpdate();
			}else if(aim.equals("Query")||aim.equals("Delete"))
			{
				if(aim.equals("Delete")){
					ps = ct.prepareStatement(sql);
					ps.executeUpdate();
					sql = "select * from test1";
				}
				ps=ct.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					Vector hang = new Vector();
					hang.add(rs.getString(1));
					hang.add(rs.getString(2));
					hang.add(rs.getString(3));
					hang.add(rs.getInt(4));
					hang.add(rs.getString(5));
					hang.add(rs.getString(6));
					rowData.add(hang);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)  ps.close();
				if(ct!=null)  ct.close();
				if(rs!=null)  rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	//通过传递的SQL来获得数据模型
	public StuModel(String aim,String sql)
	{
		this.init(aim, sql);
	}
	//做一个构造函数，用于初始化数据模型
	public StuModel()
	{
		this.init("Query","select * from test1");
	}

	//得到共有多少行
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	//得到共有多少列
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	//得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}

}
