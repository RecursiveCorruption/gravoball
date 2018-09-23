package com.recursive.corruption.gravoball;
import com.badlogic.gdx.math.Vector2;
interface Physics {
   final float playableConstant = 9.8f;
    Vector2 gravityforce (Ball b);
    Vector2 getAccelaration(Vector2 force) ;
    void changeVelocity(Vector2 force);
}
