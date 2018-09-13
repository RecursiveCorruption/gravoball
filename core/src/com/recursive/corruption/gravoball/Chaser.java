package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;

public class Chaser extends Ball {
    Chaser(float x, float y) {
        super(x, y, 6.f, new Color(.2f, .5f, 1.0f, 1.0f));
    }

    public void update(GravoBallGame game) {
        Player player = game.getPlayer();
        float dx = player.x - x;
        float dy = player.y - y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        x += dx / dist;
        y += dy / dist;
    }
}
