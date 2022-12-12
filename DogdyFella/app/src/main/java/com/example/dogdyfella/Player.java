package com.example.dogdyfella;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.dogdyfella.object.Circle;
import com.example.dogdyfella.object.GameObject;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;

    public Player(Context context, Joystick joystick, double posX, double posY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.player), posX, posY, radius);
        this.joystick = joystick;

    }

    @Override
    public void update() {
        //update velocity based on actuator of joystick
        velX = joystick.getActuatorX()*MAX_SPEED;
        velY = joystick.getActuatorY()*MAX_SPEED;

        posX += velX;
        posY += velY;
    }

    public void setPosition(double positionX, double positionY) {
        this.posX = positionX;
        this.posY = positionY;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}





