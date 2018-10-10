package com.recursive.corruption.gravoball;

import com.badlogic.gdx.math.Vector2;

interface Physics {
    float gravity = 0.098f; // Set to a number to make playable
    Vector2 gravityforce(Ball b);
    Vector2 getAccelaration(Vector2 force);
    void changeVelocity(Vector2 force);
}
