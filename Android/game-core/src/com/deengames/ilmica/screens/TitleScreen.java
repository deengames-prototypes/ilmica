package com.deengames.ilmica.screens;

import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class TitleScreen extends Screen {
	
	@Override
	public void initialize() {
		super.initialize();
		
		this.fadeOutImmediately();
		
		Text t = this.addText("Hi mom!");
		t.setX(25);
		t.setY(10);
		
		this.fadeIn();
	}
}
