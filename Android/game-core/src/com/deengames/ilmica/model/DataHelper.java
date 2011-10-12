package com.deengames.ilmica.model;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class DataHelper {

	// Static class
	private DataHelper() { }
	
	public static String[] getQuestionSetNames() {
		ArrayList<String> toReturn = new ArrayList<String>();
		
		String raw = FileHelper.readFile("content/questions.txt");		
		String[] lines = raw.split("\r\n");
		for (String line : lines) {
			if (line.trim().startsWith("Set:")) {
				toReturn.add(line.substring(4).trim());
			}
		}
		
		return toReturn.toArray(new String[0]);
	}
	
	public static ArrayList<Question> getQuestionsForSet(String setName) {
		ArrayList<Question> toReturn = new ArrayList<Question>();
		
		String raw = FileHelper.readFile("content/questions.txt");		
		String[] lines = raw.split("\r\n");
		boolean inTargetSet = false;
		Question currentQuestion = null;
		
		for (String line : lines) {
			if (inTargetSet == false) {
				if (line.trim().startsWith("Set:")) {
					String set = line.substring(4).trim();
					if (set.equals(setName)) {
						inTargetSet = true;
					}
				}
			} else {
				String trimmed = line.trim();
				if (trimmed.startsWith("Set:")) {
					// We were in a set, we hit the next set
					break;
				} else if (trimmed.startsWith("Question:")) {
					if (currentQuestion != null) {
						toReturn.add(currentQuestion);
					}
					
					String question = trimmed.substring(9).trim();
					currentQuestion = new Question(question);
				} else if (trimmed.startsWith("Answer:") || trimmed.startsWith("Best Answer:")) {
					if (trimmed.startsWith("A")) {
						currentQuestion.addAnswer(trimmed.substring(7).trim());
					} else {
						// Best answer
						currentQuestion.addBestAnswer(trimmed.substring(12).trim());
					}
				}
			}
		}
		
		return toReturn;
	}
}
