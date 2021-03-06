/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.deengames.ilmica;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.deengames.ilmica.screens.QuizScreen;
import com.deengames.ilmica.screens.ReviewScreen;
import com.deengames.ilmica.screens.SplashScreen;
import com.deengames.ilmica.screens.TitleScreen;
import com.deengames.radiantwrench.controller.ScreenController;
import com.deengames.radiantwrench.core.Game;
import com.deengames.radiantwrench.utils.RadiantWrenchException;

public class IlmicaGame extends Game implements ApplicationListener {
	
	public void create() {
		ScreenController.showScreen(new SplashScreen());
		//ScreenController.showScreen(new QuizScreen("Fundamentals of Tawheed 1.0"));
		super.create();
	}
}
