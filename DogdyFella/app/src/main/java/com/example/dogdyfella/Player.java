package com.example.dogdyfella;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

 public class Player {
    private double positionX;
    private double positionY;
    private double raduis;
    private Paint paint;

     public Player(Context context, double positionX, double positionY, double raduis) {
         this.positionX = positionX;
         this.positionY = positionY;
         this.raduis = raduis;

         paint = new Paint();
         int color = ContextCompat.getColor(context, R.color.player);
         paint.setColor(color);
     }

     public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX,(float) positionY, (float) raduis, paint);
    }

    public void update() {
    }

     public void setPosition(double positionX, double positionY) {
         this.positionX = positionX;
         this.positionY = positionY;
     }
 }
