package com.deengames.ilmica.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class TitleScreen extends Screen {
	
	@Override
	public void initialize() {
		super.initialize();
		
		this.fadeOutImmediately();
		this.addSprite("content/titlescreen.jpg");
		this.fadeIn();
	}
}
