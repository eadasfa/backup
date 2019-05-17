/*
 * 这是我的stu表模型
 */
package test3;

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
	public void init(String aim,Vector seek)
	{	
		String sql=null;
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
				if(aim.equals("Update")){
					sql = "update test1 set name = ?,sex=?,adress=?,"+
							"age=?,major=? where id = ?";
				}else if(aim.equals("AddStu")){
					sql = "insert into test1 values(?,?,?,?,?,?)";
				}
				ps = ct.prepareStatement(sql);
				ps.setString(1, seek.get(0).toString());
				ps.setString(2, seek.get(1).toString());
				ps.setString(3, seek.get(2).toString());
				ps.setInt(4, Integer.parseInt(seek.get(3).toString()));
				ps.setString(5, seek.get(4).toString());
				ps.setString(6, seek.get(5).toString());
				
				ps.executeUpdate();
			}else if(aim.equals("Query")||aim.equals("Delete"))
			{
				if(seek.size()==0)
				{
					sql = "select * from test1";
				}else if(seek.size()==1&&aim.equals("Query")){
					sql = "select * from test1 where name = ?";
				}
				if(aim.equals("Delete")){
					ps = ct.prepareStatement("delete test1 where id= ?");
					ps.setString(1, seek.get(0).toString());
					ps.executeUpdate();
					sql = "select * from test1";
				}
				ps=ct.prepareStatement(sql);
				if(seek.size()==1&&aim.equals("Query")){
					ps.setString(1, seek.get(0).toString());
				}
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
				if(rs!=null)  rs.close();
				if(ps!=null)  ps.close();
				if(ct!=null)  ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	//通过传递的SQL来获得数据模型
	public StuModel(String aim,Vector seek)
	{
		this.init(aim, seek);
	}
	//做一个构造函数，用于初始化数据模型
	public StuModel()
	{
		Vector seek = new Vector();
		this.init("Query",seek);
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
