package spittr.tools;

import java.util.Random;

public class Tools {
	public static String generateString(){
		Random r = new Random();
		int t = r.nextInt(20)+10;
		StringBuilder str = new StringBuilder();
		for(int i =0;i<t;i++){
			int z=0;
			do{
				z=r.nextInt(127);
			}while(!((z>='A'&&z<='Z')||(z>='a'&&z<='z')));
			
			str.append((char)z+"");
		}
		return str.toString();
	}
}
