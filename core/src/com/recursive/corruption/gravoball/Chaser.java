package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
public class Chaser extends Ball implements Physics{
    Chaser(float x, float y) {
        super(x, y, 6.f,10.f , new Color(.2f, .5f, 1.0f, 1.0f));
    }


        private float Duration = 0;
    public void update(GravoBallGame game,float chaserDt) {
        Player player = game.getPlayer();
        float dx = player.x - x;
        float dy = player.y - y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        x += dx / dist;
        y += dy / dist;

        Duration = chaserDt;
    }

    @Override
    public void changeVelocity(Vector2 force ) {
        Vector2 acceleration = this.getAccelaration(force);
        this.velocity.x += acceleration.x*Duration;
        this.velocity.y += acceleration.y*Duration;

    }

    @Override
    public Vector2 getAccelaration(Vector2 force)
    {
        float xAcceleration = force.x/mass;
        float yAcceleration = force.y/mass;
        Vector2 acceleration = new Vector2(xAcceleration,yAcceleration);
        return acceleration;
    }

    @Override
    public Vector2 gravityforce(Ball b) {
        float forceInX = playableConstant*b.mass*this.mass/((b.x-this.x)*(b.x-this.x));
        float forceInY = playableConstant*b.mass*this.mass/((b.y-this.y)*(b.y-this.y));
        Vector2 res =  new Vector2(forceInX,forceInY);
        return res;
    }
}
