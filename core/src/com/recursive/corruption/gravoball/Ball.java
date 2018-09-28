package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
abstract public class Ball{
    Color color;
    float x, y, radius,mass;
    Vector2 velocity;
    //Tuple3<float,float,float> velocity;


    Ball(float x, float y, float radius,float mass, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.mass = mass;
        this.velocity = new Vector2(0,0);
    }

    final void render(Renderer renderer) {
        renderer.circle(x + radius / 2.f, y + radius / 2.f, radius, color);
    }

    abstract void update(GravoBallGame game,float dt);
}
