package test;
/**
 *��һ����ά�����У�ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ��µ�����˳������
 *�����һ������������������һ����ά�����һ���������ж��������Ƿ��и�������
 */
public class SearchInTwoDimention {
    public boolean Find(int target, int [][] array) {
    	int m = array.length;
    	int n = array[0].length;
		return BinarySearch(target, array, 0, m-1, 0, n-1);
    }
    public boolean BinarySearch(int target,int [][]array,int rowLo,int rowHi,
    													int colLo,int colHi)
    {
    	if(rowLo>rowHi||colLo>colHi)	return false;
    	int rowMid = (rowLo+rowHi)/2;
    	int colMid = (colLo+colHi)/2;
    	if(array[rowMid][colMid]!=target) {
    		if(array[rowMid][colMid]<target)
        	{
        		if(BinarySearch(target, array, rowMid+1, rowHi,colLo,colHi))
        			return true;
        		return BinarySearch(target, array, rowLo, rowHi,colMid+1,colHi);
        	}else if(array[rowMid][colMid]>target) {
        		if(BinarySearch(target, array, rowLo, rowHi, colLo,colMid-1))
        			return true;
        		return BinarySearch(target, array, rowLo, rowMid-1,colLo,colHi);
        	}
    	}
    	return true;
    }
    public static void main(String[] args) {
		int a[][] = {
				{1, 2,3,4,5,6,7,8,9,10},
				{11,12,13,14,15,16,17,18,19,20},
				{21,22,23,24,25,26,27,28,29,30}
		};
		int target = 11;
		System.out.println(new SearchInTwoDimention().Find(target, a));
	}
}
