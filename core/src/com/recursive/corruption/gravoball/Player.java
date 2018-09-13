package com.recursive.corruption.gravoball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Player extends Ball {
    Player(float x, float y) {
        super(x, y, 6.f, new Color(1.0f, .1f, .1f, 1.0f));
    }

    public void update(GravoBallGame game) {
        x = Gdx.input.getX();
        y = Gdx.graphics.getHeight() - Gdx.input.getY();
    }
}
