package com.recursive.corruption.gravoball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GravoBallGame extends ApplicationAdapter {
    private Renderer renderer;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entitiesToRemove = new ArrayList<>();
    private Player player;
    private Random random;

    private Timer timer = new Timer();
    private static final long PERIOD = 1000L;
    private static final long DELAY = 1000L;

    private TimerTask repeatedTask = new TimerTask() {
        public void run() {
            if (entities.size() < 50) {
                entities.add(new Comet(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));
            }
        }
    };


    Player getPlayer() {
        return player;
    }

    @Override
    public void create() {
        renderer = new Renderer();
        random = new Random();
        player = new Player(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight());
        entities.add(player);
        timer.scheduleAtFixedRate(repeatedTask, PERIOD, DELAY);
        entitiesToRemove = new ArrayList<Entity>();
    }

    public void removeEntity(Entity ball) {
        entitiesToRemove.add(ball);
    }

    private void update() {
        if (entities.size() < 45) {
            entities.add(new Chaser(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));
        }

        for (Entity entity : entities) {
            entity.update(this, Gdx.graphics.getDeltaTime());
        }
        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();
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
        for (Entity entity : entities) {
            entity.render(renderer);
        }
        renderer.end();
    }
}
