package test;
/**
 * һֻ����һ�ο�������1��̨�ף�Ҳ��������2��������Ҳ��������n���������������һ��n����̨���ܹ��ж�����������
 */
/**
 * ������
 * 	ÿһ̨�׵ķ�ʽ��������ǰ�������ĺ�
 */
public class JUMPFloor {
	public static int JumpFloorII(int target) {

        if(target<=0)    return 0;
        if(target ==1)    return 1;
        int sum = 2;//���ʾ̨����Ϊ2�ķ�ʽ����Ҳ����Ϊ��0����1�׵�����
        for(int i=3;i<=target;i++)
        {
            sum+=sum;
        }
        return sum;
    }
	public static void main(String[] args) {
		for(int i=0;i<=15;i++)
			System.out.println("��"+i+"�׵ķ�ʽ��������"+JumpFloorII(i));
		System.out.println();
	}
}
