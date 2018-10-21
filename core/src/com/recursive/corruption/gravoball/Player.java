package com.recursive.corruption.gravoball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Player extends Ball {
    Player(float x, float y) {
        super(x, y, 6.f, 1000.f, new Color(1.0f, .1f, .1f, 1.0f));
    }

    public void updateBall(GravoBallGame game, float playerDt) {
        pos.x = Gdx.input.getX();
        pos.y = Gdx.graphics.getHeight() - Gdx.input.getY();
        game.scorePoints(playerDt);
    }

    @Override
    Vector2 calcAcc(GravoBallGame game, float dt) {
        return new Vector2(0f, 0f);
    }
}
