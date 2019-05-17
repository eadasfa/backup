package test2;


public class FirstAppearChar {
	public char FirstNotRepeatingChar(String str) {
		int length = str.length();
		int[] temp = new int[256];
		for(int i = 0;i<length;i++) {
			temp[str.charAt(i)]++; 
		}
		for(int i = 0;i<length;i++) {
			if(temp[str.charAt(i)]==1)
				return str.charAt(i);
		}
        return 0;
    }
	public static void main(String[] args) {
		System.out.println(new FirstAppearChar().FirstNotRepeatingChar("dafwavasfadfafd"));
	}
}
