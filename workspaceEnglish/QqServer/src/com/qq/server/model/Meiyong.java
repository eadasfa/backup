package com.qq.server.model;
/**
 * 这是人事管理数据模型，完成对表的各种操作
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
	//通过员工号删除员工
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
	//写一个方法，用于查询需要现实的人事信息
	//query方法不通用，修改，使其通用
	public void query(String sql,String paras[])
	{
		//初始化
		this.colums = new Vector();
		
		
		//创建SqlHelper
		SqlHelper sh = new SqlHelper();
		ResultSet rs = sh.query(sql, paras);
		
		ResultSetMetaData rsmt=null;
		
		this.rows = new Vector();
		//把rs结果放入rows
		try {
			//从rs中可以得到一个ResultSetMetadata
			//得到有多少列
			//rsmt 可以得到有多少列，而且可以知道每列的名字
			rsmt = rs.getMetaData();
			//得到列名
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
