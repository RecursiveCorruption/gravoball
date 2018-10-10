package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


public class Comet extends Ball {
    Comet(float x, float y) {
        super(x, y, 8.f, 100.f, new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1.0f));
    }

    @Override
    Vector2 calcAcc(GravoBallGame game, float dt) {
        return calcAttractionTo(game.getPlayer()).scl(0.5f);
    }
}

