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
import com.deengames.radiantwrench.utils.RadiantWrenchException;

// Adapted from http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=2168&sid=a5ac07a6f80769c1b8405b8ba181e913#p11486
public class ImageButton extends Image implements Clickable {
	
	private TextureRegion _down;
	private TextureRegion _up;
	private Texture _texture;
	private String _fileName;
	private boolean _wasClicked = false;

	private ClickListener _clickListener;

	public ImageButton(String fileName) {
		super("Button");
		this._fileName = fileName;
		
		this.loadTexture();
		Texture t = this.getTexture();
		
		int halfWidth = t.getWidth() / 2;
		int width = t.getWidth();
		
		this.setUpRegion(new TextureRegion(t, 0, 0, halfWidth, t.getHeight()));
		this.setDownRegion(new TextureRegion(t, halfWidth, 0, halfWidth, t.getHeight()));
		
		this.rotation = 123;
		this.originX = width;
		this.originY = 999;
	}
	
	public void setScale(float scale) {
		this.scaleX = scale;
		this.scaleY = scale;
	}
	
	public float getScale() {
		if (this.scaleX != this.scaleY) {
			throw new RadiantWrenchException("Scale X and Y don't match.");
		} else {
			return this.scaleX;
		}
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		int yFromTop = (int)(ScreenController.getCurrentScreen().getHeight() - y);
		
		boolean touchDown = (x >= this.x && x <= this.x + this.width && 
				yFromTop >= this.y && yFromTop <= this.y + this.height);
		
		if (touchDown) {
			this.region  = this._down;
			this._wasClicked = true;
		}

		return touchDown;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		this.region = this._up;
		super.touchUp(x, y, pointer);
		
		if (this._wasClicked) {
			if (this._clickListener != null) {
				this._clickListener.onClick(this);
			}
			
			this._wasClicked = false;
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
		if (this.region != null) {
			this.region.setRegion(this._up);			
		}
	}

	public void setClickListener(ClickListener c) {
		this._clickListener = c;
	}
	
	private void verifyRegionIsSet() {
		if (this.region == null) {
			this.region = this._up;
		}
	}
	
	public int getScaledWidth() {
		return Math.round(this.width * this.scaleX);
	}
	
	public int getScaledHeight() {
		return Math.round(this.height * this.scaleY);
	}


	// There's already a draw from our inherited class. Sigh. RW = Radiant Wrench
	public void rwDraw(SpriteBatch spriteBatch) {
		verifyRegionIsSet();
		
		// Calculating Y is complicated (inverted Y). Sigh. Just accept it, it's experimentally derived.
		spriteBatch.draw(this.region, this.x,
				ScreenController.getCurrentScreen().getHeight() - this.y - this.height,
				this.scaleX * this.width, this.scaleY * this.height);
		
		/*
		 * Epic fail LOL, looked at the wrong class three times XD
		 
		float destX = this.x;
		float destY = ScreenController.getCurrentScreen().getHeight() - this.y - this.height;
		int srcX = (this.region == _up ? 0 : Math.round(this.width / 2)); 
				
		spriteBatch.draw(this._texture, destX, destY,
				this.originX, this.originY, this.getScaledWidth(), this.getScaledHeight(), // Use center origin
				this.originX, this.originY, this.rotation, // No scale/rotation, really
				srcX, 0, Math.round(this.width), Math.round(this.height), // Assume 2 frames
				false, false); // No flip
		*/
				
	}
}