package com.deengames.ilmica.model;

import java.util.ArrayList;

public class Question {
	private String _text;
	private ArrayList<String> _answers = new ArrayList<String>();
	private int _bestAnswer = -1;
	
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
}
