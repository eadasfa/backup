package qi.simplechess;

import java.awt.Font;

public class Const {
	
	public static int NOPIECE = 0;//无子
	public static int KING = 1;//王
	public static int ARCHER = 2;//弓箭手
	public static int KNIGHT = 3;// 骑士
	public static int FIGHTER = 4;//步兵
	
	public static int BLACK = 1;
	public static int WHITE = 0;
	
	public static int SERCH_DEPTH = 7;//搜索深度
	public static int INFINITY_VALUE = 1000;//表示无穷大
	/**
	 * 值为1表示在棋盘内
	 */
	public static int []isInBorad={
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 1, 1, 1, 1, 0, 0,
		0, 1, 1, 1, 1, 1, 0, 0,
		0, 1, 1, 1, 1, 1, 0, 0,
		0, 1, 1, 1, 1, 1, 0, 0,
		0, 1, 1, 1, 1, 1, 0, 0,
		0, 1, 1, 1, 1, 1, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0
	};
	//初始化所有走法
	public static int []kingMoveTable={1,-1,8,-8,7,-7,9,-9};
	public static int []archerMoveTable={7,-7,9,-9};
	public static int []knightMoveTable={8,-8,1,-1};
	public static int []fighterMoveTable={8,-8,1,-1};
	//判断步兵是否后退
	public static int [][]isAtHome={
		{ //白王(步兵)
			0, 0, 0, 0, 0, 0, 0, 8, //下标为7的元素用来判断步兵是否后退
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0
		},{ //黑王(步兵)
			0, 0, 0, 0, 0, 0, 0,-8, //下标为7的元素用来判断步兵是否后退
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0
		}
	};
	/**
	 * 局面评估向量
	 * 王35,骑士5,弓箭手5,步兵3
	 */
	public static int value[]={35,5,5,3};

	public static Font f=new Font("宋体", Font.BOLD, 30);
}
