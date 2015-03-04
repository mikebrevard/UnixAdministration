package com.webapp.server;

import java.util.Random;

public class UserSimulator {

	private static Integer DATABASE_SIZE = 100;

	public static Integer getRowNumber() {
		Random rand = new Random();
		return rand.nextInt((DATABASE_SIZE - 0) + 1) + 0;
	}

	public static String randomWrite() {
		String text = "(Name, Email, Company) VALUES ('random name (wr)', 'random email (wr)', 'random company (wr)')";
		return text;
	}

	public static String randomUpdate() {
		String text = "Name='random name (up)', Email='random email (up)', Company='random company (up)'";
		return text;
	}
}