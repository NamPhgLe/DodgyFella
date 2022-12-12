
package com.example.dogdyfella.object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Circle extends GameObject {

    protected Paint paint;
    protected double radius;

    public Circle(Context context, int color, double posX, double posY, double radius){
        super(posX, posY);
        this.radius = radius;
        //sets color of circle
        paint = new Paint();
        paint.setColor(color);
    }

    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObjects(obj1, obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        if(distance < distanceToCollision){
            return true;
        } else {
            return false;
        }
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) posX,(float) posY, (float) radius, paint);
    }

}
