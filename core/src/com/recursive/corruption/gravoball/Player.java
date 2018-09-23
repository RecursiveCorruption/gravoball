package com.recursive.corruption.gravoball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Player extends Ball implements Physics {
    private float playerMass = 90;
    Player(float x, float y) {
        super(x, y, 6.f, 90 ,new Color(1.0f, .1f, .1f, 1.0f));
    }

    private float Duration = 0;
    public void update(GravoBallGame game,float playerDt) {
        x = Gdx.input.getX();
        y = Gdx.graphics.getHeight() - Gdx.input.getY();
        Duration = playerDt;
    }

    @Override

    public Vector2 gravityforce(Ball b) {
        return null;
    }

    @Override
    public Vector2 getAccelaration(Vector2 force) {
        return null;
    }

    @Override
    public void changeVelocity(Vector2 force) {

    }
}
