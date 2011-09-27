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
		int centerX = Gdx.graphics.getWidth() / 2;
		int centerY = Gdx.graphics.getHeight() / 2;

		clearScreen();

		_spriteBatch.begin();
		_spriteBatch.setColor(Color.WHITE);
		
		for (Sprite s : ScreenController.getCurrentScreen().getSprites()) {
			Texture t = s.getTexture();
			_spriteBatch.draw(t,  centerX - (t.getWidth() / 2) + s.getX(),
					centerY - (t.getHeight() / 2) + s.getY(), 0, 0, 
					t.getWidth(), t.getHeight());
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