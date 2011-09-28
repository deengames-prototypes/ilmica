package com.deengames.radiantwrench.core;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class Game implements ApplicationListener {
	
	SpriteBatch _spriteBatch;
	BitmapFont _defaultFont;
	
	private static Game _instance = new Game();
	public static Game GetCurrentGame() { return _instance; }
	
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
	}

	@Override
	public void render () {
		int SCREEN_WIDTH = Gdx.graphics.getWidth();
		int SCREEN_HEIGHT = Gdx.graphics.getHeight();
		int centerX = SCREEN_WIDTH / 2;
		int centerY = SCREEN_HEIGHT / 2;

		clearScreen();

		_spriteBatch.begin();
		_spriteBatch.setColor(Color.WHITE);
		
		Screen currentScreen = ScreenController.getCurrentScreen();		
		
		for (Sprite s : currentScreen.getSprites()) {
			Texture t = s.getTexture();
			_spriteBatch.draw(t,  s.getX(), SCREEN_HEIGHT - t.getHeight() - s.getY(), 0, 0, 
					t.getWidth(), t.getHeight());
		}
		
		for (Text t : currentScreen.getTexts()) {
			_defaultFont.draw(this._spriteBatch, t.getDisplayText(), t.getX(), SCREEN_HEIGHT - t.getY());			
		}
		
		_spriteBatch.end();
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
}