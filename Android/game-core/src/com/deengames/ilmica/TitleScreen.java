package com.deengames.ilmica;

import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class TitleScreen extends Screen {
	@Override
	public void initialize() {
		Sprite s = this.addSprite("data/badlogic.jpg");
		s.setX(100);
		s.setY(50);
		
		Text t = this.addText("Hi mom!");
		t.setX(25);
		t.setY(10);
	}
}
