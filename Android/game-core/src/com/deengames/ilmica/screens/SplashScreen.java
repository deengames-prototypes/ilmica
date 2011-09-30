package com.deengames.ilmica.screens;

import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class SplashScreen extends Screen {

	@Override
	public void initialize() {
		super.initialize();
		
		this.fadeOutImmediately();
		
		Sprite s = this.addSprite("content/logo.png");
		
		// Data height: 460. Padded to 512, sigh.
		s.setX((this.getWidth() - 460) / 2);
		
		// Data height: 243. Padded to 256, sigh.
		s.setY((this.getHeight() - 243) / 2);
		
		this.fadeIn();
	}
	
	@Override
	public void update(double elapsedSeconds) {
		super.update(elapsedSeconds);
	}
}
