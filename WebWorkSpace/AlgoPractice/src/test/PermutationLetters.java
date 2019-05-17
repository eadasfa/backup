package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
/**
 * ����һ���ַ���,���ֵ����ӡ�����ַ������ַ����������С����������ַ���abc,���ӡ��
 * ���ַ�a,b,c�������г����������ַ���abc,acb,bac,bca,cab��cba��
 *	��������:
 *	����һ���ַ���,���Ȳ�����9(�������ַ��ظ�),�ַ�ֻ������Сд��ĸ��
 */
public class PermutationLetters {
	public ArrayList<String> Permutation(String str) {		
		ArrayList<String> res = new ArrayList<String>();
		if(str!=null&&str.length()!=0)
			Permutation(str.toCharArray(), res,0);
		Collections.sort(res);
		return res;
    }
	private void Permutation(char[]seq,ArrayList<String> res,int start) {		
		
		if(start == seq.length)	{
			res.add(String.valueOf(seq));
			return;
		}
		for(int i = start;i<seq.length;i++) {
			
			swap(seq, start, i);
			Permutation(seq, res,start+1);
			swap(seq, i, start);
		}
		
    }
	private void swap(char[]c,int i,int j) {
		if(i!=j) {
			char t = c[i];
			c[i] = c[j];
			c[j] = t;
		}
	}
	public ArrayList<String> Permutation2(String str) {
		
		ArrayList<String> res = new ArrayList<String>();
		if(str.length()==1) 	{
			res.add(str);
		}
		else {
			for(int i=0;i<str.length();i++)
			{
				ArrayList<String> temp = Permutation(str.substring(0, i)+
						str.substring(i+1,str.length()));
				for(String s:temp) {
					if(!res.contains(str.charAt(i)+s))
						res.add(str.charAt(i)+s);
				}
			}
		}
		return res;
    }
}
