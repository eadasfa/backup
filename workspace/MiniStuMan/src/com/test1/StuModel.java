/*
 * �����ҵ�stu��ģ��
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

	//rowData�����������
	//columnNames �������
	Vector rowData,columnNames;
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs =null;
	public void init(String aim,String sql)
	{
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
	//ͨ�����ݵ�SQL���������ģ��
	public StuModel(String aim,String sql)
	{
		this.init(aim, sql);
	}
	//��һ�����캯�������ڳ�ʼ������ģ��
	public StuModel()
	{
		this.init("Query","select * from test1");
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
