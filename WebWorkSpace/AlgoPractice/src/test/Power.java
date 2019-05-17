package test;
/**
 *给定一个double类型的浮点数base和int类型的整数exponent。
 *求base的exponent次方。
 */
public class Power {
	public double power(double base, int exp)
	{
		if(base==0)	return 0;
		double res = 1;
		double cur = base;
		int exp1 = Math.abs(exp);
		while(exp1!=0)
		{
			if((exp1 & 1) == 1)
				res *= cur;
			cur *= cur;
			exp1 = exp1>>1;
		}
		return exp>0 ? res:1/res;
	}
	public static void main(String[] args) {
		double base = 2;
		for(int i=-1;i<=20;i++)
			System.out.println("2^"+i+"="+new Power().power(base, i));
	}
}
