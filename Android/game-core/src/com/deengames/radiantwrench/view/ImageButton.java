package com.deengames.radiantwrench.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;

// Adapted from http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=2168&sid=a5ac07a6f80769c1b8405b8ba181e913#p11486
public class ImageButton extends Image implements Clickable {
	
	private TextureRegion _currentRegion;
	private TextureRegion _down;
	private TextureRegion _up;
	private Texture _texture;
	private String _fileName;
	private boolean _wasDown = false;

	private ClickListener _clickListener;

	public ImageButton(String fileName) {
		super("Button");
		this._fileName = fileName;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		int yFromTop = (int)(ScreenController.getCurrentScreen().getHeight() - y);
		
		boolean touchDown = (x >= this.x && x <= this.x + this.width && 
				yFromTop >= this.y && yFromTop <= this.y + this.height);
		
		if (touchDown) {
			this.region  = this._down;
			this._wasDown = true;
			this._currentRegion = this._down;
		}

		return touchDown;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		this.region = this._up;
		this._currentRegion = this._up;
		super.touchUp(x, y, pointer);
		
		if (this._wasDown) {
			if (this._clickListener != null) {
				this._clickListener.onClick(this);
			}
			
			this._wasDown = false;
		}
	}

	public void loadTexture() {
		_texture = new Texture(Gdx.files.internal(this._fileName));
		this.width = this._texture.getWidth() / 2;
		this.height = this._texture.getHeight();
	}

	public Texture getTexture() {
		return this._texture;
	}

	public void setDownRegion(TextureRegion down) {
		this._down = down;
	}

	public void setUpRegion(TextureRegion up) {
		this._up = up;
		this._currentRegion = this._up;
		if (this.region != null) {
			this.region.setRegion(this._up);			
		}
	}

	public TextureRegion getCurrentRegion() {
		if (this._currentRegion == null) {
			this._currentRegion = this._up;
		}
		
		return this._currentRegion;
	}
	
	public void setClickListener(ClickListener c) {
		this._clickListener = c;
	}

	// THere's already a draw from our inherited class. Sigh. RW = Radiant Wrench
	public void rwDraw(SpriteBatch _spriteBatch) {
		// Calculating Y is complicated (inverted Y). Sigh. Just accept it, it's experimentally derived.
		_spriteBatch.draw(this._currentRegion, this.x, ScreenController.getCurrentScreen().getHeight() - this.y - this.height);
	}
}