package com.recursive.corruption.gravoball;

import com.badlogic.gdx.math.Vector2;

/**
 * Any physics entity that exists in the world
 */
public abstract class PhysicsEntity extends Entity {
    Vector2 pos, vel;
    float radius, mass;
    static private final float GRAVITY = 2f, FRICTION = 0.999f, MIN_GRAV_DIST = 20f;

    PhysicsEntity(Vector2 pos, float radius, float mass) {
        this.pos = pos;
        this.vel = new Vector2(0f, 0f);
        this.radius = radius;
        this.mass = mass;
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }

    public Vector2 getVel() {
        return new Vector2(vel);
    }

    public float getRadius() {
        return radius;
    }

    public float getMass() {
        return mass;
    }

    public void updatePhysics(GravoBallGame game, float dt) {
        vel.mulAdd(calcAcc(game, dt), dt);
        vel.scl(FRICTION);
        pos.mulAdd(vel, dt);
    }

    abstract Vector2 calcAcc(GravoBallGame game, float dt);

    Vector2 calcAttractionTo(PhysicsEntity b) {
        Vector2 delta = new Vector2(b.pos).sub(pos);
        float dist = Math.max(MIN_GRAV_DIST, delta.len());

        // Technically shouldn't divide by mass, but allows variable acceleration
        return delta.scl((GRAVITY * b.mass) / (dist * (float)Math.log(dist)));
    }

    double calcDistanceTo(PhysicsEntity b) {
        Vector2 delta = new Vector2(b.pos).sub(pos);
        return delta.len();
    }
}
