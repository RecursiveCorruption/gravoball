package com.recursive.corruption.gravoball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Renderer {

    private ShapeRenderer shapeRenderer= new ShapeRenderer();

    private ShapeRenderer.ShapeType shapeType;

    public Renderer() {
        shapeType = ShapeRenderer.ShapeType.Filled;
    }

    public Renderer(ShapeRenderer.ShapeType shapeType) {
        this.shapeType = shapeType;
    }


    public void begin() {
        shapeRenderer.begin(shapeType);
    }

    public void end() {
        shapeRenderer.end();
    }

    public void circle(float x, float y, float radius, Color color) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }
}
