package com.deengames.radiantwrench.core;

import java.util.ArrayList;
import java.util.Date;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.utils.RadiantWrenchException;
import com.deengames.radiantwrench.view.ImageButton;
import com.deengames.radiantwrench.view.ImageCheckbox;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class Game implements ApplicationListener, InputProcessor {
	
	SpriteBatch _spriteBatch;
	BitmapFont _defaultFont;
	Date _lastRenderOn;
	Sprite _blackout;
	
	private static Game _instance = new Game();
	public static Game getCurrentGame() { return _instance; }
	private Color NO_BLENDING = new Color(1, 1, 1, 1);
	
	public Game() {
		_instance = this;		
	}
	
	private void clearScreen() {
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	
	public BitmapFont getDefaultFont() {
		return this._defaultFont;
	}
	
	@Override
	public void create() {
		_defaultFont = new BitmapFont();
		_defaultFont.setColor(Color.WHITE);	
		
		// Can't be earlier
		for (Sprite s : ScreenController.getCurrentScreen().getSprites()) {
			s.loadTexture();
			if (s.getFileName().endsWith("/blackout.jpg")) {
				this._blackout = s;
			}
		}
		
		_spriteBatch = new SpriteBatch(); // Can't be earlier
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render () {
		//try {
			if (this._lastRenderOn == null) {
				this._lastRenderOn = new Date();
			}
			
			Date now = new Date();		
			double elapsedTime = (now.getTime() - this._lastRenderOn.getTime()) / 1000f;
			
			int SCREEN_WIDTH = Gdx.graphics.getWidth();
			int SCREEN_HEIGHT = Gdx.graphics.getHeight();
			int centerX = SCREEN_WIDTH / 2;
			int centerY = SCREEN_HEIGHT / 2;
	
			Screen currentScreen = ScreenController.getCurrentScreen();
			currentScreen.update(elapsedTime);
			
			clearScreen();
	
			_spriteBatch.begin();
			_spriteBatch.setColor(Color.WHITE);	
			
			for (Sprite s : currentScreen.getSprites()) {
				if (s.getFileName().endsWith("/blackout.jpg")) {
					// For some reason, assigning this early on gives us two copies?!?!
					this._blackout = s;
				} else {
					s.draw(this._spriteBatch);
				}
			}
			
			_spriteBatch.setColor(NO_BLENDING);
			
			for (Text t : currentScreen.getTexts()) {
				t.draw(this._spriteBatch);
			}
			
			for (ImageButton b : currentScreen.getImageButtons()) {
				b.rwDraw(this._spriteBatch);
			}
			
			for (ImageCheckbox i : currentScreen.getImageCheckboxes()) {
				i.rwDraw(this._spriteBatch);
			}
			
			this._blackout.draw(this._spriteBatch);
				
			_spriteBatch.end();
			
			this._lastRenderOn = new Date();
		/*} catch (Exception e) {
			ScreenController.getCurrentScreen().showException(e);
		}*/
	}
	
	@Override
	public void resize (int width, int height) {
		_spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
	}

	@Override
	public void pause () {

	}

	@Override
	public void resume () {

	}

	@Override
	public void dispose () {

	}


	@Override
	public boolean keyDown(int keyCode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyUp(int keyCode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int x, int y, int pointer, int button) { 
		int yFromScreenTop = ScreenController.getCurrentScreen().getHeight() - y;
		Screen currentScreen = ScreenController.getCurrentScreen();
		
		for (Text t : currentScreen.getTexts()) {
			t.touchDown(x, y, pointer);
		}
		
		for (ImageButton b : currentScreen.getImageButtons()) {
			b.touchDown(x, yFromScreenTop, pointer);			
		}
		
		for (ImageCheckbox c : currentScreen.getImageCheckboxes()) {
			c.touchDown(x, yFromScreenTop, pointer);
		}
		
		return true;
	}


	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		int yFromScreenTop = ScreenController.getCurrentScreen().getHeight() - y;
		Screen currentScreen = ScreenController.getCurrentScreen();
		
		for (Text t : currentScreen.getTexts()) {
			t.touchUp(x,  y, pointer);
		}
		
		for (ImageButton b : currentScreen.getImageButtons()) {
			b.touchUp(x, yFromScreenTop, pointer);			
		}
		
		return true;
	}

	public void rethrowException(RadiantWrenchException r) throws RadiantWrenchException {
		throw r;
	}
}