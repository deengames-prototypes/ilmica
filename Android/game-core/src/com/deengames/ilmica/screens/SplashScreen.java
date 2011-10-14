package com.deengames.ilmica.screens;

import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.utils.Action;
import com.deengames.radiantwrench.utils.RadiantWrenchException;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Screen {

	private Timer _timer = new Timer();
	
	@Override
	public void initialize() { //throws RadiantWrenchException {
		super.initialize();
		
		this.fadeOutImmediately();
		
		Sprite s = this.addSprite("content/logo.png");
		
		// Data height: 460. Padded to 512, sigh.
		s.setX((this.getWidth() - 460) / 2);
		
		// Data height: 243. Padded to 256, sigh.
		s.setY((this.getHeight() - 243) / 2);
		
		this.addFadeInListener(new Action() {
			public void invoke() {
				_timer.schedule(new FadeOutClass(), 3 * 1000); // 3s
			}
		});
		
		this.addFadeOutListener(new Action() {
			public void invoke() {
				ScreenController.showScreen(new TitleScreen());
			}
		});
		
		this.fadeIn();
	}
	
	private class FadeOutClass extends TimerTask {
		public FadeOutClass() { }
		
		public void run() {
			ScreenController.getCurrentScreen().fadeOut();
		}
	}
	
	@Override
	public void update(double elapsedSeconds) {// throws RadiantWrenchException {
		super.update(elapsedSeconds);
	}
}
