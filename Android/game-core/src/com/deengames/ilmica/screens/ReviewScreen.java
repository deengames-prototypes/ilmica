package com.deengames.ilmica.screens;

import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.utils.Action;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;
import com.deengames.radiantwrench.utils.RadiantWrenchException;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Text;

public class ReviewScreen extends Screen {

	private final int HEADER_Y = 12;
	private final int HORIZONTAL_OFFSET = 32;
	private final int HEADER_OFFSET = 36;
	private final int LINE_OFFSET = 24;
	private final int FONT_SIZE = 14;
	
	private String _setName;
	private int[] _userAnswers;
	private int[] _correctAnswers;
	private long _startTime;
	
	public ReviewScreen(String setName, int[] userAnswers, int[] correctAnswers, long startTime) {
		this._setName = setName;
		this._userAnswers = userAnswers;
		this._correctAnswers = correctAnswers;
		this._startTime = startTime;
	}
	
	@Override
	public void initialize() {
		super.initialize();
		
		int numCorrect = calcuatePercentCorrect(this._userAnswers, this._correctAnswers);
		int percentCorrect = Math.round((100f * numCorrect) / this._correctAnswers.length);
		
		Text header = this.addText("Review");
		header.setFontSize(24);
		header.setX((this.getWidth() - header.getWidth()) / 2);
		header.setY(HEADER_Y);
		
		Text setNameText = this.addText("Set: " + this._setName);
		setNameText.setFontSize(FONT_SIZE);
		setNameText.setX(HORIZONTAL_OFFSET);
		setNameText.setY(header.getY() + HEADER_OFFSET);
		
		Text results = this.addText("Answered Correctly: " + numCorrect + "/" + this._correctAnswers.length + " (" + percentCorrect + "%)");
		results.setFontSize(FONT_SIZE);
		results.setX(HORIZONTAL_OFFSET);
		results.setY(setNameText.getY() + LINE_OFFSET);
		
		Text numQuestions = this.addText("# of Questions: " + this._correctAnswers.length);
		numQuestions.setFontSize(FONT_SIZE);
		numQuestions.setX(HORIZONTAL_OFFSET);
		numQuestions.setY(results.getY() + LINE_OFFSET);
		
		long now = System.nanoTime();
		long elapsedSeconds = Math.round((now - this._startTime) / Math.pow(10, 9));
		
		long seconds = elapsedSeconds % 60;
		long minutes = (elapsedSeconds / 60) % 60;
		long hours = (elapsedSeconds / 3600);
		
		String playTime = "";
		if (hours > 0) {
			playTime = hours + " hour(s), ";
		}
		playTime += minutes + " minute(s), " + seconds + " second(s)";
		
		Text timePlayed = this.addText("Time played: " + playTime);
		timePlayed.setFontSize(FONT_SIZE);
		timePlayed.setX(HORIZONTAL_OFFSET);
		timePlayed.setY(numQuestions.getY() + LINE_OFFSET);
		
		Text clickToTitle = this.addText("(Touch/click anywhere to return to the title screen)");
		clickToTitle.setFontSize(14);
		clickToTitle.setX((this.getWidth() - clickToTitle.getWidth()) / 2);
		clickToTitle.setY(this.getHeight() - clickToTitle.getHeight());
		
		// Can't easily do screen-wide touchdown, just use blackout
		this._blackoutSprite.setClickListener(new ClickListener() {
			@Override
			public void onClick(Clickable clickable) {
				fadeOut();
				addFadeOutListener(new Action() {
					@Override
					public void invoke() {
						ScreenController.showScreen(new TitleScreen());
					}
				});
			}
			
		});
	}

	private int calcuatePercentCorrect(int[] userAnswers, int[] correctAnswers) {
		if (userAnswers.length != correctAnswers.length) {
			throw new RadiantWrenchException("Best (" + correctAnswers.length + ") and actual (" + userAnswers.length + ") array lengths don't match.");
		}
		
		int numCorrect = 0;
		for (int i = 0; i < userAnswers.length; i++) {
			if (userAnswers[i] == correctAnswers[i]) {
				numCorrect++;
			}
		}
		
		return numCorrect;
	}
	
}
