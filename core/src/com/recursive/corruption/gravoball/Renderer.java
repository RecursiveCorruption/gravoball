package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Renderer {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public void begin() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    public void end() {
        shapeRenderer.end();
    }

    public void circle(float x, float y, float radius, Color color) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }
}
