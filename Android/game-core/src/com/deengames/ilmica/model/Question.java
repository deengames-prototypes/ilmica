package com.deengames.ilmica.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Question {
	private String _text;
	private ArrayList<String> _answers = new ArrayList<String>();
	private int _bestAnswerIndex = -1;
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
			this._bestAnswerIndex = this._answers.size() - 1;			
		}
	}

	public String[] getAnswers() {
		return this._answers.toArray(new String[0]);
	}

	public void randomizeAnswerOrder() {
		boolean hasAllNone = false;
		for (String answer : this._answers) {
			if (answer.toLowerCase().contains("all of the above") || answer.toLowerCase().contains("none of the above")) {
				hasAllNone = true;
			}
		}
		
		// skip questions with two answers, or with "all of the above" "none of the above"
		if (this._answers.size() > 2 && !hasAllNone) {
			ArrayList<String> randomized = new ArrayList<String>();
			Random rGen = new Random();
			
			while (this._answers.size() > 0) {
				int next = rGen.nextInt(this._answers.size());
				randomized.add(this._answers.get(next));
				this._answers.remove(next);
			}
			
			this._answers.addAll(randomized);
		}
	}

	public String getMetaData(QuestionMetaDataType key) {
		return _metaData.get(key);
	}

	public void setMetaData(QuestionMetaDataType key, String value) {
		this._metaData.put(key, value);
	}
	
	public int getBestAnswerIndex() {
		return this._bestAnswerIndex;
	}
}
