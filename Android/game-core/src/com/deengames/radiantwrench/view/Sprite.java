package com.deengames.radiantwrench.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;
import com.deengames.radiantwrench.utils.RadiantWrenchException;

public class Sprite implements Comparable<Sprite>, Drawable, Clickable {
	
	protected int _x = 0;
	protected int _y = 0;
	protected int _z = 0;
	protected float _scale = 1f;
	
	protected Texture _texture;
	private String _fileName = "";
	
	protected int _rotationAngle = 0;
	protected float _alpha = 1;
	protected float _alphaRate = 0;

	private ClickListener _clickListener;
	private boolean _wasClicked = false;

	public Sprite(String fileName) {
		this._fileName = fileName;
		this.loadTexture();
	}
	
	public int getX() {
		return _x;
	}
	public void setX(int x) {
		this._x = x;
	}
	public int getY() {
		return _y;
	}
	public void setY(int y) {
		this._y = y;
	}

	public int getZ() {
		return _z;
	}

	public void setZ(int z) {
		this._z = z;
	}
	
	public void setScale(float scale) {
		this._scale = scale;
	}
	
	public float getScale() {
		return this._scale;
	}
	
	private void loadTexture() {
		_texture = new Texture(Gdx.files.internal(this._fileName));
	}
	
	public Texture getTexture() {
		return this._texture;
	}

	public int getRotationAngle() {
		return _rotationAngle;
	}

	public void setRotationAngle(int _rotationAngle) {
		this._rotationAngle = _rotationAngle;
	}

	public float getAlpha() {
		return _alpha;
	}

	public void setAlpha(float alpha) {
		if (alpha < 0) {
			this._alpha = 0;
		} else if (alpha > 1) {
			this._alpha = 1;
		} else {
			this._alpha = alpha;
		}
	}
	
	public float getAlphaRate() {
		return _alphaRate;
	}

	public void setAlphaRate(float _alphaRate) {
		this._alphaRate = _alphaRate;
	}

	/**
	 * Called every frame before rendering.
	 */
	public void update() {
		
	}

	@Override
	// For sorting
	public int compareTo(Sprite o) {
		int myZ = this.getZ();
		int theirZ = o.getZ();
		if (myZ < theirZ) {
			return -1;
		} else if (myZ > theirZ) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return this._fileName;
	}

	public int getOriginalWidth() {
		return this._texture.getWidth();
	}

	public int getOriginalHeight() {
		return this._texture.getHeight();
	}
	
	public String getFileName() {
		return this._fileName;
	}	

	public int getHeight() {
		return Math.round(this.getTexture().getHeight() * this._scale);
	}

	public int getWidth() {
		return Math.round(this.getTexture().getWidth() * this._scale);
	}
	
	public void setClickListener(ClickListener c) {
		this._clickListener = c;
	}
	
	public boolean touchDown(float x, float y, int pointer) {
		int yFromTop = (int)(ScreenController.getCurrentScreen().getHeight() - y);
		
		boolean touchDown = (x >= this.getX() && x <= this.getX() + this.getWidth() && 
				yFromTop >= this.getY() && yFromTop <= this.getY() + this.getHeight());
		
		if (touchDown) {
			this._wasClicked = true;
		}

		return touchDown;
	}

	public void touchUp(float x, float y, int pointer) {
		if (this._wasClicked) {
			if (this._clickListener != null) {
				this._clickListener.onClick(this);
			}
			
			this._wasClicked  = false;
		}
	}
	
	public void draw(SpriteBatch spriteBatch) {
		int screenHeight = ScreenController.getCurrentScreen().getHeight();
		Texture t = this._texture;
		
		spriteBatch.setColor(new Color(1, 1, 1, this._alpha));
		spriteBatch.draw(t,  0f + this._x, 0f + screenHeight - this.getHeight() - this._y,
				this.getWidth() / 2, this.getHeight() / 2, // origin 
				this.getWidth(), this.getHeight(), // draw scaled
				1, 1, 0, // Scale to (1, 1), rotation = 0
				0, 0, this.getOriginalWidth(), this.getOriginalHeight(),
				false, false);
	}
}
