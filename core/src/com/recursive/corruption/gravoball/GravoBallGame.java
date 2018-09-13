package com.recursive.corruption.gravoball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Random;

public class GravoBallGame extends ApplicationAdapter {
    private Renderer renderer;
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private Player player;
    private Random random;

    Player getPlayer() {
        return player;
    }

    @Override
    public void create() {
        renderer = new Renderer();
        random = new Random();
        player = new Player(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight());
        balls.add(player);
    }

    private void update() {
        while (balls.size() < 20) {
            balls.add(new Chaser(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));
        }
        for (Ball ball : balls) {
            ball.update(this);
        }
    }

    @Override
    public void render() {
        update();

        Gdx.gl.glClearColor(0.8f, 0.8f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        renderer.begin();
        for (Ball ball : balls) {
            ball.render(renderer);
        }
        renderer.end();
    }
}
