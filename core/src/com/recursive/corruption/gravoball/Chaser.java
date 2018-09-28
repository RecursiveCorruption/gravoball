package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
public class Chaser extends Ball implements Physics{
    Chaser(float x, float y) {
        super(x, y, 6.f,100.f , new Color(.2f, .5f, 1.0f, 1.0f));
    }


        private float duration = 0;
    public void update(GravoBallGame game,float chaserDt) {
        Player player = game.getPlayer();
       /* float dx = player.x - x;
        float dy = player.y - y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        x += dx / dist;
        y += dy / dist;*/

        duration = chaserDt;
        Vector2 forceFromPlayer = gravityforce(player);
        this.changePosition(forceFromPlayer);
        this.changeVelocity(forceFromPlayer);
    }

    public void changePosition(Vector2 force) {
        Vector2 acceleration = this.getAccelaration(force);
        this.x += this.velocity.x* duration;
        this.y += this.velocity.y* duration;
    }
    @Override
    public void changeVelocity(Vector2 force ) {
        Vector2 acceleration = this.getAccelaration(force);
        this.velocity.x += acceleration.x* duration;
        this.velocity.y += acceleration.y* duration;
        this.velocity.x = this.velocity.x*0.99f;
        this.velocity.y = this.velocity.y*0.99f;

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
        float directionInX = Math.abs(b.x-this.x)/(b.x-this.x);
        float directionInY = Math.abs(b.y-this.y)/(b.y-this.y);
         float r =  (float)(Math.sqrt(((b.x-this.x)*(b.x-this.x) + (b.y-this.y)*(b.y-this.y))));
        float totalForce = gravity *b.mass*this.mass/r*r;
       float forceInX = totalForce*(b.x-this.x)/r;
        float forceInY = totalForce*(b.y-this.y)/r;
        Vector2 res =  new Vector2(forceInX,forceInY);
        return res;
    }
}
