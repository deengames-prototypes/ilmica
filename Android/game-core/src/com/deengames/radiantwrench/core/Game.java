package com.deengames.radiantwrench.core;

import java.util.ArrayList;
import java.util.Date;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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
import com.deengames.radiantwrench.view.Screen;
import com.deengames.radiantwrench.view.Sprite;
import com.deengames.radiantwrench.view.Text;

public class Game implements ApplicationListener {
	
	SpriteBatch _spriteBatch;
	BitmapFont _defaultFont;
	Date _lastRenderOn;
	Stage _ui;
	Skin _skin;
	Window _window;
	
	private static Game _instance = new Game();
	public static Game getCurrentGame() { return _instance; }
	private Color NO_BLENDING = new Color(1, 1, 1, 1);
	
	public Game() {
		_instance = this;
	}
	
	public Skin getSkin() {
		return this._skin;
	}
	
	private void clearScreen() {
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	
	public void addButton(Button b) {
		this._ui.addActor(b);
	}
	
	@Override
	public void create () {
		_defaultFont = new BitmapFont();
		_defaultFont.setColor(Color.WHITE);	
		
		this._skin = new Skin(Gdx.files.internal("data/uiskin.json"), Gdx.files.internal("data/uiskin.png"));
		this._ui = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(this._ui);
		this._window = new Window("window", "Dialog", this._ui, this._skin.getStyle(WindowStyle.class), 420, 440);
        this._window.x = this._window.y = 0;
        this._ui.addActor(this._window);
		
		// Can't be earlier
		for (Sprite s : ScreenController.getCurrentScreen().getSprites()) {
			s.loadTexture();
		}
		
		_spriteBatch = new SpriteBatch(); // Can't be earlier
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
		
		for (Button b : currentScreen.getButtons()) {
			b.x = 50;
			b.y = 100;
			b.draw(_spriteBatch, 1);
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
}