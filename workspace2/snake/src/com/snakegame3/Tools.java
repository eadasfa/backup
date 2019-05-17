package com.snakegame3;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

public class Tools {

	public static Color color = new Color(55, 155, 255);
	public static int GAME_PANEL_WIDTH=660;
	public static int GAME_PANEL_HEIGHT=520;
	public static Font font = new Font("宋体",Font.BOLD , 18);
	public static int x0=10;
	public static int y0=40;
	//游戏状态
	public static String PREPARED = "1";
	public static String RUNNING = "2";
	public static String PAUSE = "3";
	public static String DEATH = "4";
	//snake速度
	public static int SPEED = 20;
	public static Vector<Color> colors()
	{
		Vector<Color> cc = new Vector<Color>();
		cc.add(new Color(220,63,35));
		cc.add(new Color(222,227,28));
		cc.add(new Color(174,34,221));
		cc.add(new Color(38,217,141));
		cc.add(new Color(38,213,217));
		cc.add(new Color(87,37,218));
		cc.add(new Color(227,122,28));
		cc.add(new Color(221,34,211));
		cc.add(new Color(191,64,99));
		cc.add(new Color(137,222,33));
		return cc;
	}
}
