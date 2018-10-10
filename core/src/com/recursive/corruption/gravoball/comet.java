package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


public class comet extends Ball implements Physics {
    comet(float x, float y) {
        super(x, y, 8.f, 100.f, new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1.0f));
    }

    float duration = 0.f;

    public void update(GravoBallGame game, float chaserDt) {
        Player player = game.getPlayer();
        duration = chaserDt;
        final float dx = player.x - x;
        final float dy = player.y - y;
        x += dx / 10;
        y += dy / 10;
    }

    @Override
    public void changeVelocity(Vector2 force) { }

    @Override
    public Vector2 getAccelaration(Vector2 force) {
        return null;
    }

    @Override
    public Vector2 gravityforce(Ball b) {
        return null;
    }
}

