package com.recursive.corruption.gravoball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;




public class Player extends Ball {

    private int health = 100;

    private boolean isDead = false;

    Player(float x, float y) {
        super(x, y, 6.f, 1.f, new Color(1.0f, .1f, .1f, 1.0f));

    }

    public void updateBall(GravoBallGame game, float playerDt) {
        pos.x = Gdx.input.getX();
        pos.y = Gdx.graphics.getHeight() - Gdx.input.getY();

        // regen health every frame
        if (!isDead && health < 100) {
            health++;
        }

        //gain mass every frame exponentially - makes a dramatic start
        if (mass < 250) {
            mass *= 1.027;
            mass += 0.25;
        } else {
            mass += 0.5;
        }
        //System.out.println("Mass: " + mass);

        getLivingStatus();

        game.scorePoints(playerDt);
    }

    // TODO death GUI update
    // Am I alive?
    private boolean getLivingStatus() {
        if (health <= 0) {
            if (!isDead) {
                //first frame being dead
                System.out.println("You are very dead");
            }
            isDead = true;
            radius = 9999999;
            return false;
        } else {
            System.out.println("HP: " + health);
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
        int damage = 11;
        //damage += physicsEntity.getMass() / 7;
        //damage /= 7;
        health -= damage;
        if (!isDead) {
            System.out.println("----------DMG: " + damage);
        }
        return damage;
    }

    @Override
    Vector2 calcAcc(GravoBallGame game, float dt) {
        return new Vector2(0f, 0f);
    }
}
