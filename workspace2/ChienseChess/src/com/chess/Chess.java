package com.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Chess extends JFrame implements MouseListener,MouseMotionListener
{
	ChessImage chesses = new ChessImage();
	private Image qipan = null;
	private int theDepth=0;//��ǰ���
	private int theBestMove=0;//��õ��߷�
	private int moves[] = new int[Const.mostMoves];
	private int currentPlayer=1;//��ǰ���巽
	private int player = 1;//���ִ��Ϊ��ɫ
	int location = 0;//���ѡ�е�λ��
	int last = 0;
	//��ǰ���λ��
	int x=-10;
	int y=-10;
	/**
	 * �����ʾ
	 */
	//(11,12)
	private int []board = {
			/**
			 * �ڷ�
			 * 1��2�R3��4ʿ5��6��7��
			 */
		//  1   2   3   4   5   6   7   8   9  10  11
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
			0,  1,  2,  3,  4,  5,  4,  3,  2,  1,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  0,  6,  0,  0,  0,  0,  0,  6,  0,  0,
			0,  7,  0,  7,  0,  7,  0,  7,  0,  7,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			//���Ӻ���
			/**
			 * �췽
			 * 9��10�R11��12ʿ11��14��15��
			 */
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0, 15,  0, 15,  0, 15,  0, 15,  0, 15,  0,
			0,  0, 14,  0,  0,  0,  0,  0, 14,  0,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
			0,  9, 10, 11, 12, 13, 12, 11, 10,  9,  0,
			0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
	};
	//��0��ʼ
	public int getXFromLocation(int location)
	{
		return location%11-1;
	}
	public int getYFromLocation(int location)
	{
		return location/11-1;
	}
	public int getXYFromLocation(int x,int y)
	{
		return (y+1)*11+x+1;
	}
	//�ı䵱ǰ���巽
	public void changePlayer()
	{
		currentPlayer = 1-currentPlayer;
	}
	/**
	 * 
	 * @param chess����
	 * @param player��ǰ����Ϊ��ɫ���ɫ
	 * @return
	 */
	public boolean isMyChess(int chess,int player)
	{
		if(chess==0)
			return false;
		
		return (chess&8)==player*8;
	}
	/**
	 * �����߷�
	 */
	//������ʼ��λ�ã���Ŀ��λ�������߷�
	public int composeMove(int from,int to)
	{
		return from*board.length+to;
	}
	//���߷�����  ���
	public int generateMoveFrom(int move)
	{
		return move/board.length;
	}
	//���߷����� Ŀ�ĵ�
	public int generateMoveTo(int move)
	{
		return move%board.length;
	}
	public void addChess(int location,int chess)
	{
		board[location]=chess;
	}
	public void removeChess(int location)
	{
		board[location]=0;
	}
	/**
	 * @param moves �������ɵ��߷�
	 * @return	�����߷�����Ŀ
	 */
	public int generateAllMoves(int moves[])
	{
		int count=0;
		int from=0;
		int to = 0;
		int enemy = 1-currentPlayer;
		for(int location=0;location<board.length;location++)
		{
			//�������е����ӣ��ҵ���ǰ���巽������
			if(isMyChess(board[location], currentPlayer))
			{
				//����ԭ�ȵ�λ��
				from = location;
				//���ӵĸ������
				switch(board[from]-currentPlayer*8) {
				case 1://܇
					outer1:
					for(int i=0;i<4;i++)
					{	
						for(int j=0;j<9;j++)
						{
							to = Const.juMoves[i][j]+from;
							if(Const.inBoard[to]==0||
									isMyChess(board[to], currentPlayer))
								continue outer1;
							else {
								moves[count++]=composeMove(from, to);
								if(isMyChess(board[to], enemy))
								{
									continue outer1;
								}
								
							}
						}
					}
					break;
				case 2://�R
					
					for(int i=0;i<4;i++)
					{	
						//����
						int barried= from+Const.maMoves[i];
						if(Const.inBoard[barried]==1&&
								board[barried]==0)
						{
						
							for(int j=2;j<=3;j++)
							{
								if(i<2)
									to = from + Const.maMoves[j]
											+2*Const.maMoves[i];
								else {
									int t=(j-2)==0?-1:1;
									to = from + Const.maMoves[i+2]
											+t;
								}
								if(Const.inBoard[to]==0||
										isMyChess(board[to], currentPlayer))
									continue;
								else {
									moves[count++]=composeMove(from, to);
								}
							}
							
						}
					}
					break;
				case 3://��
						for(int i=0;i<4;i++)
						{
							int eye = Const.xiangMoves[i]+from;
							if(Const.inHome[currentPlayer][eye]==1
									&&board[eye]==0)
							{
								to = Const.xiangMoves[i+4]+from;
								if(Const.inHome[currentPlayer][to]<=0||
										isMyChess(board[to], currentPlayer))
									continue;
								else {
									moves[count++]=composeMove(from, to);
								}
							}
							
						}
					break;
				case 4://ʿ
					for(int i=0;i<Const.shiMoves.length;i++)
					{
						to = Const.shiMoves[i]+from;
						if(Const.inTian[currentPlayer][to]==0||
								isMyChess(board[to], currentPlayer))
							continue;
						else {
							moves[count++]=composeMove(from, to);
						}
					}
					break;
				case 5://��
					for(int i=0;i<Const.jiangMoves.length;i++)
					{
						to = Const.jiangMoves[i]+from;
						if(Const.inTian[currentPlayer][to]==0||
								isMyChess(board[to], currentPlayer))
							continue;
						else {
							if(!jinangIsInALine(from))
								moves[count++]=composeMove(from, to);
						}
					}
					break;
				case 6://��
					outer1:
					for(int i=0;i<4;i++)
					{	
						int k=0;//��¼�м��м�������
						for(int j=0;j<9;j++)
						{
							to = Const.paoMoves[i][j]+from;
							if(Const.inBoard[to]==0)//���˷�Χ����һ��
								continue outer1;
							else
							{
								if(k==0)
								{
									if(board[to]>0) {
										k++;
										continue ;
									}
									else {
										moves[count++]=composeMove(from, to);
									}
								}else if(k==1) //�м���һ������
								{
									if(isMyChess(board[to], enemy))
									{
										moves[count++]=composeMove(from, to);
										continue outer1;
									}
									
								}
							}
							
						}
					}
					break;
				case 7://��
					for(int i=0;i<Const.zuMoves.length;i++)
					{
						//��ֹ����
						if(Const.zuMoves[i]!=Const.inHome[currentPlayer][10])
						{
							to = Const.zuMoves[i]+from;
							if(Const.inBoard[to]==0||
									isMyChess(board[to], currentPlayer))
								continue;
							else {
								if(Const.inHome[currentPlayer][to]==1&&
										Math.abs(to-from)==1)
									continue;
								else {
									moves[count++]=composeMove(from, to);
								}
							}
								
						}
					}
					break;
				}
			}
		}
		
		return count;//�����߷���Ŀ
	}
	public boolean jinangIsInALine(int location)
	{
		boolean flag = false;
		int k=-Const.inHome[currentPlayer][10];
		for(int i=1;Const.inBoard[i*k+location]==1;i++)
		{
			if(board[i*k+location]-8*currentPlayer==5)
				flag = true;
		}
		return flag;
	}
	/**
	 * @param beAte���汻�Ե�����
	 * @param move�߷�
	 * @return
	 */
	public boolean makeOneMove(int beAte[],int move)
	{
		boolean flag = false;
		int count = generateAllMoves(moves);
		
		for(int i=0;i<count;i++)
		{
			if(move==moves[i])
			{
				flag = true;
				break;
			}
		}
		if(flag)
		{
			int from = generateMoveFrom(move);
			int to = generateMoveTo(move);
			last = to;
			beAte[0]=board[to];//�ȱ��汻�Ե�������
			addChess(to, board[from]);//�ƶ�����
			removeChess(from);//�Ƴ�ԭ��������
			this.changePlayer();//����ɹ�����������˫��
		}	
		return flag;
	}
	public void undoOneMove(int beAte[],int move)
	{
		int from = this.generateMoveFrom(move);
		int to = this.generateMoveTo(move);
		addChess(from, board[to]);//�ָ�������ԭ��λ�õ�����
		addChess(to, beAte[0]);
		this.changePlayer();
	}
	/**
	 *��������
	 */
	public boolean isPlayerDead()
	{
		boolean flag = true;
		int move[]= new int[Const.mostMoves];
		int theKing = currentPlayer*8+Const.JIANG;
		int k = generateAllMoves(move);
		for(int i=0;i<k;i++)
		{
			if(board[generateMoveFrom(move[i])]==theKing)
			{
				flag = false;
				break;
			}	
		}
		return flag;
	}
	/**
	 * �ڴ˲���ʱ���Ѿ��ı䵱ǰ���巽�ˣ���ֻ��Ҫ�ж�Ŀǰ�Ѿ��ı��������Ƿ�������
	 * ����֪���ı�֮ǰ�������Ƿ�ʤ��
	 * @return ���ص�ǰ���巽�Ƿ�ʤ��
	 */
	public boolean isWin()
	{
		return isPlayerDead();
	}
	/**
	 * ������������
	 * @return ��ǰ���巽�ļ�ֵ��ȥ�з����巽�ļ�ֵ
	 */
	public int evaluatePosition()
	{
		int blackValue = 0;
		int redValue = 0;
		for(int i=0;i<board.length;i++)
		{
			if(board[i]>8) {
				//����ļ�ֵ
				redValue+=Const.chessValue[board[i]-8];
			}else
				blackValue = Const.chessValue[board[i]];
		}
		int value = redValue - blackValue;
		return currentPlayer==1?value:-value;
	}
	int dd=0;
	public int alphaBetaSearch(int alpha,int beta,int depth)
	{
		if(dd<=7)
		{
			System.out.println("Enter alphaBetaSearch "+(dd++));
		}
		int value=0;
		if(depth == 0)
		{
			return evaluatePosition() ;
		}
		if(isPlayerDead())//�����ǰ���巽����
		{
			return -Const.INFINITY_VALUE+theDepth;//������С�ģ���������ⲽ
		}
		int allMoves[]=new int[Const.mostMoves];
		int beAte[]=new int[1];
		int count = this.generateAllMoves(allMoves);
		for(int i=0;i<count;i++)
		{
			//��һ���壬����һ���ж��ⲽ�������
			if(this.makeOneMove(beAte, allMoves[i]))
			{
				this.theDepth++;
				value = -alphaBetaSearch(-beta, -alpha, depth-1);
				this.undoOneMove(beAte, allMoves[i]);
				this.theDepth--;
				if(value>=beta)
				{
					return beta;//�����һ��õ���ֵ������Χ ������beta;
				}
				else if(value>alpha)
				{
					alpha = value;
					if(theDepth==0)
					{//����Ǹ��ڵ�
						this.theBestMove=allMoves[i];//������ڵ�
					}
				}
					
			}
		}
		
		return alpha;
	}
	public void computerThink()
	{
//		System.out.println("Enter computerThink!");
		this.theDepth=0;
		int beAte[]=new int[1];
		this.alphaBetaSearch(-Const.INFINITY_VALUE, Const.INFINITY_VALUE,
				Const.SEARCH_DEEPTH);

		this.makeOneMove(beAte, theBestMove);
		if(!isWin())
		{
			this.repaint();
		}
		else {
			
		}
	}
	public Chess()
	{

		try {
			qipan = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/qipan.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//	this.getContentPane().setBackground(Color.gray);
		this.setSize(800,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		BufferedImage bi = new BufferedImage(
				700, 800, 2);
		this.drawChessBoard(g,bi);
//		super.paint(g);
		g.drawImage(bi, -50, -40, 700, 800, this);
		

	}
	public void drawChessBoard(Graphics g,BufferedImage bi2)
	{
		
		BufferedImage bi = new BufferedImage(
				700, 800, 2);
		Graphics gg = bi.createGraphics();
		Graphics gg2 = bi2.createGraphics();
		gg.setColor(Color.white);
		gg.fillRect(0, 0, 550, 700);
		gg.setColor(Color.BLACK);
		gg.drawImage(qipan, 0, 0, 630, 700, this);
		
		//ѭ��������������
		for(int i=0;i<board.length;i++)
		{
			if(board[i]>0)
			{
				drawChess(gg, i);
			}
		}
		gg2.drawImage(bi, 0, 0, 700, 800, this);
		
	}
	public void drawChess(Graphics g,int location) 
	{
		int x0=47+(int)(52.5*getXFromLocation(location))+53;
		int y0=55+(int)(54.8*getYFromLocation(location))+43;
		Image chess = null;
		if(location ==this.location||this.last==location)
			chess = ChessImage.getImage(board[location]);
		else chess = ChessImage.chess[board[location]];
	
		g.drawImage(chess, x0-25, y0-25, 50, 50, this);
		
	}
	public static void main(String[] args) {
		new Chess();
	}
	
	public void mouseClicked(MouseEvent e) {
//		System.out.println(x+" "+y+" "+getXYFromLocation(x, y));
		
		int k = this.getXYFromLocation(x, y);
		if(isMyChess(board[k], player))
		{
			location = this.getXYFromLocation(x, y);
			this.repaint();
		}else if(location>0){
			int to = this.getXYFromLocation(x, y);
			int move = this.composeMove(location, to);
			int beAte[]=new int[1];
			
			if(!this.makeOneMove(beAte, move))
			{
				JOptionPane.showMessageDialog(this, "���岻�Ϸ���");
			}
			else {
				this.repaint();
				location = 0;
				
//				this.changePlayer();
//				System.out.println("Computer is thinking!");
				this.computerThink();
    		
			}
		}

		
	}
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		double x0 = (e.getX()-47)/52.5;
		double y0 = (e.getY()-55)/54.8;
		x = (int) x0; y = (int) y0;
		x=Math.abs(x0-x)>0.5?x+1:x;
		y=Math.abs(y0-y)>0.5?y+1:y;
		if(x<0) 	x=0;
		if(x>8)		x=8;
		if(y<0)		y=0;
		if(y>9)		y=9;
	}
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
