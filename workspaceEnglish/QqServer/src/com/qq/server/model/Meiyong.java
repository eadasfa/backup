package com.qq.server.model;
/**
 * �������¹�������ģ�ͣ���ɶԱ�ĸ��ֲ���
 */
/*package com.qq.server.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


import javax.swing.table.*;

import com.qq.server.db.SqlHelper;

public class EmpModel extends AbstractTableModel{

	Vector colums=null;
	Vector rows=null;
	public EmpModel() {
		// TODO Auto-generated constructor stub
	}
	//ͨ��Ա����ɾ��Ա��
	public boolean delEmpById(String empId)
	{
		boolean b = true;
		String sql = "delete from rszl where empid = ? ";
		String []paras = {empId};
		SqlHelper sh = new SqlHelper();
		try {
			b= sh.exeUpdate(sql, paras);
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
		}finally{
			sh.close();
		}
		return b;
	}
	//дһ�����������ڲ�ѯ��Ҫ��ʵ��������Ϣ
	//query������ͨ�ã��޸ģ�ʹ��ͨ��
	public void query(String sql,String paras[])
	{
		//��ʼ��
		this.colums = new Vector();
		
		
		//����SqlHelper
		SqlHelper sh = new SqlHelper();
		ResultSet rs = sh.query(sql, paras);
		
		ResultSetMetaData rsmt=null;
		
		this.rows = new Vector();
		//��rs�������rows
		try {
			//��rs�п��Եõ�һ��ResultSetMetadata
			//�õ��ж�����
			//rsmt ���Եõ��ж����У����ҿ���֪��ÿ�е�����
			rsmt = rs.getMetaData();
			//�õ�����
			for(int i=0;i<rsmt.getColumnCount();i++)
			{	
				this.colums.add(rsmt.getColumnName(i+1));
			}
			while(rs.next())
			{
				Vector temp = new Vector();
				for(int i=0;i<rsmt.getColumnCount();i++)
				{
					temp.add(rs.getString(i+1));
				}
				rows.add(temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sh.close();
		}
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
	

}*/
