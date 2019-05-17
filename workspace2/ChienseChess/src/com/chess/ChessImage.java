package com.chess;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChessImage {
	public static Image[]chess ;
	private static String s[]= {
			"","black/BR","black/BN","black/BB","black/BA",		
			"black/BK","black/BC","black/BP",
			"","red/RR","red/RN","red/RB","red/RA",
			"red/RK","red/RC","red/RP",
			};
	public ChessImage()
	{
		chess = new Image[16];
		
		for(int i=1;i<8;i++)
		{
			try {
				chess[i] = ImageIO.read(ClassLoader.getSystemResourceAsStream(s[i]+".GIF"));
				chess[i+8] = ImageIO.read(ClassLoader.getSystemResourceAsStream(s[i+8]+".GIF"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static Image getImage(int piece)
	{
		Image c=null;
		try {
			c = ImageIO.read(ClassLoader.getSystemResourceAsStream(s[piece]+"S.GIF"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
}
