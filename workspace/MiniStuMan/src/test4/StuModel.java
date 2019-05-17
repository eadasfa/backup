/*
 * 这是我的stu表模型
 */
package test4;

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
	public boolean addStu(Vector seek){
		String sql = "insert into test1 values(?,?,?,?,?,?)";
		SqlHelper hp = new SqlHelper();
		boolean b= hp.updExecute(sql, seek);
		return b;
	}
	public boolean updateStu(Vector seek){
		String sql = "update test1 set name = ?,sex=?,adress=?,"+
				"age=?,major=? where id = ?";
		SqlHelper hp = new SqlHelper();
		boolean b= hp.updExecute(sql, seek);
		return b;
	}
	public boolean deleteStu(Vector seek){
		
		String sql = "delete test1 where id= ?";
		SqlHelper hp = new SqlHelper();
		boolean b= hp.updExecute(sql, seek);
		return b;
	}
	public void queryStu(Vector seek){
		String sql=null;
		if(seek == null||seek.size()==0)
		{
		    sql= "select * from test1";
		}else if(seek.size()==1){
			sql = "select * from test1 where id = ?";
		}	
		
		SqlHelper hp = new SqlHelper();
		rs= hp.Query(sql, seek);
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
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			hp.sqlClose();
		}
		
	}
	
	
	//通过传递的SQL来获得数据模型

	//做一个构造函数，用于初始化数据模型
	public StuModel()
	{
		
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
