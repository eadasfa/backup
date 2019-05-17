package com.qi.fiveinrow;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 游戏面板
 */
public class GameFrame extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	ChessBoard chessboard = null;
	JButton newgame = null;
	JButton startgame = null;
	JButton change_advancer = null;
	JButton withdraw = null;
	JLabel jl = null;
	Image title=null;
	public GameFrame()
	{
		this.setSize(800,700);
		this.setLayout(null);
		
		chessboard = new ChessBoard();
		chessboard.setBounds(10, 20, 
				Tools.CHESSBOARD_WIDTH,Tools.CHESSBOARD_WIDTH);
		this.add(chessboard);
		newgame = new JButton("new game");
		newgame.setBounds(630, 100, 140, 30);
		newgame.addActionListener(this);
		this.add(newgame);
		startgame = new JButton("start game");
		startgame.setBounds(630, 50, 140, 30);
		startgame.addActionListener(this);
		this.add(startgame);
		
		change_advancer = new JButton("change advancer");
		change_advancer.setBounds(630, 150, 140, 30);
		change_advancer.addActionListener(this);
		this.add(change_advancer);
		
		jl = new JLabel("你先手");
		jl.setFont(new Font("宋体",Font.BOLD,18));
		jl.setBounds(630, 200, 150, 30);
		this.add(jl);
		withdraw = new JButton("withdraw");
		withdraw.setBounds(630, 250, 140, 30);
		withdraw.addActionListener(this);
		this.add(withdraw);
		
		try {
			title = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/title.jpg"));
			this.setIconImage(title);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	//	this.requestFocusInWindow();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newgame)
		{
			chessboard.newGame();
		}else if(e.getSource()==startgame)
		{
			chessboard.startGame();
		}else if(e.getSource()==change_advancer)
		{
			String str = chessboard.changeAdvancer();
			jl.setText(str);
		}else if(e.getSource()==withdraw)
		{
			chessboard.huiqi();
		}
		
	}
}














