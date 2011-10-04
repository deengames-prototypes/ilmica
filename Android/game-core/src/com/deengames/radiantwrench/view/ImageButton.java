package com.deengames.radiantwrench.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

// Adapted from http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=2168&sid=a5ac07a6f80769c1b8405b8ba181e913#p11486
public class ImageButton extends Image {
	
	private TextureRegion _currentRegion;
	private TextureRegion _down;
	private TextureRegion _up;
	private Texture _texture;
	private String _fileName;

	private ClickListener _clickListener;

	public ImageButton(String fileName) {
		super("Button");
		this._fileName = fileName;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		// WHYYYYYY??!?!?!
		//boolean touchDown = super.touchDown(x, y, pointer);
		boolean touchDown = (x >= this.x && x <= this.x + this.width && 
				y >= this.y && y <= this.y + this.height);
		
		if (touchDown) {
			if (this.region == null) {
				this.region  = this._down;
			} else {
				this.region.setRegion(_down);
			}
			
			if (this._clickListener != null) {
				this._clickListener.onClick(this);
			}
			this._currentRegion = this._down;
		}

		return touchDown;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		this.region.setRegion(_up);
		this._currentRegion = this._up;
		super.touchUp(x, y, pointer);
	}

	public interface ClickListener {
		public void onClick(ImageButton clickedOn);
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
	}

	public TextureRegion getCurrentRegion() {
		if (this._currentRegion == null) {
			this._currentRegion = this._down;
		}
		
		return this._currentRegion;
	}
	
	public void setClickListener(ClickListener c) {
		this._clickListener = c;
	}
}