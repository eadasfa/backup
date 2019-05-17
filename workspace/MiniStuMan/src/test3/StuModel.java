/*
 * �����ҵ�stu��ģ��
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

	//rowData�����������
	//columnNames �������
	Vector rowData,columnNames;
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs =null;
	public void init(String aim,Vector seek)
	{	
		String sql=null;
		//rowData�����������
		//columnNames �������
		rowData = new Vector();
	    columnNames = new Vector();
	    
	    columnNames.add("ѧ��");
	    columnNames.add("����");
	    columnNames.add("�Ա�");
	    columnNames.add("����");
	    columnNames.add("����");
	    columnNames.add("Ժϵ");	

		try{
			
			//��������
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			//�õ�����
			ct=DriverManager.getConnection
					("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=aaaaaa","sa","       ");
			//�ж�Ŀ��
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
	//ͨ�����ݵ�SQL���������ģ��
	public StuModel(String aim,Vector seek)
	{
		this.init(aim, seek);
	}
	//��һ�����캯�������ڳ�ʼ������ģ��
	public StuModel()
	{
		Vector seek = new Vector();
		this.init("Query",seek);
	}

	//�õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	//�õ����ж�����
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	//�õ�ĳ��ĳ�е�����
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
