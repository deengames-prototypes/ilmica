package com.deengames.radiantwrench.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.deengames.radiantwrench.core.Game;
import com.deengames.radiantwrench.utils.Action;
import com.deengames.radiantwrench.utils.ClickListener;
import com.deengames.radiantwrench.utils.Clickable;

public class Text implements Drawable, Clickable {
	
	private int _x = 0;
	private int _y = 0;
	private int _z = 0;
	private String _text = "";
	private BitmapFont _font;
	private boolean _wasDown = false;

	private ClickListener _clickListener;
	
	public Text(String text) {
		this._text = text;
		this._font = Game.getCurrentGame().getDefaultFont();
	}
	
	public boolean touchDown(float x, float y, int pointer) {
		TextBounds bounds = this._font.getBounds(this._text);
		
		boolean touchDown = (x >= this._x && x <= this._x + bounds.width && 
				y >= this._y && y <= this._y + bounds.height);
		
		if (touchDown) {
			this._wasDown = true;
		}

		return touchDown;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		
		if (this._wasDown) {
			if (this._clickListener != null) {
				this._clickListener.onClick(this);
			}
			
			this._wasDown = false;
		}
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
		return Math.round(this._font.getBounds(this._text).width);
	}

	public int getHeight() {
		return Math.round(this._font.getBounds(this._text).height);
	}
	
	public BitmapFont getFont() {
		return this._font;
	}
	
	public void setFont(BitmapFont font) {
		this._font = font;
	}
	
	public void setClickListener(ClickListener c) {
		this._clickListener = c;
	}
}
