package com.deengames.radiantwrench.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Sprite implements Comparable<Sprite> {
	
	private int _x = 0;
	private int _y = 0;
	private int _z = 0;
	
	private Texture _texture;
	private String _fileName = "";
	
	private int _rotationAngle = 0;
	private float _alpha = 1;
	private float _alphaRate = 0;

	public Sprite(String fileName) {
		this._fileName = fileName;
	}
	
	public int getX() {
		return _x;
	}
	public void setX(int _x) {
		this._x = _x;
	}
	public int getY() {
		return _y;
	}
	public void setY(int _y) {
		this._y = _y;
	}

	public int getZ() {
		return _z;
	}

	public void setZ(int _z) {
		this._z = _z;
	}
	
	public void loadTexture() {
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
}
