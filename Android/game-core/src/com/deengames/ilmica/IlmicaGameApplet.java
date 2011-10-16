package com.deengames.ilmica;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;


public class IlmicaGameApplet extends LwjglApplet
{
    private static final long serialVersionUID = 1L;
    public IlmicaGameApplet()
    {
        super(new IlmicaGame(), false);
    }
}