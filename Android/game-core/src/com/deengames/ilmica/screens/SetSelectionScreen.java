package com.deengames.ilmica.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.deengames.ilmica.model.DataHelper;
import com.deengames.radiantwrench.view.ImageButton;
import com.deengames.radiantwrench.view.ImageButton.ClickListener;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class SetSelectionScreen extends Screen {
	
	private Sprite _selectedSet;
	
	@Override
	public void initialize() {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/mainMenuBackground.jpg");
		this.addSprite("content/choose-question-set.png");		
		_selectedSet = this.addSprite("content/selected-set.png");
		_selectedSet.setAlpha(0);
		
		String[] sets = DataHelper.getQuestionSetNames();
		
		int baseX = 50;
		int baseY = 100;
		
		for (int i = 0; i < sets.length; i++) {
			Text t = this.addText(sets[i]);
			t.setX(baseX);
			t.setY(baseY + (30 * i));
		}
		
		this.fadeIn();
	}
}
