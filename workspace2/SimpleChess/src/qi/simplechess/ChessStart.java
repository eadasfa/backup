/**
 * ������:��������
 * ʱ�䣺2017.12.17
 */
package qi.simplechess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
/**
 * ���̿�ʼ
 */
public class ChessStart extends JFrame implements MouseListener,MouseMotionListener{
	int x0=0;
	int y0=30;
	int MAX_GEN_MOVES=100;
	int mouseLocation=0;
	int last=-100;
	public static void main(String[] args) {
		ChessStart cs=new ChessStart();
	
		
	}
	//�߳̿���
	boolean thread=false;
	//��˸����
	int time=0;
	int playerFrom=0;
	Image qipan = null;
	/**
	 * ���̱�ʾ
	 * �Ϻ��°�
	 */
	private int []board= {
			0,   0,   0,   0,   0,   0,  0,  0,
			0,  18,  17,  16,  17,  18,  0,  0,
			0,  19,  19,  19,  19,  19,  0,  0,
			0,   0,   0,   0,   0,   0,  0,  0,
			0,   0,   0,   0,   0,   0,  0,  0,
			0,  11,  11,  11,  11,  11,  0,  0,
			0,  10,   9,   8,   9,  10,  0,  0,
			0,   0,   0,   0,   0,   0,  0,  0
	};
	/**��ʾ��ǰ��˭���壬��ʼ��Ϊ�׷�*/
	private int currentPlayer=Const.WHITE;
	
	private int theBestMove = 0;
	private int theDepth=0;//��ǰ�������
	/**
	 * �����߷�
	 */
	private int theMoves[]= new int[MAX_GEN_MOVES];
	public ChessStart()
	{
		try {
			qipan = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/qipan2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setSize(600,550);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void changeCurrentPlayer()
	{
		currentPlayer=1-this.currentPlayer;
	}
	//ɾ��λ��location������
	public void removePiece(int location)
	{
		board[location]=Const.NOPIECE;
	}
	public void addPiece(int location,int piece)
	{
		board[location]=piece;
	}
	/**
	 * �Ƿ��ǵ�ǰ��ҵ�����
	 * @param piece
	 * @return
	 */
	public boolean isMyPiece(int piece)
	{
		int flag = piece/8-1;
		if(flag==this.currentPlayer)
			return true;
		else return false;
	}
	/**
	 * �Ƿ���player������
	 * @param piece
	 * @param player
	 * @return
	 */
	public boolean isMyPiece(int player,int piece)
	{
		int flag = piece/8-1;
		if(flag==player)
			return true;
		else return false;
	}
	/**
	 * 
	 * @param location
	 * @return location ��x��ʼλ��Ϊ0
	 */
	public int getXFromLocation(int location)
	{
		return location%8-1;
	}
	/**
	 * 
	 * @param location
	 * @return location ��x��ʼλ��Ϊ0
	 */
	public int getYFromLocation(int location)
	{
		return location/8-1;
	}
	
	/*****************************
	 ********* �߷�����  *************
	 ****************************/
	/**

	 * @param from �������
	 * @param to �����յ�
	 * @return from��to������ɵ����֣����Բ�ֳ�from��to
	 */
	public int generateMoves(int from,int to)
	{
		return  from+to*256;
	}
	/** 
	 * @param move�߷�
	 * @return from ��location
	 */
	public int getMoveFrom(int move)
	{
		return move%256;
	}
	/**
	 * @param move�߷�
	 * @return to��location
	 */
	public int getMoveTo(int move)
	{
		return move/256;
	}
	/**
	 * 
	 * @param moves�߷�����
	 * @return�߷��ĸ�������Ϊ0�����������
	 */
	public int getAllMoves(int moves[])
	{
		int count=0;//�߷��ĸ���
		for(int i=0;i<64;i++)
		{
			//������������ڼ�������
			if(Const.isInBorad[i]==0)
				continue;
			int piece = board[i];//�õ�����
			//��������ҷ����ӣ���������
			if(!isMyPiece(piece))
				continue;
			int from = i;
			int to=0;
			switch (piece%8+1)
			{
			case 1://KING = 1
			{
				for(int j=0;j<
						Const.kingMoveTable.length;j++)
				{
					to = from+Const.kingMoveTable[j];
					if(Const.isAtHome[currentPlayer][to]==1
							&&!isMyPiece(board[to]))
					{
						moves[count]=generateMoves(from, to);
						count++;
					}
				}
				break;
			}
			case 2://ARCHER = 2
			{
				for(int j=0;j<
						Const.archerMoveTable.length;j++)
				{
					to = from + Const.archerMoveTable[j];
					if(Const.isInBorad[to]==1&&
							!isMyPiece(board[to]))
					{
						moves[count]=generateMoves(from, to);
						count++;
					}
				}
				break;
			}
			case 3://KNIGHT = 3
			{
				for(int j=0;j<
						Const.knightMoveTable.length;j++)
				{
					to = from + Const.knightMoveTable[j];
					if(Const.isInBorad[to]==1&&
							!isMyPiece(board[to]))
					{
						moves[count]=generateMoves(from, to);
						count++;
					}
				}
				break;
			}
			case 4://FIGHTER = 4
			{
				for(int j=0;j<
						Const.fighterMoveTable.length;j++)
				{
					to = from + Const.fighterMoveTable[j];
					if(Const.isAtHome[currentPlayer][from]==1
							&&Const.isAtHome[currentPlayer][to]==1)
					{
						if(from-to>1||from-to<-1)
						{
							//�жϺ���
							if(((from+Const.isAtHome[currentPlayer][7])!=to)
									&&!isMyPiece(board[to]))
							{
								moves[count]=generateMoves(from, to);
								count++;
							}
						}
					}else if(Const.isInBorad[to]==1)
					{
						if(((from+Const.isAtHome[currentPlayer][7])!=to)
								&&!isMyPiece(board[to]))
						{
							moves[count]=generateMoves(from, to);
							count++;
						}
					}
				}
				break;
			}
				
			}
		}
		return count;
	}
	public boolean makeOneMove(int move,int []beAtePiece)
	{
		boolean illegal=false;
		
		int count = this.getAllMoves(theMoves);
		//�ж��߷��Ƿ�Ϸ�
		for(int i=0;i<count;i++)
		{
			if(theMoves[i]==move)
			{
				illegal = true;
				break;
			}
		}
		//����߷����Ϸ�������������ʧ��
//		System.out.println(illegal);
		if(!illegal)
			return false;
		int from = this.getMoveFrom(move);
		int to = this.getMoveTo(move);

		beAtePiece[0] = board[to];//���汻�Ե�����
		
		removePiece(to);
		addPiece(to,board[from]);
		removePiece(from);
//		System.out.println(this.currentPlayer+"  makeOneMove:"+to);
		this.changeCurrentPlayer();
		return true;
		
	}
	public void undoOneMove(int move,int beAtePiece)
	{
		int from = this.getMoveFrom(move);
		int to = this.getMoveTo(move);
		addPiece(from, board[to]);
		
		removePiece(to);
		addPiece(to,beAtePiece);
		this.changeCurrentPlayer();
	}
	/*******************************
	 *********** ��������**************
	 *******************************/
	/**
	 * �жϵ�ǰ���巽�Ƿ�����
	 * @return
	 */
	public boolean isPlayerDead()
	{
//		System.out.println("Enter isPlayerDead");
		int count = this.getAllMoves(theMoves);//��ǰ���巽�����߷�
		int kingTo[]=new int[8];//�õ���ǰ���巽king��Ҫȥ�ĵط�
		int kingOn=0;
		int k=0;//��¼king�м��������ߵĵط�
		for(int i=0;i<count;i++)
		{
			//�ҵ���ǰ���巽��king��λ��
			if(board[this.getMoveFrom(theMoves[i])]
					==currentPlayer*8+8)
			{//�����������
				kingTo[k]=this.getMoveTo(theMoves[i]);//�õ�kingȥ�ĵط�
				k++;
			}
		}
//		System.out.println("k:"+k);
		if(k==0)//�����ǰ���巽������ߣ�����true����ʧ����
		{
			for(int i=0;i<64;i++)
			{
				if(board[i]==currentPlayer*8+8)
				{
					kingOn=i;
					break;
				}
			}
			if(kingOn==0)
			{
				return true;
			}
		}
		
//		System.out.println("kingOn:"+kingOn);
		//�õ��Է����߷�
		int moves[]=new int [this.MAX_GEN_MOVES];
		this.changeCurrentPlayer();
		int count2 = this.getAllMoves(moves);
		this.changeCurrentPlayer();
		int n=0;//���king�ߵĵط��Ƿ񱻽���
		for(int j=0;j<count2;j++)
		{
			if(kingOn==this.getMoveTo(moves[j]))//�Ƿ���������
			{
				if(k==0)
				{
					return true;
				}
				else{
					for(int i=0;i<k;i++)
					{
						//�ж�king�����ĵط��Ƿ񱻽���
						if(kingTo[i]==this.getMoveTo(moves[j]))
						{
							n++;
							break;
						}
					}
					if(n==k)
						break;
				}
			}
		}
		if(k!=0&&n==k)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * ��������
	 */
	public int evaluatePosition()
	{
		int whiteValue=0;
		int blackValue=0;
		if(this.isPlayerDead())
		{
			return -Const.INFINITY_VALUE+this.theDepth;
		}
		for(int i=0;i<64;i++)
		{
			if(Const.isInBorad[i]==1)
			{
				if(board[i]>=16)
				{
					whiteValue+=Const.value[board[i]%8];
				}
				if(board[i]>1&&board[i]<16)
				{
					blackValue+=Const.value[board[i]%8];
				}
			}
		}
		int value=whiteValue-blackValue;
		//����Ϊ��
		return currentPlayer==Const.WHITE?-value:value;
	}
//////////////////////////////////////////////
//��������                 //
//////////////////////////////////////////////

//Alpha-Beta��������
	public int AlphaBetaSearch(int depth, int alpha, int beta) 
	{
//		System.out.println("Enter AlphaBetaSearch");
		int i, genCount, value;
		int allMoves[]=new int[MAX_GEN_MOVES];
		int beAteChess[]=new int[1];
	
		//���������ָ����ȣ��򷵻ؾ�������ֵ
		if (depth == 0) {
			return evaluatePosition();
		}
//		System.out.println(1);
		//�����ɱ�壬�͸��ݾ�ɱ��Ĳ�����������
		if (isPlayerDead()) { //�Լ�����
			return -Const.INFINITY_VALUE + theDepth;
		}
//	    System.out.println(2);
		genCount = getAllMoves(allMoves);
	
		for (i = 0; i < genCount; i++) {
			
			if (makeOneMove(allMoves[i], beAteChess)) { //�������ɹ�
				theDepth++;
//				value = -AlphaBetaSearch(depth - 1, -beta, -alpha); //�ݹ�
				value = -AlphaBetaSearch(depth - 1, -beta, -alpha); //�ݹ�
				undoOneMove(allMoves[i], beAteChess[0]); //��ԭ
				theDepth--;
	
				if (value >= beta) {
					return beta;
				}
				if (value > alpha) {
					alpha = value;
					if (theDepth == 0) { //����Ǹ��ڵ㱣������߷�
						theBestMove = allMoves[i];
					}
				}
			}
		}
	
		return alpha;
	}

	//�õ�������
	public void computerThink() {
		int beAteChess[]=new int[1];
		theDepth = 0; //������ľ���
		AlphaBetaSearch(Const.SERCH_DEPTH, -Const.INFINITY_VALUE, Const.INFINITY_VALUE);
		makeOneMove(theBestMove, beAteChess);
		last = this.getMoveTo(theBestMove);
	}
	
	public void paint(Graphics g)
	{
		//���ڽ���Ҫ�����Ļ���Ϊһ��ͼƬ
		BufferedImage bf = new BufferedImage(
				460, 540, 1);
		Graphics bg = bf.createGraphics();
		bg.drawImage(qipan, 0, 0, 460, 540, this);
		bg.setFont(Const.f);
		for(int i=0;i<64;i++)
		{
			this.drawChess(i, bg);
			
		}
		//���������ͼƬ
		g.drawImage(bf, x0, y0, 460, 540, this);
	}

	public void drawChess(int location,Graphics g)
	{
		if(Const.isInBorad[location]==1&&board[location]>1)
		{
			int x=(int)(50+this.getXFromLocation(location)*87.5);
			int y = (int)(43+this.getYFromLocation(location)*88);
			int length=30;
			g.setColor(Color.gray);
			//��������
			g.fillOval(x-length,
					y-length, 60, 60);
			
			if(board[location]>=16)
			{
				g.setColor(Color.black);
			}
			else if(board[location]<16&&board[location]>7)
			{
				g.setColor(Color.WHITE);
			}
			switch(board[location]%8+1)
			{
			case 1://king
				{
					g.drawString("��", x-15, y+10);
					break;
				}
			case 2://ARCHER
				{
					g.drawString("��", x-15, y+10);
					break;
				}
			case 3://KNIGHT
				{
					g.drawString("��", x-15, y+10);
					break;
				}
			case 4://FIGHTER
				{
					g.drawString("��", x-15, y+10);
					break;
				}
			}
			
			if(last == location)
			{
				g.setColor(Color.red);
				g.drawOval(x-20, y-20,40, 40);
			}
			g.setColor(Color.black);
		}
	}
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseMoved(MouseEvent e) {
		double xx=(e.getX()-x0-50)/87.5;
		double yy=(e.getY()-y0-45)/87.9;
		int x=(int)xx;
		int y=(int)yy;
		if(yy-y>0.5)
		{
			y+=1;
		}
		if(xx-x>0.5)
		{
			x+=1;
		}
		
		if(x>=0&&x<5&&y>=0&&y<6)
		{
			this.mouseLocation=(y+1)*8+x+1;
		}
		
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(this.getXFromLocation(this.mouseLocation)
//				+" "+this.getYFromLocation(this.mouseLocation));
		if(Const.isInBorad[this.mouseLocation]==1)
		{
		
			if(isMyPiece(board[this.mouseLocation]))
			{
//				System.out.println(this.mouseLocation);
				this.playerFrom = this.mouseLocation;
			}
			else if(this.playerFrom!=0)
			{
//				System.out.println(this.mouseLocation);
				int beAtePiece[]=new int[1];
				int move = this.generateMoves(this.playerFrom, this.mouseLocation);
//				System.out.println(move);
				if(this.makeOneMove(move, beAtePiece))
				{
					last= this.mouseLocation;
//					System.out.println("ok");
					this.playerFrom=0;
					this.repaint();
					if(this.isPlayerDead())
					{
						JOptionPane.showMessageDialog(this, "You win!");

					}else
					{
						this.computerThink();
						if(this.isPlayerDead())
						{
//							System.out.println("you fail");
							JOptionPane.showMessageDialog(this, "You fail!");
						}
						else
						{
							this.repaint();
						}
					}
				}
			}
		}
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
