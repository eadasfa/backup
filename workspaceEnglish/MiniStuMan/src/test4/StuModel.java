/*
 * �����ҵ�stu��ģ��
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

	//rowData�����������
	//columnNames �������
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
	
	
	//ͨ�����ݵ�SQL���������ģ��

	//��һ�����캯�������ڳ�ʼ������ģ��
	public StuModel()
	{
		
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
