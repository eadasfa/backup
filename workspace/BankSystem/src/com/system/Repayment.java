package com.system;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

public class Repayment extends JPanel implements ActionListener{
	
	Vector v = new Vector();
	Frame frame;
	SqlHelp sp=null;
	ResultSet rs=null;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	JTextField jft1,jft2,jft3,jft4,jft5;
	JLabel jl1,jl2,jl3,jl4,jl5;
	String loan_number,user;

	public Repayment(String user,Frame frame) {
		this.frame=frame;
		this.user=user;
		v = new Vector();
		jb1 = new JButton("还款");
		jb1.addActionListener(this);
		jb2 = new JButton("清除");
		jb2.addActionListener(this);
		
		jl1 = new JLabel("余       额");
		jl2 = new JLabel("贷       款");
		jl3 = new JLabel("已       还");
		jl4 = new JLabel("未       还");
		jl5 = new JLabel("还款数额");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jft1 = new JTextField(10);
		jft1.setEditable(false);
		jft2 = new JTextField(10);
		jft2.setEditable(false);
		jft3 = new JTextField(10);
		jft3.setEditable(false);
		jft4 = new JTextField(10);
		jft4.setEditable(false);
		jft5 = new JTextField(10);
		sp = new SqlHelp();
		v.add(user);
		
		this.upDate();
		//将贷款账号加入Vector
		
		
		jp1.add(jl1);
		jp1.add(jft1);
		jp2.add(jl2);
		jp2.add(jft2);
		jp3.add(jl3);
		jp3.add(jft3);
		jp4.add(jl4);
		jp4.add(jft4);
		jp5.add(jl5);
		jp5.add(jft5);
		jp6.add(jb1);
		jp6.add(jb2);
		this.setLayout(new GridLayout(6,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			String num = jft5.getText().trim();
			if(num.length()==0){
				JOptionPane.showMessageDialog(this, "请输入还款金额");
			}else{
				PasswdOk yes = new PasswdOk(frame,user);
				if(yes.getFlag())
				{
					String sql="EXECUTE Repayment  ?,?,?";
					v.add(this.loan_number);
					v.add(num);
					if(!sp.sqlUpdate(sql, v))
					{
						JOptionPane.showMessageDialog(this, "还款失败");
						v.remove(2);
						v.remove(1);
					}else{
						v.remove(2);
						v.remove(1);
						this.upDate();
					}
					
				}
				jft5.setText(null);
			}
			
			
		}else if(e.getSource()==jb2)
		{
			jft5.setText(null);
		}
	}
	public void upDate()
	{
		//查询余额
		String sql ="select L.amount,A.balance ,B.loan_number "+
					 "from loan as L,depositor AS D,borrower AS B , "+
					 "account AS A where D.account_number = ? "+
				   	 "AND D.customer_name=B.customer_name "+ 
					 "AND B.loan_number=L.loan_number "+
					 "AND A.account_number=D.account_number";
		
		
		rs = sp.sqlQuery(sql, v);
		int balance=0,loan=0;
		try {
			if(rs.next())
			{	
				loan = rs.getInt(1);
				balance = rs.getInt(2);
				this.loan_number = rs.getString(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
				try {
					if(rs!=null) rs.close();
					sp.sqlClose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//查询贷款
		sql = "SELECT P.sum_a FROM  depositor AS D,borrower AS B ,"+
				"(SELECT SUM(payment_amount),loan_number "+
				" FROM payment GROUP BY loan_number) AS "+
				" P(sum_a,loan_number)where D.account_number=? "+
				"AND D.customer_name = B.customer_name "+
				"AND B.loan_number=P.loan_number";
		rs = sp.sqlQuery(sql, v);
		int all=0;
		try {
			if(rs.next())
			{	
				all = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				if(rs!=null) rs.close();
				sp.sqlClose();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		jft1.setText(String.valueOf(balance));
		jft2.setText(String.valueOf(loan));
		jft3.setText(String.valueOf(all));
		jft4.setText(String.valueOf(loan-all));
	}

}
