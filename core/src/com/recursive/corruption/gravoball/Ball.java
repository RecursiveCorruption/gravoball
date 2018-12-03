package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

abstract public class Ball extends PhysicsEntity {
    private Color color;

    Ball(float x, float y, float radius, float mass, Color color) {
        super(new Vector2(x, y), radius, mass);
        this.color = color;
    }

    final void render(Renderer renderer) {
        renderer.circle(pos.x + radius / 2.f, pos.y + radius / 2.f, radius, color);
    }

    void updateBall(GravoBallGame game, float dt) { }

    final void update(GravoBallGame game, float dt) {
        updateBall(game, dt);
        updatePhysics(game, dt);
    }

    protected void setColor(Color newColor) {
        this.color = newColor;
    }
}
