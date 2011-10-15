package com.deengames.radiantwrench.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.utils.RadiantWrenchException;

public class SpriteSheet extends Sprite {

	private int _frameRow = 0;
	private int _frameIndex = 0;
	private int _frameWidth = 0;
	private int _frameHeight = 0;
	
	private int _horizontalFrames = 0;
	private int _verticalFrames = 0;
	
	public SpriteSheet(String fileName, int frameWidth, int frameHeight) {
		super(fileName);
		if (frameWidth <= 0) {
			throw new RadiantWrenchException("Frame width must be at least 1!");
		} else if (frameWidth > this._texture.getWidth()) {
			throw new RadiantWrenchException("Frame width (" + frameWidth + ") must be less than the texture width (" + this._texture.getWidth() + ")");
		} else if (frameHeight <= 0) {
			throw new RadiantWrenchException("Frame height must be at least 1!");
		} else if (frameWidth > this._texture.getHeight()) {
			throw new RadiantWrenchException("Frame height (" + frameHeight + ") must be less than the texture height (" + this._texture.getHeight() + ")");
		} else {
			// All signs point to "valid"
			this._frameWidth = frameWidth;
			this._frameHeight = frameHeight;
			this._horizontalFrames = this._texture.getWidth() / frameWidth;
			this._verticalFrames = this._texture.getHeight() / frameHeight;
		}
	}

	public int getFrameRow() {
		return _frameRow;
	}

	public void setFrameRow(int frameRow) {
		this._frameRow = frameRow;
	}

	public int getFrameIndex() {
		return _frameIndex;
	}

	public void setFrameIndex(int frameIndex) {
		this._frameIndex = frameIndex;
	}
	
	public int getWidth() {
		return Math.round(this._frameWidth * this._scale);
	}
	
	public int getHeight() {
		return Math.round(this._frameHeight * this._scale);
	}
	

	public int getOriginalWidth() {
		return this._texture.getWidth();
	}

	public int getOriginalHeight() {
		return this._texture.getHeight();
	}
	
	
	public void draw(SpriteBatch spriteBatch) {
		int screenHeight = ScreenController.getCurrentScreen().getHeight();
		Texture t = this._texture;
		
		float u = this._frameIndex * 1.0f / this._horizontalFrames; // Start X, as a percent
		float v = this._frameRow * 1.0f / this._verticalFrames; // Start Y, as a percent
		float u2 = (this._frameIndex + 1.0f) / this._horizontalFrames; // Stop X, as a percent
		float v2 = (this._frameRow + 1.0f) / this._verticalFrames; // Stop Y, as a percent
		
		//(0, 0, 0.5, 1) shows the first frame, with two frames
		//(0.5, 0, 1, 1) shows the second frame, with two frames
		
		spriteBatch.setColor(new Color(1, 1, 1, this._alpha));
		spriteBatch.draw(t,  0f + this._x, 0f + screenHeight - t.getHeight() - this._y,
				this.getWidth(), this.getHeight(), u, v, u2, v2);
	}
}
