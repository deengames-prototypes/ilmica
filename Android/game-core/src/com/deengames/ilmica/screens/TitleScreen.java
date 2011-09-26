package com.deengames.ilmica.screens;

import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;

public class TitleScreen extends Screen {
	
	@Override
	public void initialize() {
		Sprite s = this.addSprite("data/badlogic.jpg");
		s.setX(100);
		s.setY(100);
	}
}
