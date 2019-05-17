package dynamicprogramming;

import java.util.Arrays;

/**
 * ”√2*1 ∏≤∏«2*n
 * @author Administrator
 *
 */
public class CoverTwoMultiN {
	public static int RectCover(int n) {
        int allState = 4;
        int dp[][] = new int[n][4];
        //test first line
        dp[0][0] = 1;
        dp[0][3] = 1;
        for(int i=1;i<n;i++)
        {
            for(int now=0;now<allState;now++)
            {
                for(int lastline=0;lastline<allState;lastline++)
                {
                    if(((lastline==0||lastline==3)&&now==3)
                       ||(i+1<n&&lastline==3&&now==0))
                        dp[i][now] +=dp[i-1][lastline];
                }
            }
        }
        for(int i=0;i<n;i++)
        	System.out.println(Arrays.toString(dp[i]));
        return dp[n-1][3];
    }
	public static void main(String[] args) {
		System.out.println(CoverTwoMultiN.RectCover(2));
	}
}
