package com.deengames.radiantwrench.view;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.deengames.radiantwrench.core.Game;
import com.deengames.radiantwrench.utils.Action;

public class Screen {	
	
	// #region fade delegate/events
	private ArrayList<Action> _fadeOutListeners = new ArrayList<Action>();
	private ArrayList<Action> _fadeInListeners = new ArrayList<Action>();
	private ArrayList<Button> _buttons = new ArrayList<Button>();
	
	public void addFadeOutListener(Action f) {
		this._fadeOutListeners.add(f);
	}
	
	public void removeFadeOutListener(Action f) {
		this._fadeOutListeners.remove(f);
	}
	
	public void addFadeInListener(Action f) {
		this._fadeInListeners.add(f);
	}
	
	public void removeFadeInListener(Action f) {
		this._fadeInListeners.remove(f);
	}
	// #endregion
	
	protected ArrayList<Sprite> _sprites = new ArrayList<Sprite>();
	protected ArrayList<Text> _texts = new ArrayList<Text>();
	private Sprite _blackoutSprite;
		
	public Sprite[] getSprites() { 
		return this._sprites.toArray(new Sprite[0]);
	}
	
	public Text[] getTexts() {
		return this._texts.toArray(new Text[0]);
	}
	
	public Button[] getButtons() {
		return this._buttons.toArray(new Button[0]);
	}
	
	public void initialize() {
		this._blackoutSprite = this.addSprite("content/blackout.jpg");
		this._blackoutSprite.setZ(999999999);
		this._blackoutSprite.setAlpha(0);
	}
	
	public void update(double elapsedSeconds) {
		for (Sprite s : this._sprites) {
			s.update();
		}
		
		updateSpriteAlphas(elapsedSeconds);
	}
	
	public void fadeOutImmediately() {
		this._blackoutSprite.setAlpha(1);
	}

	public void fadeOut() {
		this.fadeOut(0.5f);
	}
	
	public void fadeOut(float inSeconds)
    {
        this._blackoutSprite.setAlphaRate(1.0f / inSeconds);
    }
	
	public void fadeInImmediately() {
		this._blackoutSprite.setAlpha(0);
	}
	
	public void fadeIn()
    {
		this.fadeIn(0.5f);
    }
	
	public void fadeIn(float inSeconds) {
		this._blackoutSprite.setAlphaRate(-1.0f / inSeconds);
	}
	
	private void updateSpriteAlphas(double elapsedSeconds)
        {
			for (Sprite sprite : this._sprites)
            {
                if (sprite.getAlphaRate() != 0)
                {
                    float alphaDelta = (float)elapsedSeconds * sprite.getAlphaRate();

                    if (sprite.getAlpha() + alphaDelta >= 1)
                    {
                        sprite.setAlpha(1);
                    }
                    else
                        if (sprite.getAlpha() + alphaDelta <= 0)
                        {
                            sprite.setAlpha(0);
                        }
                        else
                        {
                            sprite.setAlpha(sprite.getAlpha() + alphaDelta);
                        }
                }
            }
			
			if (this._fadeInListeners.size() > 0 && this._blackoutSprite.getAlphaRate() != 0) {
				if (this._blackoutSprite.getAlphaRate() <= 0 && this._blackoutSprite.getAlpha() <= 0) {
					this._blackoutSprite.setAlpha(0);
					this._blackoutSprite.setAlphaRate(0);
					for (Action a : this._fadeInListeners) {
						a.invoke();
					}
				} else if (this._blackoutSprite.getAlphaRate() >= 0 && this._blackoutSprite.getAlpha() >= 1) {
					this._blackoutSprite.setAlpha(1);
					this._blackoutSprite.setAlphaRate(0);
					for (Action a : this._fadeOutListeners) {
						a.invoke();
					}
				}
					
			}
        }

	public void destroy() {
		this._sprites.clear();
	}
	
	public Sprite addSprite(String fileName) {
		Sprite s = new Sprite(fileName);
		this._sprites.add(s);
		Collections.sort(this._sprites);
		s.loadTexture();
		return s;
	}
	
	public Text addText(String text) {
		Text t = new Text(text);
		this._texts.add(t);
		return t;
	}
	
	public Button addButton() {
		Button button = new Button("Single", Game.getCurrentGame().getSkin().getStyle(ButtonStyle.class), "button-sl");
		this._buttons.add(button);
		Game.getCurrentGame().addButton(button);
		return button;
	}
	
	public int getWidth() {
		return Gdx.graphics.getWidth();
	}
	
	public int getHeight() {
		return Gdx.graphics.getHeight();
	}
}
