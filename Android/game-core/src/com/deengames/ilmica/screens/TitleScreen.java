package com.deengames.ilmica.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.util.RadiantWrenchException;
import com.deengames.radiantwrench.utils.Action;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;
import com.deengames.radiantwrench.view.ImageButton;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class TitleScreen extends Screen {
	
	@Override
	public void initialize() throws RadiantWrenchException {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/mainMenuBackground.jpg");
		this.addSprite("content/ilmica.png");
		
		ImageButton go = this.addImageButon("content/go-button.png");
		go.x = 50;
		go.y = this.getHeight() - 50- go.height;
		Screen me = this;
		
		go.setClickListener(new ClickListener() {
			public void onClick(Clickable clicked) {
				Screen me = ScreenController.getCurrentScreen();
				me.fadeOut();
				me.addFadeOutListener(new Action() {
					public void invoke() throws RadiantWrenchException {
						ScreenController.showScreen(new SetSelectionScreen());
					}
				});
			}
		});
		this.fadeIn();
	}
}
