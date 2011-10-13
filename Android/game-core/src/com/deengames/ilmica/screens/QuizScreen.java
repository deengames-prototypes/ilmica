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
import com.deengames.radiantwrench.util.RadiantWrenchException;
import com.deengames.radiantwrench.utils.Action;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;
import com.deengames.radiantwrench.view.ImageButton;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class QuizScreen extends Screen {
	
	private String _setName;
	private Question[] _questions;
	
	private Text _questionText;
	private ArrayList<Text> _answersTexts = new ArrayList<Text>();
	
	private final int QUESTION_TEXT_OFFSET = 32;
	
	public QuizScreen(String setName) {
		this._setName = setName;
	}
	
	@Override
	public void initialize() throws RadiantWrenchException {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/mainMenuBackground.jpg");
		Text t = this.addText(_setName);
		t.getFont().setScale(2);
		t.setX((this.getWidth() - t.getWidth()) / 2);
		t.setY(12);
		
		this._questionText = this.addText("");
		this._questionText.getFont().setScale(1.25f);
		this._questionText.setX(QUESTION_TEXT_OFFSET);
		this._questionText.setY(t.getY() + 36);
		// Equal out with our LHS padding
		this._questionText.setMaxWidth(this.getWidth() - (2 * QUESTION_TEXT_OFFSET));
		
		this._questions = MainController.makeQuizForSet(this._setName);
		showFirstQuestion();
		
		this.fadeIn();
	}

	private void showFirstQuestion() {
		showQuestion(0);
	}
	
	private void showQuestion(int index) {
		this._questionText.setDisplayText(this._questions[index].getText());
	}
}
