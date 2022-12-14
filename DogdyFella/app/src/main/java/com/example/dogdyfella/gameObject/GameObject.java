
package com.example.dogdyfella.gameObject;

import android.graphics.Canvas;

import com.example.dogdyfella.GameDisplay;

public abstract class GameObject{
    protected double posX, posY;
    protected double velX, velY;
    protected double directionX = 1, directionY;

    public GameObject(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);

    public abstract void update();

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    protected static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return  Math.sqrt(Math.pow(obj2.getPosX() - obj1.getPosX(), 2)) +
                Math.sqrt(Math.pow(obj2.getPosY() - obj1.getPosY(), 2));
    }

    protected double getDirectionX() {
        return directionX;
    }
    protected double getDirectionY() {
        return directionY;
    }

}
