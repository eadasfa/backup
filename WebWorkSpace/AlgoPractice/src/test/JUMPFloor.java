package test;
/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
/**
 * 分析：
 * 	每一台阶的方式数，都是前面数量的和
 */
public class JUMPFloor {
	public static int JumpFloorII(int target) {

        if(target<=0)    return 0;
        if(target ==1)    return 1;
        int sum = 2;//你表示台阶数为2的方式数，也可认为第0、第1阶的数量
        for(int i=3;i<=target;i++)
        {
            sum+=sum;
        }
        return sum;
    }
	public static void main(String[] args) {
		for(int i=0;i<=15;i++)
			System.out.println("第"+i+"阶的方式的数量："+JumpFloorII(i));
		System.out.println();
	}
}
