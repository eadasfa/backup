package dynamicprogramming;

import java.util.Arrays;

public class MatrixChainMultiplication {
	private int m[][];
	private int []p;
	private int s[][];
	private int n;
	public MatrixChainMultiplication(int []p)
	{
		this.p=p;
		this.n = p.length-1;//矩阵链的长度
		this.m = new int[p.length][p.length];
		this.s = new int[p.length][p.length];
		this.matrixChainOrder(p);
	}
	public void matrixChainOrder(int []p)
	{
		for(int l=2;l<=n;l++)//the length of matrix
		{
			for(int i=1;i<=n-l+1;i++)//the start 
			{
				int j = i+l-1;//the end	(the start is i and the length is l)
				m[i][j] = Integer.MAX_VALUE;
				for(int k=i;k<=j-1;k++)
				{
					int q = m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
					if(q<m[i][j])
					{
						m[i][j] = q;
						s[i][j] = k;	
					}
				}
			}
		}
	}
	public int getLeast()
	{	return m[1][n];	}
	public String getString(int i,int j)
	{
		if(i>=j)	return "A"+j;
		String str="(";
		str+=getString(i, s[i][j]);
		str+=getString(s[i][j]+1, j);
		str+=")";
		return str;
	}
	public String toString()
	{
		return getString(1, n);
	}
	public static void main(String[] args) throws Exception {
		int p[] = {5,10,3,12,5,50,6};
		MatrixChainMultiplication mcm =new MatrixChainMultiplication(p);
//		Matrix A = new Matrix(3, 4);
//		Matrix B = new Matrix(4, 4);
//		Matrix C = A.matrixMultiply(B);
//		System.out.println(C);
		System.out.println("The least cost is "+mcm.getLeast());
		System.out.println(mcm);
	}
	

	
}
class Matrix
{
	private int rows;
	
	private int columns;
	private int[][] matrix;
	public Matrix(int rows,int columns)
	{
		this.rows = rows;
		this.columns = columns;
		matrix = new int[rows][columns];
		setMatrix();
	}
	private void setMatrix()
	{
		for(int i=0;i<rows;i++)
			for(int j=0;j<columns;j++)
				matrix[i][j] = (int)(Math.random()*100);
	}
	
	public int getVal(int i,int j)
	{
		return matrix[i][j];
	}
	public void setVal(int i,int j,int val)
	{
		matrix[i][j]=val;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	//普通法
	public Matrix matrixMultiply(Matrix B) throws Exception
	{
		if(this.getColumns()!=B.getRows())	
			throw new Exception("The two matrixes cannot be multiplied!");
		Matrix C = new Matrix(this.getRows(),B.getColumns());
		for(int i=0;i<this.getRows();i++)
		{
			for(int j=0;j<B.getColumns();j++)
			{
				int val=0;
				for(int k =0;k<this.getColumns();k++)
				{
					val+=this.getVal(i, k)*B.getVal(k, j);
				}
				C.setVal(i, j, val);
			}
		}
		return C;
	}
	public static Matrix matrixMultiply(Matrix A,Matrix B) throws Exception
	{	return A.matrixMultiply(B);		}
	//Strassen
//	public static Matrix strassenMulti(Matrix A,Matrix B) throws Exception
//	{
//		if(A.getColumns()!=B.getRows())	
//			throw new Exception("The two matrixes cannot be multiplied!");
//		
//	}
	public Matrix matrixAdd(Matrix B) throws Exception
	{
		if(this.getRows()!=B.getRows()||this.getColumns()!=B.getColumns())
			throw new Exception("The two matrixes cannot be added");
		Matrix C = new Matrix(this.getRows(), B.getColumns());
		for(int i=0;i<this.getRows();i++)
			for(int j=0;j<B.getColumns();j++)
				C.setVal(i, j, this.getVal(i, j)+B.getVal(i, j));
		return C;
	}
	public Matrix matrixMinus(Matrix B) throws Exception
	{
		if(this.getRows()!=B.getRows()||this.getColumns()!=B.getColumns())
			throw new Exception("The two matrixes cannot be minused");
		Matrix C = new Matrix(this.getRows(), B.getColumns());
		for(int i=0;i<this.getRows();i++)
			for(int j=0;j<B.getColumns();j++)
				C.setVal(i, j, this.getVal(i, j)-B.getVal(i, j));
		return C;
	}
	public void show()
	{
		System.out.println(this);
		
	}
	@Override
	public String toString() {
		String s="";
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
				s+=String.format("%d\t", matrix[i][j]);
			s+="\n";
		}
		return s;
	}
	
}
