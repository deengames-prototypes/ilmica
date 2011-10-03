package com.deengames.ilmica.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class TitleScreen extends Screen {
	
	@Override
	public void initialize() {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/titlescreen.jpg");
		this.addImageButon("content/go-button.png");		
		this.fadeIn();
	}
}
