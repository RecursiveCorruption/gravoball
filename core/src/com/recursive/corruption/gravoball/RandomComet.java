package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class RandomComet extends Comet {

    RandomComet(float x, float y) {
        super(x, y);
        double randomScaler = Math.random();
        this.radius = (float) ((randomScaler * 20.f) + 5);
        this.mass = this.radius * this.radius / 7;
        //this.setColor(new Color((float) randomScaler, (float) randomScaler, 1.0f - (float) randomScaler, 1.0f));
    }

    @Override
    Vector2 calcAcc(GravoBallGame game, float dt) {
        return calcAttractionTo(game.getPlayer()).scl(0.5f);
    }
}
