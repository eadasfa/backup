package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * ��һ���ɺܶ�ľ�����ɵļ��ϣ�ÿ��ľ���ж�Ӧ�ĳ��ȣ������ܷ��ü����е���Щľ����ĳ��
 * ˳����β��������һ��������� 0 �ļ򵥶����������ľ����Ҫ���ϣ��򵥶���μ������Խ��Ķ���Ρ�
 * ��ʼ�����ǿյģ������ֲ�����Ҫô���������һ������Ϊ L ��ľ����Ҫôɾȥ�������Ѿ��е�ĳ��ľ����
 * ÿ�β����������㶼��Ҫ��֪�Ƿ����ü����е���Щľ������һ���򵥶���Ρ�
 * ��������:
 * 		ÿ���������������һ�����ݣ�ÿ�����ݵ�һ��Ϊһ�������� n ��ʾ����������(1 �� n �� 50000) ��
 * 	��������n�У�ÿ�е�һ������Ϊ�������� i (i �� {1,2})���ڶ�������Ϊһ������ L(1 �� L �� 1,000,000,000)��
 * 	��� i=1 �����ڼ����ڲ���һ������Ϊ L ��ľ������� i=2 ����ɾȥ�ڼ����ڵ�һ������Ϊ L ��ľ����
 * 	�������ݱ�֤ɾ��ʱ�����бض����ڳ���Ϊ L ��ľ��������������󼯺϶��Ƿǿյġ�
 * 
 * �������:
 *		����ÿһ�β���������һ���������������ڵ�ľ�����Թ��ɼ򵥶���Σ���� "Yes" ��������� "No"��
 *		��������1:
 *			5
			1 1
			1 1
			1 1
			2 1
			1 2
			
			�������1:
			No
			No
			Yes
			No
			No
 */
public class SimpleMultiplePolygon {
	private static final String NO="No";
	private static final String YES="Yes";
	public List<Integer> list = new ArrayList<>();
	public static void main(String[] args) {
		SimpleMultiplePolygon sp = new SimpleMultiplePolygon();
		Scanner s = new Scanner(System.in);
//		int n = s.nextInt();
//		for(int i=0;i<n;i++) {
//			sp.operate(s.nextInt(), s.nextInt());
//			System.out.println(sp.testNow()?YES:NO);
//		}
		int n = Integer.parseInt(args[0]);
		for(int i=0;i<n;i++) {
			sp.operate(Integer.parseInt(args[i*2+1]), Integer.parseInt(args[i*2+2]));
			if(i==n-2) {
				System.out.println(sp.list);
			}
			System.out.println(sp.testNow()?YES:NO);
		}
	}
	public  boolean testNow() {
		if(list!=null&&list.size()<3)
			return false;
		Collections.sort(list);
		int d = list.get(list.size()-1)-list.get(list.size()-2);
		int num = 0;
		for(int i=0;i<list.size()-2;i++) {
			num += list.get(i);
		}
		if(num<=d)
			return false;
		return true;
	}
	public  void delete(int length) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i)==length) {
				list.remove(i);
			}
		}
	}
	public void operate(int i, int length) {
		switch (i) {
		case 1:
			list.add(length);
			break;
		case 2:
			this.delete(length);
			break;
		default:
			break;
		}
	}
}
