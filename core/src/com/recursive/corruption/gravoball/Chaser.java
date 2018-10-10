package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Chaser extends Ball {
    Chaser(float x, float y) {
        super(x, y, 6.f, 10.f, new Color(.2f, .5f, 1.0f, 1.0f));
    }

    @Override
    public Vector2 calcAcc(GravoBallGame game, float dt) {
        return calcAttractionTo(game.getPlayer());
    }
}
