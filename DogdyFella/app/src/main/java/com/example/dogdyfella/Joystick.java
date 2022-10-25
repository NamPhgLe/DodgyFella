package com.example.dogdyfella;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.text.method.Touch;

import androidx.core.content.ContextCompat;

public class Joystick {

    private Paint outCirclePaint;
    private Paint innerCirclePaint;

    private final int outCircleRadius;
    private final int innerCircleRadius;

    private  int outCircleCenterPosX;
    private  int outCircleCenterPosY;
    private  int innerCircleCenterPosX;
    private  int innerCircleCenterPosY;

    private double joystickCneterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    public Joystick(Context context, int centerPosX, int centerPosY, int outCircleRaduis, int innerCircleRaduis) {
        //joyStick position
        outCircleCenterPosX = centerPosX;
        outCircleCenterPosY = centerPosY;
        innerCircleCenterPosX = centerPosX;
        innerCircleCenterPosY = centerPosY;

        //radi of circle
        this.outCircleRadius = outCircleRaduis;
        this.innerCircleRadius = innerCircleRaduis;

        //paint circles
        outCirclePaint = new Paint();
        int outCicleColor = ContextCompat.getColor(context, R.color.outCircle);
        outCirclePaint.setColor(outCicleColor);

        innerCirclePaint = new Paint();
        int inCircleColor = ContextCompat.getColor(context, R.color.inCircle);
        innerCirclePaint.setColor(inCircleColor);

    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(outCircleCenterPosX, outCircleCenterPosY, outCircleRadius, outCirclePaint);
        canvas.drawCircle(innerCircleCenterPosX,innerCircleCenterPosY,innerCircleRadius,innerCirclePaint);
    }

    public void update() {
        updateInnerCirclePos();
    }

    private void updateInnerCirclePos(){
        innerCircleCenterPosX = (int) (outCircleCenterPosX + actuatorX*outCircleRadius);
        innerCircleCenterPosY = (int) (outCircleCenterPosY + actuatorY*outCircleRadius);

    }
    public boolean isPressed(double touchPosX, double touchPosY) {
        joystickCneterToTouchDistance = Math.sqrt(Math.pow(outCircleCenterPosX - touchPosX, 2) +
                                        Math.pow(outCircleCenterPosY - touchPosY, 2));
        return joystickCneterToTouchDistance < outCircleRadius;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setActuator(double touchPosX, double touchPosY){
        double deltaX = touchPosX - outCircleCenterPosX;
        double deltaY = touchPosY - outCircleCenterPosY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        if(deltaDistance < outCircleRadius){
             actuatorX = deltaX/outCircleRadius;
             actuatorY = deltaY/outCircleRadius;
        } else {
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }
    }

    public void restActuator() {
        actuatorX =0;
        actuatorY =0;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}