package com.deengames.ilmica.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.deengames.ilmica.controller.MainController;
import com.deengames.ilmica.model.DataHelper;
import com.deengames.ilmica.model.Question;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.utils.Action;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;
import com.deengames.radiantwrench.utils.RadiantWrenchException;
import com.deengames.radiantwrench.view.ImageButton;
import com.deengames.radiantwrench.view.ImageCheckbox;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.SpriteSheet;
import com.deengames.radiantwrench.view.Text;

public class QuizScreen extends Screen {
	
	private String _setName;
	private Question[] _questions;
	
	private Text _questionText;
	private ArrayList<Text> _answersTexts = new ArrayList<Text>();
	
	private final int QUESTION_TEXT_HORIZONTAL_OFFSET = 32;
	private final int QUESTION_TEXT_VERTICAL_OFFSET = 44;
	private final int ANSWER_HORIZONTAL_OFFSET = 72;
	private final int ANSWERS_PADDING_FROM_QUESTION = 36;
	private final int SPACE_BETWEEN_ANSWERS = 32;
	private final int CHECKMARK_IMAGE_HORIZONTAL_WHITESPACE_OFFSET = 4;
	private final int CHECKMARK_IMAGE_VERTICAL_WHITESPACE_OFFSET = 10;
	
	private ImageCheckbox _clickedCheckbox = null;
	private SpriteSheet _previousButton = null;
	private SpriteSheet _nextButton = null;
	
	// TODO: move into model?
	int _currentQuestionIndex = 0;
	
	public QuizScreen(String setName) {
		this._setName = setName;
	}
	
	@Override
	public void initialize() { //throws RadiantWrenchException {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/images/mainMenuBackground.jpg");
		
		Text t = this.addText(_setName);
		t.setFontSize(24);
		t.setX((this.getWidth() - t.getWidth()) / 2);
		t.setY(12);
		
		this._questionText = this.addText("");
		this._questionText.setFontSize(14);
		this._questionText.setX(QUESTION_TEXT_HORIZONTAL_OFFSET);
		this._questionText.setY(t.getY() + QUESTION_TEXT_VERTICAL_OFFSET);
		
		// Equal out with our LHS padding
		this._questionText.setMaxWidth(this.getWidth() - (2 * QUESTION_TEXT_HORIZONTAL_OFFSET));
		
		this._questions = MainController.makeQuizForSet(this._setName);
		
		Sprite doneButton = this.addSprite("content/images/checkmark.png");
		doneButton.setX((this.getWidth() - doneButton.getWidth()) / 2);
		doneButton.setY(this.getHeight() - doneButton.getHeight());
		
		this._previousButton = this.addSpriteSheet("content/images/left-arrow.png", 64, 64);
		this._previousButton.setY(this.getHeight() - this._previousButton.getHeight());
		this._previousButton.setClickListener(new ClickListener() {
			public void onClick(Clickable clickable) {
				showPreviousQuestionIfPossible();
			}
		});
		
		this._nextButton = this.addSpriteSheet("content/images/right-arrow.png", 64, 64);
		this._nextButton.setX(this.getWidth() - this._nextButton.getWidth());
		this._nextButton.setY(this.getHeight() - this._nextButton.getHeight());
		this._nextButton.setClickListener(new ClickListener() {
			public void onClick(Clickable clickable) {
				showNextQuestionIfPossible();
			}
		});

		showCurrentQuestion();
		
		this.fadeIn();
	}

	private void showPreviousQuestionIfPossible() {
		if (this._currentQuestionIndex > 0) {
			this._currentQuestionIndex--;
			this.showCurrentQuestion();
		}
	}
	
	private void showNextQuestionIfPossible() {
		if (this._currentQuestionIndex < this._questions.length - 1) {
			this._currentQuestionIndex++;
			this.showCurrentQuestion();
		}
	}
	
	private void setupNextAndPreviousControls(int questionNumber) {
		if (questionNumber > 0) {
			this._previousButton.setFrameIndex(1);
		} else {
			this._previousButton.setFrameIndex(0);
		}
		
		if (questionNumber == this._questions.length - 1) {
			this._nextButton.setFrameIndex(0);
		} else {
			this._nextButton.setFrameIndex(1);
		}
	}

	private void showCurrentQuestion() {		
		showQuestionAndAnswers(this._currentQuestionIndex);
		setupNextAndPreviousControls(this._currentQuestionIndex);
	}

	private void showQuestionAndAnswers(int index) {
		clearPreviousAnswerControls();
		
		this._questionText.setDisplayText(this._questions[index].getText());
		String[] answers = this._questions[index].getAnswers();
		
		for (int i = 0; i < answers.length; i++) {
			Text t = this.addText(answers[i]);
			t.setX(ANSWER_HORIZONTAL_OFFSET);
			t.setY(this._questionText.getY() + 
					ANSWERS_PADDING_FROM_QUESTION + 
					this._questionText.getHeight() + (SPACE_BETWEEN_ANSWERS * i));
			t.setFontSize(12);
			
			ImageCheckbox c = this.addImageCheckbox();
			c.x = t.getX() - c.getScaledWidth() - CHECKMARK_IMAGE_HORIZONTAL_WHITESPACE_OFFSET;
			c.y = t.getY() - CHECKMARK_IMAGE_VERTICAL_WHITESPACE_OFFSET;
			
			c.setClickListener(new ClickListener() {
				public void onClick(Clickable clickable) {
					_clickedCheckbox = (ImageCheckbox)clickable;
					checkedQuestionChanged();
				}			
			});
		}
	}
	
	private void clearPreviousAnswerControls() {
		// Iterating over a list and removing items from it? ARRR MATEY!
		ArrayList<Text> toRemove = new ArrayList<Text>();
		
		for (int i = 0; i < this._texts.size(); i++) {
			Text t = this._texts.get(i);
			if (t != this._questionText) {
				toRemove.add(t);
			}
		}
		
		this._texts.removeAll(toRemove);
		
		while (this._imageCheckBoxes.size() > 0) {
			this._imageCheckBoxes.remove(0);
		}
	}

	/**
	 * Uncheck every checkbox except the checked guy.
	 * Essentially, this is like a set of radio buttons.
	 */
	public void checkedQuestionChanged() {
		for (ImageCheckbox c : this._imageCheckBoxes) {
			if (c == this._clickedCheckbox && c.getIsChecked() == true) {
				c.setIsChecked(true);
			} else {
				c.setIsChecked(false);
			}
		}
	}
}
