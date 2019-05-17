package qi.scanner;

public class Token {
	public Token_Type type;
	public String lexeme;
	public double value;
	private Object FuncPtr=null;

	
	public double f(double a)
	{
		return a;
	}
	public Token()
	{
	}
	public static void main(String[] args) {
		new Token();
	}
}
