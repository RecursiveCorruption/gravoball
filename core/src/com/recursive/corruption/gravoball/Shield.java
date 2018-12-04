package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// this draws a "Shield" outline around the player
// Note: shield does not actually protect against damage
// This is just a pseudo-animation
public class Shield extends Entity {

    private final int FRAMES_UNTIL_REMOVE_SHIELD_IMAGE = 3;

    private Player player;
    private int currentFrame;

    public Shield(Player player) {
        this.player = player;
        currentFrame = 0;
    }

    final void render(Renderer renderer) {
        renderer.circle(player.getPos().x, player.getPos().y, player.getRadius()*2, new Color(1.0f, .25f, .25f, 1f));

        // TODO figure out how to use lineRendering for an outline
        // Renderer lineRenderer = new Renderer(ShapeRenderer.ShapeType.Line);
        // lineRenderer.begin();
        // lineRenderer.circle(player.getPos().x, player.getPos().y, player.getRadius()*2, player.getColor());
    }

    final void update(GravoBallGame game, float dt) {
        currentFrame++;
        if (currentFrame >= FRAMES_UNTIL_REMOVE_SHIELD_IMAGE) {
            game.removeEntity(this);
        }
    }
}
