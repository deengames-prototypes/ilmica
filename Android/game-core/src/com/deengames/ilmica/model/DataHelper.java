package com.deengames.ilmica.model;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class DataHelper {

	// Static class
	private DataHelper() { }
	
	public static String[] getQuestionSetNames() {
		ArrayList<String> toReturn = new ArrayList<String>();
		String raw = Gdx.files.internal("content/questions.txt").readString();
		
		String[] lines = raw.split("\r\n");
		for (String line : lines) {
			if (line.trim().startsWith("Set:")) {
				toReturn.add(line.substring(4).trim());
			}
		}
		
		return toReturn.toArray(new String[0]);
	}
}
