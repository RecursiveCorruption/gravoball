package com.recursive.corruption.gravoball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Random;


public class GravoBallGame extends ApplicationAdapter {
    private Renderer renderer;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entitiesToRemove = new ArrayList<>();
    private Player player;
    private Random random;
    private float timeTillSpawn = 0f;

    private static final float SPAWN_PERIOD = 0.1f; // in seconds

    Player getPlayer() {
        return player;
    }

    @Override
    public void create() {
        renderer = new Renderer();
        random = new Random();
        player = new Player(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight());
        entities.add(player);
        entitiesToRemove = new ArrayList<>();
        timeTillSpawn = 0f;
    }

    public void removeEntity(Entity ball) {
        entitiesToRemove.add(ball);
    }

    private void update(float dt) {
        updateSpawner(dt);

        for (Entity entity : entities) {
            entity.update(this, dt);
        }
        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
    }

    private void updateSpawner(float dt) {
        timeTillSpawn -= dt;
        if (timeTillSpawn < 0f && entities.size() < 45) {
            timeTillSpawn = SPAWN_PERIOD;
            if (random.nextFloat() < 0.85) {
                entities.add(new Chaser(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));
            } else {
                entities.add(new Comet(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));
            }
        }
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0.8f, 0.8f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        renderer.begin();
        for (Entity entity : entities) {
            entity.render(renderer);
        }
        renderer.end();
    }
}
