package com.deengames.radiantwrench.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Text {
	private int _x = 0;
	private int _y = 0;
	private String _text = "";

	public Text(String text) {
		this._text = text;
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
	
	public String getDisplayText() {
		return this._text;
	}
	public void setDisplayText(String text) {
		this._text = text;
	}
	
}
