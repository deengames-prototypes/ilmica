package com.deengames.radiantwrench.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class Screen {
	
	ArrayList<Sprite> _sprites = new ArrayList<Sprite>();
	ArrayList<Text> _texts = new ArrayList<Text>();
		
	public Sprite[] getSprites() { 
		return this._sprites.toArray(new Sprite[0]);
	}
	
	public Text[] getTexts() {
		return this._texts.toArray(new Text[0]);
	}
	
	public void initialize() {
		
	}
	
	public void destroy() {
		this._sprites.clear();
	}
	
	public Sprite addSprite(String fileName) {
		Sprite s = new Sprite(fileName);
		this._sprites.add(s);
		return s;
	}
	
	public Text addText(String text) {
		Text t= new Text(text);
		this._texts.add(t);
		return t;
	}
	
	public int getWidth() {
		return Gdx.graphics.getWidth();
	}
	
	public int getHeight() {
		return Gdx.graphics.getHeight();
	}
}
