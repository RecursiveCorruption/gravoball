package com.recursive.corruption.gravoball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Random;
//
import java.util.Timer;
import java.util.TimerTask;


public class GravoBallGame extends ApplicationAdapter {
    private Renderer renderer;
    private ArrayList<Ball> balls = new ArrayList<Ball>();
    private ArrayList<Ball> ballsToRemove = new ArrayList<Ball>();
    private Player player;
    private Random random;
    //private  Timer timer;
    Timer timer = new Timer();
    private  long period = 1000L;

    TimerTask repeatedTask = new TimerTask() {
        public void run() {
            if (balls.size() < 50) {
                balls.add(new comet(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));

            }

        }
    };

    long delay  = 1000L;



    Player getPlayer() {
        return player;
    }

    @Override
    public void create() {
        renderer = new Renderer();
        random = new Random();
        player = new Player(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight());
        balls.add(player);
        timer.scheduleAtFixedRate(repeatedTask,period,delay);
        ballsToRemove = new ArrayList<Ball>();
    }

    public void removeBall(Ball ball) {
        ballsToRemove.add(ball);
    }

    private void update() {
        if (balls.size() < 45) {
            balls.add(new Chaser(random.nextFloat() * Gdx.graphics.getWidth(), random.nextFloat() * Gdx.graphics.getHeight()));

            }

        for (Ball ball : balls) {
            ball.update(this, Gdx.graphics.getDeltaTime());
        }
        balls.removeAll(ballsToRemove);
        ballsToRemove.clear();
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
