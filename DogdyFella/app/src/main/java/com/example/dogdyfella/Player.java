package com.example.dogdyfella;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

 public class Player {
     private static final double SPEED_PIXELS_PER_SECONDS = 400.0;
     private static final double MAX_SPEED = SPEED_PIXELS_PER_SECONDS / GameLoop.MAX_UPS;
     private double posX, posY;

    private Paint paint;
    private double radius, velX, velY;

     public Player(Context context, double positionX, double positionY, double radius) {
         this.posX = positionX;
         this.posY = positionY;
         this.radius = radius;

         paint = new Paint();
         int color = ContextCompat.getColor(context, R.color.player);
         paint.setColor(color);
     }

     public void draw(Canvas canvas) {
        canvas.drawCircle((float) posX,(float) posY, (float) radius, paint);
    }

    public void update(Joystick joystick) {
         velX = joystick.getActuatorX()*MAX_SPEED;
         velY = joystick.getActuatorY()*MAX_SPEED;
         posX += velX;
         posY += velY;
    }

     public void setPosition(double positionX, double positionY) {
         this.posX = positionX;
         this.posY = positionY;
     }
 }
