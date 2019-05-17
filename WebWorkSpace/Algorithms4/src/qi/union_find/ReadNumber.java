package qi.union_find;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadNumber {
	private int n=0;
	private double m=0;
	private String numbers="";
	String num[];
	private int numLength=0;
	public ReadNumber(String s)
	{
		this.readFile(s);
	}
	public int getANumber() {
		if(n>=this.numLength) return -1;
		else if(this.num[n].length()>0)
		{
			String s = this.num[n];
			if(s.length()==1)
			{
				n++;m=0;
				return Integer.parseInt(s);
			}
			else if(m==0)
			{
				int t= (int)( Math.ceil(s.length()*1.0/2));
//				System.out.println("s.length()="+s.length()+"  t="+t);
				String st = s.substring(0,t);
				m=1;
				return Integer.parseInt(st);
			}else {
				int t= (int)( Math.ceil(s.length()*1.0/2));
				String st = s.substring(t,s.length());
				n++;m=0;

				return Integer.parseInt(st);
			}
		}else {
			n++;m=0;
			return this.getANumber();
		}
		
	}

	public void readFile(String s)
	{
		BufferedReader br = null;
		FileReader fr=null;
		try {
			fr = new FileReader(s);
			br = new BufferedReader(fr);
			String str = "";
			while((str=br.readLine())!=null)
			{
				numbers+=str;
			}
			num = numbers.toString().split(" ");
			numLength=num.length;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
