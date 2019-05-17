package qi.simplechess;

import java.awt.Font;

public class Const {
	
	public static int NOPIECE = 0;//����
	public static int KING = 1;//��
	public static int ARCHER = 2;//������
	public static int KNIGHT = 3;// ��ʿ
	public static int FIGHTER = 4;//����
	
	public static int BLACK = 1;
	public static int WHITE = 0;
	
	public static int SERCH_DEPTH = 7;//�������
	public static int INFINITY_VALUE = 1000;//��ʾ�����
	/**
	 * ֵΪ1��ʾ��������
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
	//��ʼ�������߷�
	public static int []kingMoveTable={1,-1,8,-8,7,-7,9,-9};
	public static int []archerMoveTable={7,-7,9,-9};
	public static int []knightMoveTable={8,-8,1,-1};
	public static int []fighterMoveTable={8,-8,1,-1};
	//�жϲ����Ƿ����
	public static int [][]isAtHome={
		{ //����(����)
			0, 0, 0, 0, 0, 0, 0, 8, //�±�Ϊ7��Ԫ�������жϲ����Ƿ����
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0,
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0
		},{ //����(����)
			0, 0, 0, 0, 0, 0, 0,-8, //�±�Ϊ7��Ԫ�������жϲ����Ƿ����
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
	 * ������������
	 * ��35,��ʿ5,������5,����3
	 */
	public static int value[]={35,5,5,3};

	public static Font f=new Font("����", Font.BOLD, 30);
}
