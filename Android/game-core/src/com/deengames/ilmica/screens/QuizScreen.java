package com.deengames.ilmica.screens;

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
import com.deengames.radiantwrench.view.ImageButton;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class QuizScreen extends Screen {
	
	private String _setName;
	private Question[] _questions;
	
	public QuizScreen(String setName) {
		this._setName = setName;
	}
	
	@Override
	public void initialize() {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/mainMenuBackground.jpg");
		Text t= this.addText(_setName);
		t.getFont().setScale(2);
		t.setX((this.getWidth() - t.getWidth()) / 2);
		t.setY(12);
		
		this._questions = MainController.makeQuizForSet(this._setName);
	
		this.fadeIn();
	}
}
