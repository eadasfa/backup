package com.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Loan extends JPanel implements Runnable
{
	
	int times=0;
	String s;
	ResultSet rs=null;
	public Loan(String user)
	{
		SqlHelp sp = new SqlHelp();
		String sql = "select  L.amount from loan as L,depositor AS D,borrower AS B "+
						" where D.account_number = ? AND "+
						" D.customer_name=B.customer_name AND B.loan_number=L.loan_number";
		Vector v = new Vector();
		v.add(user);	
		rs = sp.sqlQuery(sql, v);
		try {
			if(rs.next()){
				s=rs.getString(1).toString();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sp.sqlClose();
		}
	}
	public void paint(Graphics g)
	{
		int x=200;
		int y=100;
		super.paint(g);
		
		//提示信息
		//设置字体
		if(times%2==0){
			g.setColor(Color.BLUE);
			Font myFont = new Font("华文新魏",Font.BOLD ,30);
			g.setFont(myFont);
			g.drawString("贷款", x, y);
			g.drawString(s, x, y+50);
		}
		if(times%3==0){
			g.setColor(Color.RED);
			Font myFont = new Font("华文新魏",Font.BOLD ,30);
			g.setFont(myFont);
			g.drawString("贷款", x, y);
			g.drawString(s, x, y+50);
		}
		if(times%4==0){
			g.setColor(Color.GRAY);
			Font myFont = new Font("华文新魏",Font.BOLD ,30);
			g.setFont(myFont);
			g.drawString("贷款", x, y);
			g.drawString(s, x, y+50);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			
			//重画
			this.repaint();
		}
	}
}