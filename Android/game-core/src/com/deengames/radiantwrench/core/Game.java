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
import com.deengames.radiantwrench.view.ImageButton;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class Game implements ApplicationListener, InputProcessor {
	
	SpriteBatch _spriteBatch;
	BitmapFont _defaultFont;
	Date _lastRenderOn;
	
	private static Game _instance = new Game();
	public static Game getCurrentGame() { return _instance; }
	private Color NO_BLENDING = new Color(1, 1, 1, 1);
	
	public Game() {
		_instance = this;		
	}
	
	
	private void clearScreen() {
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void create () {
		_defaultFont = new BitmapFont();
		_defaultFont.setColor(Color.WHITE);	
		
		// Can't be earlier
		for (Sprite s : ScreenController.getCurrentScreen().getSprites()) {
			s.loadTexture();
		}
		
		_spriteBatch = new SpriteBatch(); // Can't be earlier
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render () {
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
			Texture t = s.getTexture();
			_spriteBatch.setColor(new Color(1, 1, 1, s.getAlpha()));
			_spriteBatch.draw(t,  0f + s.getX(), 0f + SCREEN_HEIGHT - t.getHeight() - s.getY(), 0, 0, 
					t.getWidth(), t.getHeight());					
		}
		
		_spriteBatch.setColor(NO_BLENDING);
		
		for (Text t : currentScreen.getTexts()) {
			BitmapFont font = _defaultFont;			
			if (t.getFont() != null) {
				font = t.getFont();
			}
			font.draw(this._spriteBatch, t.getDisplayText(), t.getX(), SCREEN_HEIGHT - t.getY());			
		}
		
		for (ImageButton b : currentScreen.getImageButtons()) {
			b.x = 50;
			b.y = 50;
			_spriteBatch.draw(b.getCurrentRegion(), b.x, b.y);
		}
			
		_spriteBatch.end();
		
		this._lastRenderOn = new Date();
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
		
		for (ImageButton b : ScreenController.getCurrentScreen().getImageButtons()) {
			b.touchDown(x, yFromScreenTop, pointer);			
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
		
		for (ImageButton b : ScreenController.getCurrentScreen().getImageButtons()) {
			b.touchUp(x, yFromScreenTop, pointer);			
		}
		
		return true;
	}
}