package com.deengames.ilmica.model;

import com.badlogic.gdx.Gdx;

/**
 * A wrapper around the underlying library. Sigh.
 */
public class FileHelper {
	public static String readFile(String fileName) {
		return Gdx.files.internal(fileName).readString();
	}
}
