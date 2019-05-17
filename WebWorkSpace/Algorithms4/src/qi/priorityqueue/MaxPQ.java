package qi.priorityqueue;

public class MaxPQ <Key extends Comparable<Key>>
{
	 public static void main(String[] args) {
		String str = "PRIO*R**I*T*Y***QUE***U*E";
		String s[] = str.split("");
		int n = s.length;
		MaxPQ<String> m = new MaxPQ<String>();
		for(int i=0;i<n;i++)
		{
//			if(s[i].equals("*"))
//				System.out.println(m.deMax());
//			else
//				m.insert(s[i]);
			m.insert(s[i]);
		}
		System.out.println(m);
	 }
	 private Key []pq;
	 private int N=0;//储存于1---N中  pq[0]没用
	 
	 @SuppressWarnings("unchecked")
	public MaxPQ(int maxN)
	 {
		 pq = (Key[]) new Comparable[maxN+1];
	 }
	 @SuppressWarnings("unchecked")
	public MaxPQ()
	 {
		 pq = (Key[]) new Comparable[10];
	 }
	 public Key peek()
	 {	return pq[1];	}
	 public boolean isEmpty()
	 {
		 return N==0;
	 }
	 public int size() 
	 {
		 return N;
	 }
	 private boolean less(int i,int j)
	 {
		 return pq[i].compareTo(pq[j])<=0;
	 }
	 private void exch(int i, int j)
	 {
		 Key k =  pq[i];
		 pq[i] = pq[j];
		 pq[j] = k;
	 }
	 private void swim(int k)
	 {
		 while(k/2>=1&&less(k/2,k))
		 {
			 exch(k/2,k);
			 k /= 2;
		 }
	 }
	 private void sink(int k)
	 {
		 while(k*2<=N)
		 {
			 int large = k;
			 if(less(large,k*2))
				 large = k*2;
			 if(k*2+1<=N&&less(large,k*2+1))
				 large = k*2+1;
			 if(large==k)
			 {
				 break;
			 }
			 exch(k,large);
			 k = large;
		 }
	 } 
	 public void insert(Key k)
	 {
		 if(N+1>=pq.length) resize((pq.length+1)*2);
		 pq[++N] = k;
		 swim(N);
	 }
	 public Key deMax()
	 {
		 if(isEmpty())
			 return null;
		 Key k = pq[1];
		 exch(1,N);
		 pq[N] = null;
		 N--;
		 sink(1);
		 if(N<=pq.length/4)  resize(pq.length/2);
		 return k;
			 
	 }
	 private void resize(int max)
	 {
		@SuppressWarnings("unchecked")
		Key[]p = (Key[]) new Comparable[max];
		 for(int i=0;i<=N;i++)
			 p[i] = pq[i];
		 pq = p;
	 }
	 public String toString()
	 {
		 String s="";
		 for(int i=1,h=1,k=0;i<=N;i++)
		 {
			 if(i==1)
				 for(int j = 0;j<10-h;j++)
					 s+=" ";
			 if(k==Math.pow(2, h-1))	
			 {
				 s+="\n";
				 k=0;
				 h++;
				 for(int j = 0;j<10-h;j++)
					 s+=" ";
			 }
			 k++;
			 s+=pq[i].toString().trim(); 
		 }
		 return s;
	 }
}












