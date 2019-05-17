package test;
import java.util.Stack;
/**
 * ��Ŀ����:
 * ���������������У���һ�����б�ʾջ��ѹ��˳�����жϵڶ��������Ƿ�Ϊ��ջ�ĵ���˳��
 * ����ѹ��ջ���������־�����ȡ���������1,2,3,4,5��ĳջ��ѹ��˳��
 * ����4,5,3,2,1�Ǹ�ѹջ���ж�Ӧ��һ���������У���4,3,5,1,2�Ͳ������Ǹ�ѹջ���еĵ�������
 * ��ע�⣺���������еĳ�������ȵģ�
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
