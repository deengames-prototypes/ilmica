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
public class ImageCheckbox extends Image implements Clickable {
	
	private TextureRegion _unchecked;
	private TextureRegion _checked;
	private Texture _texture;
	private String _fileName;
	
	private boolean _isChecked = false;

	private ClickListener _clickListener;
	
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

	public ImageCheckbox(String fileName) {
		super("Checkbox");
		this._fileName = fileName;
		
		this.loadTexture();
		Texture t = this.getTexture();
		
		int halfWidth = t.getWidth() / 2;
		int width = t.getWidth();
		
		this.setUncheckedRegion(new TextureRegion(t, 0, 0, halfWidth, t.getHeight()));
		this.setCheckedRegion(new TextureRegion(t, halfWidth, 0, halfWidth, t.getHeight()));
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		int yFromTop = (int)(ScreenController.getCurrentScreen().getHeight() - y);
		
		boolean touchDown = (x >= this.x && x <= this.x + this.getScaledWidth() && 
				yFromTop >= this.y && yFromTop <= this.y + this.getScaledHeight());
		
		if (touchDown) {
			
			this._isChecked = !this._isChecked;
			
			if (this._isChecked) {
				this.region = this._checked;
			} else {
				this.region = this._unchecked;
			}
			
			if (this._clickListener != null) {
				this._clickListener.onClick(this);
			}
		}

		return touchDown;
	}

	public void loadTexture() {
		_texture = new Texture(Gdx.files.internal(this._fileName));
		this.width = this._texture.getWidth() / 2;
		this.height = this._texture.getHeight();
	}

	public Texture getTexture() {
		return this._texture;
	}

	public void setUncheckedRegion(TextureRegion unchecked) {
		this._unchecked = unchecked;
		
		if (this.region != null) {
			this.region.setRegion(this._unchecked);			
		}
	}

	public void setCheckedRegion(TextureRegion checked) {
		this._checked = checked;
		
		if (this.region != null) {
			this.region.setRegion(this._checked);			
		}
	}

	public void setClickListener(ClickListener c) {
		this._clickListener = c;
	}

	public void setIsChecked(boolean value) {
		this._isChecked = value;
		if (this._isChecked) {
			this.region = this._checked;			
		} else {
			this.region = this._unchecked;
		}
	}
	
	public int getScaledWidth() {
		return Math.round(this.width * this.scaleX);
	}
	
	public int getScaledHeight() {
		return Math.round(this.height * this.scaleY);
	}

	private void verifyRegionIsSet() {
		if (this.region == null) {
			this.region = this._unchecked;
		}
	}
	
	public boolean getIsChecked() {
		return this._isChecked;
	}
	
	// There's already a draw from our inherited class. Sigh. RW = Radiant Wrench
	public void rwDraw(SpriteBatch spriteBatch) {
		verifyRegionIsSet();
		spriteBatch.draw(this.region, this.x, 
				ScreenController.getCurrentScreen().getHeight() - this.y - this.getScaledHeight(),
				this.getScaledWidth(), this.getScaledHeight());
	}

}