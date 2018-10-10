package com.recursive.corruption.gravoball;

/**
 * Any updatable and renderable object in the world
 */
public abstract class Entity {
    abstract void render(Renderer renderer);
    abstract void update(GravoBallGame game, float dt);
}
