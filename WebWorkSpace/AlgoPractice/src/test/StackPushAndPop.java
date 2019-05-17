package test;
import java.util.Stack;
/**
 * 题目描述:
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列
 * （注意：这两个序列的长度是相等的）
 */
public class StackPushAndPop {
	public boolean IsPopOrder(int [] pushA,int [] popA) {
		Stack<Integer> al = new Stack<Integer>();
		for(int t=0,i=0;;) {
			if(!al.isEmpty()&&al.peek()==popA[t]) {
				t++;al.pop();
			}
			else if(i<pushA.length) 
				al.push(pushA[i++]);
			else t=-1;
			
			if(t==pushA.length) break;
			if(t==-1)	return false;
		}
		
		return true;
    }
	public static void main(String[] args) {
		StackPushAndPop s = new StackPushAndPop();
		int [] pushA = {1,2,3,4,5};
		int [] popA = {4,5,3,2,1};
		System.out.println(s.IsPopOrder(pushA, popA));
	}
	
	
}
