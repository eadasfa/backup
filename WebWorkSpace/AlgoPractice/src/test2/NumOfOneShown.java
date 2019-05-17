package test2;
/**
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
 * 但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
 * 可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */
public class NumOfOneShown {
	public static void main(String[] args) {
//		NumOfOneShown t = new NumOfOneShown();
//		System.out.println(t.NumberOf1Between1AndN_Solution(13));
		int n = 21345;	
		System.out.println(numberOf1(n));
		System.out.println(numberOf1(21345,1346));
	}
	//[n,m]
	public static int numberOf1(int n,int m) {
		if(n>m) {
			int t = n;
			n = m;
			m = t;
		}
		return numberOf1(m)-numberOf1(n-1);
	}
	public static int numberOf1(int n) {
		if(n<=0) return 0;
		int num = 0;
		int i = 1;//表示当前是第几位 1 各位   10 十位
		while(i<=n) {
			int x = n/i;
			num += (x/10)*i;
			if(x%10>=2) {
				num += i;
			}
			if(x%10==1) {
				num += n%i+1;
			}
			i *= 10;
		}
		return num;    
    }
}
