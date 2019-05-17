package qi.scanner;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scanner {
	int n=0;
	public static void main(String[] args) {
		new Scanner(StaticData.filePath);
	}
	public static int lineNo=0;
	FileReader InFile = null;
	static char TokenBuffer[]=new char[StaticData.TOKEN_LEN];  // ‰»Îª∫¥Ê
	public Scanner(String filePath)
	{
		InitScanner(filePath);
		char a=this.GetChar();
		while(true)
		{
			
			System.out.print(a);
			n++;
			if(n==472)
			{
			//	System.out.println((int)a);
				break;
			}
				
			a=this.GetChar();
		}
		System.out.println("ok");
			this.ColseScanner();
	}
	public boolean InitScanner(String filePath)
	{
		lineNo=1;
		try {
			InFile = new FileReader(filePath);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(InFile==null)
			return false;
		else 
			return true;
	}
	public void ColseScanner()
	{
		if(this.InFile!=null)
		{
			try {
				this.InFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public char GetChar()
	{
		int Char = (char) -1;
		try {
			 Char = (char)(this.InFile.read());
			if(Char==65535)
			{
				this.ColseScanner();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (char) Char;
	}
}
