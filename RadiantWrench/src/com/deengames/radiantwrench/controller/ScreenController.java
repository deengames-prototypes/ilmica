package com.deengames.radiantwrench.controller;

import com.deengames.radiantwrench.core.Game;
import com.deengames.radiantwrench.view.Screen;

// Static class
public abstract class ScreenController {
	
	static Screen _currentScreen;
	
	public static Screen GetCurrentScreen() { 
		return _currentScreen;
	}
	
	public static void ShowScreen(Screen s) {
		if (_currentScreen != null) {
			_currentScreen.destroy();
		}
		
		_currentScreen = s;
		s.initialize();
	}
}
