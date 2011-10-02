package com.deengames.radiantwrench.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class Text implements Drawable {
	private int _x = 0;
	private int _y = 0;
	private int _z = 0;
	private String _text = "";
	private BitmapFont _font;

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
	
	public int getZ() {
		return this._z;
	}

	public void setZ(int z) {
		this._z = z;
	}
	
	public String getDisplayText() {
		return this._text;
	}
	public void setDisplayText(String text) {
		this._text = text;
	}

	public int getWidth() {
		return this._text.length();
	}

	public int getHeight() {
		return (int)Math.ceil(this._font.getLineHeight());
	}
	
	public BitmapFont getFont() {
		return this._font;
	}
	
	public void setFont(BitmapFont font) {
		this._font = font;
	}
	
}
