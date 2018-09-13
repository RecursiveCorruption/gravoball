package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;

abstract public class Ball {
    Color color;
    float x, y, radius;


    Ball(float x, float y, float radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    final void render(Renderer renderer) {
        renderer.circle(x + radius / 2.f, y + radius / 2.f, radius, color);
    }

    abstract void update(GravoBallGame game);
}
