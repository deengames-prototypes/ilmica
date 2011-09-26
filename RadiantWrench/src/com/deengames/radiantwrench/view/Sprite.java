package com.deengames.radiantwrench.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Sprite {
	
	private int _x = 0;
	private int _y = 0;
	private Texture _texture;

	public Sprite(String fileName) {
		this._texture = new Texture(Gdx.files.internal(fileName));
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
	
	public Texture getTexture() {
		return this._texture;
	}
	
}
