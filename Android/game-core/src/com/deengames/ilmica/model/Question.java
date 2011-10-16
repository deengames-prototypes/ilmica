package com.deengames.ilmica.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Question {
	private String _text;
	private ArrayList<String> _answers = new ArrayList<String>();
	private int _bestAnswer = -1;
	private HashMap<QuestionMetaDataType, String> _metaData = new HashMap<QuestionMetaDataType, String>();
	
	public Question(String text) {
		this._text = text;
	}
	
	public String getText() {
		return this._text;
	}
	
	public void addAnswer(String answer) {
		this.addAnswer(answer, false);
	}
	
	public void addBestAnswer(String answer) {
		this.addAnswer(answer, true);
	}
	
	public void addAnswer(String answer, boolean isBestAnswer) {
		this._answers.add(answer);
		if (isBestAnswer == true) {
			this._bestAnswer = this._answers.size() - 1;			
		}
	}

	public String[] getAnswers() {
		return this._answers.toArray(new String[0]);
	}

	public void randomizeAnswerOrder() {
		ArrayList<String> randomized = new ArrayList<String>();
		Random rGen = new Random();
		
		while (this._answers.size() > 0) {
			int next = rGen.nextInt(this._answers.size());
			randomized.add(this._answers.get(next));
			this._answers.remove(next);
		}
		
		this._answers.addAll(randomized);
	}

	public String getMetaData(QuestionMetaDataType key) {
		return _metaData.get(key);
	}

	public void setMetaData(QuestionMetaDataType key, String value) {
		this._metaData.put(key, value);
	}
}
