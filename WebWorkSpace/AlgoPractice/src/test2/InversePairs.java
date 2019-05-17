package test2;

public class InversePairs {
	public static void main(String[] args) {
		int[] array= {1,2,3,4,5,6,7,0};
		System.out.println(new InversePairs().InversePairs(array));
	}
	public int InversePairs(int [] array) {
		if(array == null || array.length<=1) return 0;
		
		return mergeSort(array, 0, array.length-1);
	}
	private int mergeSort(int [] array,int lo, int hi) {
		 if(lo>=hi) return 0;
		 int mid = (lo+hi)/2;
			
		 return mergeSort(array,lo,mid)+mergeSort(array,mid+1,hi)+merge(array,lo,hi);
	}
	private int merge(int[] array, int lo, int hi) {
		if(lo>=hi) return 0;
	
		int[] copy = array.clone();
		int num = 0;
		int mid = (lo+hi)/2;
		int i = mid;
		int j = hi;
		while(i>=lo&&hi>=lo&&j>mid) {
			if(copy[i]>copy[j]) {
				num +=(j-mid);
				array[hi--] = copy[i--];
			}else {
				array[hi--] = copy[j--];
			}
		}
		while(i>=lo)
			array[hi--] = copy[i--];
		while(j>mid)
			array[hi--] = copy[j--];
		return num;
	}
}
