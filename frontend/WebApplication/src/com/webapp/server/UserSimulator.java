package com.webapp.server;

import java.util.Random;

public class UserSimulator {

	private static Integer DATABASE_SIZE = 2500000;

	public static Integer getRowNumber() {
		Random rand = new Random();
		return rand.nextInt((DATABASE_SIZE - 0) + 1) + 0;
	}

	public static String randomWrite() {
		String text = "(column1, column2, column3, column4, column5, column6) VALUES ('random (wr)', 'random (wr)', 'random (wr)','random (wr)', 'random (wr)', 'random (wr)')";
		return text;
	}

	public static String randomUpdate() {
		String text = "column1='random (up)', column2='random (up)', column3='random (up)', column4='random (up)', column5='random (up)', column6='random (up)'";
		return text;
	}
}