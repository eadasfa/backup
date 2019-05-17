package test;
/**
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class NumberOfOne {

	public int  numberOfOne(int a)
	{
		return Integer.toBinaryString(a)
				.replaceAll("0", "")
				.length();
	}
	
	public static void main(String[] args) {
		int a = 15;
		NumberOfOne n = new NumberOfOne();
		System.out.println(n.numberOfOne(a));
		
	}
	public int  NumberOf1(int n)
	{
		int count = 0;
	    while(n!=0)
	    {
	        count++;
	        n &= n-1;
	    }
	    return count;
	}
}
