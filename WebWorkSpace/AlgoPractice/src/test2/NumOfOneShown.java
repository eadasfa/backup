package test2;
/**
 * ���1~13��������1���ֵĴ���,�����100~1300��������1���ֵĴ�����
 * Ϊ�����ر�����һ��1~13�а���1��������1��10��11��12��13��˹�����6��,
 * ���Ƕ��ں�����������û���ˡ�ACMerϣ�����ǰ����,������������ձ黯,
 * ���Ժܿ���������Ǹ�����������1���ֵĴ�������1 �� n ��1���ֵĴ�������
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
		int i = 1;//��ʾ��ǰ�ǵڼ�λ 1 ��λ   10 ʮλ
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