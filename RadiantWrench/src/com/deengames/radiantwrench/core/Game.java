package com.deengames.radiantwrench.core;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;

public class Game implements ApplicationListener {
	
	private static Game _instance = new Game();
	public static Game GetInstance() { return _instance; }
	
	SpriteBatch _spriteBatch;	
	BitmapFont _defaultFont;
	ArrayList<Sprite> _sprites = new ArrayList<Sprite>();
	
	
	public Game() {
		_instance = this;
	}
	
	@Override
	public void create () {
		_defaultFont = new BitmapFont();
		_defaultFont.setColor(Color.WHITE);		
		_spriteBatch = new SpriteBatch();		
	}

	@Override
	public void render () {
		clearScreen();
		
		Screen currentScreen = ScreenController.GetCurrentScreen();
		
		_spriteBatch.begin();
		_spriteBatch.setColor(Color.WHITE);
		for (Sprite sprite : currentScreen.getSprites()) {
			Texture t = sprite.getTexture();
			_spriteBatch.draw(t, sprite.getX() - (t.getWidth() / 2), sprite.getY() - (t.getHeight() / 2),
					0, 0, t.getWidth(), t.getHeight());
		}
		_spriteBatch.end();
	}

	private void clearScreen() {
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
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