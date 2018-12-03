package com.recursive.corruption.gravoball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Player extends Ball {

    private int health = 100;

    Player(float x, float y) {
        super(x, y, 6.f, 1000.f, new Color(1.0f, .1f, .1f, 1.0f));
    }

    public void updateBall(GravoBallGame game, float playerDt) {
        pos.x = Gdx.input.getX();
        pos.y = Gdx.graphics.getHeight() - Gdx.input.getY();

        getLivingStatus();

        game.scorePoints(playerDt);
    }

    // TODO death GUI update
    // Am I alive?
    private boolean getLivingStatus() {
        if (health <= 0) {
            System.out.println("You are very dead");
            return false;
        } else {
            System.out.println(health);
            return true;
        }
    }


    public int getHealth() {
        return health;
    }

    /**
     * Hurt the player when a physics object collides (called in GravoBallGame tryCollideWithPlayer())
     * @param physicsEntity the entity; a higher mass, velocity -> more damage
     * @return damage dealt to player
     */
    public int hurt(PhysicsEntity physicsEntity) {
        int damage = 1;
        damage *= physicsEntity.getMass();
        damage *= physicsEntity.getVel().len();
        damage /= 200;
        health -= damage;
        return damage;
    }

    @Override
    Vector2 calcAcc(GravoBallGame game, float dt) {
        return new Vector2(0f, 0f);
    }
}
