package test;

import java.util.ArrayList;

/**
 * ����һ�����󣬰��մ���������˳ʱ���˳�����δ�ӡ��ÿһ�����֣�
 * ���磬����������¾���{1 ,  2 , 3 , 4 },
					{5  , 6  ,7  ,8 },
					{9 , 10, 11 ,12 },
			    	{13 ,14 ,15 ,16 }
 * 
 * �����δ�ӡ������		1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class PlalindromeMatrix {
	public ArrayList<Integer> printMatrix(int [][] matrix) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		int row = matrix.length-1;
		int col = matrix[0].length-1;
		int t = (col+1)*(row+1);
		int i=0;
		int countOfCircle=0;
		int colNo=-1,rowNo=0;
		out1:
		while(true) {
			for(colNo++;colNo<=col-countOfCircle;colNo++) {
				al.add(matrix[rowNo][colNo]);
				if(++i==t)	break out1;
			}
			colNo--;
			for(rowNo++;rowNo<=row-countOfCircle;rowNo++){
				al.add(matrix[rowNo][colNo]);
				if(++i==t)	break out1;
			}
			rowNo--;
			for(colNo--;colNo>=countOfCircle;colNo--) {
				al.add(matrix[rowNo][colNo]);
				if(++i==t)	break out1;
			}
			colNo++;
			for(rowNo--;rowNo>countOfCircle;rowNo--) {
				al.add(matrix[rowNo][colNo]);
				if(++i==t)	break out1;
			}
			rowNo++;
			countOfCircle++;
		}
		System.out.println(al);
		return al;
		
    }
	
	public static void main(String[] args) {
		int matrix[][] = {{1},{2},{3},{4},{5}};

		PlalindromeMatrix pdm = new PlalindromeMatrix();
		pdm.printMatrix(matrix);
	}
}