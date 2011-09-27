package com.deengames.radiantwrench.view;

import java.util.ArrayList;

public class Screen {
	
	ArrayList<Sprite> _sprites = new ArrayList<Sprite>(); 
		
	public Sprite[] getSprites() { 
		return this._sprites.toArray(new Sprite[0]);
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
}
