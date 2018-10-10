package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Chaser extends Ball implements Physics {
    Chaser(float x, float y) {
        super(x, y, 6.f, 100.f, new Color(.2f, .5f, 1.0f, 1.0f));
    }
    
    private float duration = 0;

    public void update(GravoBallGame game, float chaserDt) {
        Player player = game.getPlayer();
        duration = chaserDt;
        Vector2 forceFromPlayer = gravityforce(player);
        changePosition(forceFromPlayer);
        changeVelocity(forceFromPlayer);
    }

    public void changePosition(Vector2 force) {
        Vector2 acceleration = getAccelaration(force);
        x += velocity.x * duration;
        y += velocity.y * duration;
    }

    @Override
    public void changeVelocity(Vector2 force) {
        Vector2 acceleration = getAccelaration(force);
        velocity.x += acceleration.x * duration;
        velocity.y += acceleration.y * duration;
        velocity.x = velocity.x * 0.99f;
        velocity.y = velocity.y * 0.99f;
    }

    @Override
    public Vector2 getAccelaration(Vector2 force) {
        float xAcceleration = force.x / mass;
        float yAcceleration = force.y / mass;
        return new Vector2(xAcceleration, yAcceleration);
    }

    @Override
    public Vector2 gravityforce(Ball b) {
        float directionInX = Math.abs(b.x - x) / (b.x - x);
        float directionInY = Math.abs(b.y - y) / (b.y - y);
        float r = (float) (Math.sqrt(((b.x - x) * (b.x - x) + (b.y - y) * (b.y - y))));
        float totalForce = gravity * b.mass * mass / r * r;
        float forceInX = totalForce * (b.x - x) / r;
        float forceInY = totalForce * (b.y - y) / r;
        return new Vector2(forceInX, forceInY);
    }
}
