package com.deengames.ilmica.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
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
import com.deengames.radiantwrench.view.Text;

public class QuizScreen extends Screen {
	
	private String _setName;
	private Question[] _questions;
	
	private Text _questionText;
	private ArrayList<Text> _answersTexts = new ArrayList<Text>();
	
	private final int QUESTION_TEXT_HORIZONTAL_OFFSET = 32;
	private final int QUESTION_TEXT_VERTICAL_OFFSET = 44;
	private final int ANSWER_HORIZONTAL_OFFSET = 72;
	private final int ANSWERS_PADDING_FROM_QUESTION = 32;
	private final int SPACE_BETWEEN_ANSWERS = 32;
	private final int CHECKMARK_IMAGE_HORIZONTAL_WHITESPACE_OFFSET = 4;
	private final int CHECKMARK_IMAGE_VERTICAL_WHITESPACE_OFFSET = 9;
	
	public QuizScreen(String setName) {
		this._setName = setName;
	}
	
	@Override
	public void initialize() { //throws RadiantWrenchException {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/mainMenuBackground.jpg");
		
		Text t = this.addText(_setName);
		t.getFont().setScale(2);
		t.setX((this.getWidth() - t.getWidth()) / 2);
		t.setY(12);
		
		this._questionText = this.addText("");
		this._questionText.getFont().setScale(1.25f);
		this._questionText.setX(QUESTION_TEXT_HORIZONTAL_OFFSET);
		this._questionText.setY(t.getY() + QUESTION_TEXT_VERTICAL_OFFSET);
		
		// Equal out with our LHS padding
		this._questionText.setMaxWidth(this.getWidth() - (2 * QUESTION_TEXT_HORIZONTAL_OFFSET));
		
		this._questions = MainController.makeQuizForSet(this._setName);
		showFirstQuestion();
		
		this.fadeIn();
	}

	private void showFirstQuestion() {
		showQuestion(0);
	}
	
	private void showQuestion(int index) {
		this._questionText.setDisplayText(this._questions[index].getText());
		String[] answers = this._questions[index].getAnswers();
		
		for (int i = 0; i < answers.length; i++) {
			Text t = this.addText(answers[i]);
			t.setX(ANSWER_HORIZONTAL_OFFSET);
			t.setY(this._questionText.getY() + 
					ANSWERS_PADDING_FROM_QUESTION + 
					this._questionText.getHeight() + (SPACE_BETWEEN_ANSWERS * i));
			
			ImageCheckbox c = this.addImageCheckbox();
			c.setScale(0.25f);
			c.x = t.getX() - c.getScaledWidth() - CHECKMARK_IMAGE_HORIZONTAL_WHITESPACE_OFFSET;
			c.y = t.getY() - CHECKMARK_IMAGE_VERTICAL_WHITESPACE_OFFSET;
		}
	}
}
