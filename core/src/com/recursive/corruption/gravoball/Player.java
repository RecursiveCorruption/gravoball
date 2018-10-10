package com.recursive.corruption.gravoball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Player extends Ball implements Physics {
    Player(float x, float y) {
        super(x, y, 6.f, 9000.f, new Color(1.0f, .1f, .1f, 1.0f));
    }

    private float Duration = 0;

    public void update(GravoBallGame game, float playerDt) {
        x = Gdx.input.getX();
        y = Gdx.graphics.getHeight() - Gdx.input.getY();
        Duration = playerDt;
    }

    @Override

    public Vector2 gravityforce(Ball b) {
        float forceInX = gravity * b.mass * mass / ((b.x - x) * (b.x - x));
        float forceInY = gravity * b.mass * mass / ((b.y - y) * (b.y - y));
        return new Vector2(forceInX, forceInY);
    }

    @Override
    public Vector2 getAccelaration(Vector2 force) {
        float xAcceleration = force.x / mass;
        float yAcceleration = force.y / mass;
        return new Vector2(xAcceleration, yAcceleration);
    }

    @Override
    public void changeVelocity(Vector2 force) {
        Vector2 acceleration = getAccelaration(force);
        velocity.x += acceleration.x * Duration;
        velocity.y += acceleration.y * Duration;
    }
}
