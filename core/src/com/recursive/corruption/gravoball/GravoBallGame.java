package com.recursive.corruption.gravoball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Random;


public class GravoBallGame extends ApplicationAdapter {
    private Renderer renderer;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entitiesToAdd = new ArrayList<>();
    private ArrayList<Entity> entitiesToRemove = new ArrayList<>();
    private Player player;
    private Random random;
    private float timeTillSpawn;
    private float points;

    private static final float SPAWN_PERIOD = 0.37f; // in seconds
    private static final int MAX_ENTITIES = 5555;   //TOO MUCH??

    Player getPlayer() {
        return player;
    }

    float getPoints() {
        return points;
    }

    void scorePoints(float amount) {
        points += amount;
    }

    @Override
    public void create() {
        renderer = new Renderer();
        random = new Random();
        player = new Player(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight());
        entities.add(player);
        entitiesToRemove = new ArrayList<>();
        timeTillSpawn = 0f;
        points = 0;
    }

    public void removeEntity(Entity ball) {
        entitiesToRemove.add(ball);
    }

    private void update(float dt) {
        entities.addAll(entitiesToAdd);
        entitiesToAdd.clear();
        updateSpawner(dt);

        for (Entity entity : entities) {
            entity.update(this, dt);
            tryCollideWithPlayer(entity);
        }

        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
    }

    // colliding with the player will hurt the player
    private boolean tryCollideWithPlayer(Entity entity) {
        if (entity instanceof Ball && !entity.equals(player)) {
            Ball ballEntity = (Ball) entity;
            double distToPlayer = ballEntity.calcDistanceTo(player);
            if (distToPlayer < player.getRadius() + ballEntity.getRadius()) {
                player.hurt(ballEntity);
                entitiesToAdd.add(new Shield(player));
                return true;
            }
        }
        return false;
    }

    private void updateSpawner(float dt) {
        timeTillSpawn -= dt;
        if (timeTillSpawn < 0f && entities.size() < MAX_ENTITIES) {
            timeTillSpawn = SPAWN_PERIOD;
            if (random.nextFloat() < 0.85) {
                entities.add(new Chaser(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));
            } else {
                entities.add(new RandomComet(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));
                //entities.add(new Comet(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));
            }
        }
    }

    public ArrayList<Entity> getEntities() {
        return entities;
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
