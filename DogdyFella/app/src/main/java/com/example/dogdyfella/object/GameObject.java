
package com.example.dogdyfella.object;

import android.graphics.Canvas;

import com.example.dogdyfella.Player;

public abstract class GameObject{
    protected double posX, posY;
    protected double velX, velY;

    public GameObject(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void draw(Canvas canvas);

    public abstract void update();

    protected double getPosX() {
        return posX;
    }

    protected double getPosY() {
        return posY;
    }

    protected static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return  Math.sqrt(Math.pow(obj2.getPosX() - obj1.getPosX(), 2)) +
                Math.sqrt(Math.pow(obj2.getPosY() - obj1.getPosY(), 2));
    }
}
