package com.deengames.ilmica.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.deengames.ilmica.model.DataHelper;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.utils.Action;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;
import com.deengames.radiantwrench.utils.RadiantWrenchException;
import com.deengames.radiantwrench.view.ImageButton;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class SetSelectionScreen extends Screen {
	
	private final int VERTICAL_SPACE_BETWEEN_SETS = 30;
	
	@Override
	public void initialize() { // throws RadiantWrenchException {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/images/mainMenuBackground.jpg");
		this.addSprite("content/images/choose-question-set.png");		
		
		String[] sets = DataHelper.getQuestionSetNames();
		
		int baseX = 50;
		int baseY = 100;
		
		for (int i = 0; i < sets.length; i++) {
			final Text t = this.addText(sets[i]);
			
			t.setX(baseX);
			t.setY(baseY + (VERTICAL_SPACE_BETWEEN_SETS * i));
			
			t.setClickListener(new ClickListener() {
				public void onClick(Clickable clicked) {
					ScreenController.getCurrentScreen().fadeOut();
					ScreenController.getCurrentScreen().addFadeOutListener(new Action() {
						public void invoke() {
							ScreenController.showScreen(new QuizScreen(t.getDisplayText()));
						}
					});
				}
			});
		}
		
		this.fadeIn();
	}
}
