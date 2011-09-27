package com.deengames.radiantwrench.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Sprite {
	
	private int _x = 0;
	private int _y = 0;
	private Texture _texture;
	private String _fileName = "";

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
	
	public void loadTexture() {
		_texture = new Texture(Gdx.files.internal(this._fileName));
	}
	
	public Texture getTexture() {
		return this._texture;
	}
	
}
