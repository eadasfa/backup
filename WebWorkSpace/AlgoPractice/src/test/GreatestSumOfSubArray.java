package test;

/**
HZż������Щרҵ������������Щ�Ǽ����רҵ��ͬѧ����������鿪����,
���ַ�����:�ڹ��ϵ�һάģʽʶ����,������Ҫ��������������������,
������ȫΪ������ʱ��,����ܺý��������,��������а�������,�Ƿ�Ӧ�ð���ĳ������,
�������Աߵ��������ֲ����أ�����:{6,-3,-2,7,-15,1,2,2},������������
����Ϊ8(�ӵ�0����ʼ,����3��Ϊֹ)����һ�����飬��������������������еĺͣ�
��᲻�ᱻ������ס��(�������ĳ���������1)

˼·:�����ÿһ��Ԫ�ؽ�β�����ĺͣ�ȡ���ֵ
 *
 */

public class GreatestSumOfSubArray {
	public int FindGreatestSumOfSubArray(int[] array) {

		int max = array[0];
		int last = array[0];
		for(int i=1;i<array.length;i++) {
			last = last+array[i]>array[i]?(last+array[i]):array[i];
			if(max<last) max = last;
		}
		return max;  
    }
}
