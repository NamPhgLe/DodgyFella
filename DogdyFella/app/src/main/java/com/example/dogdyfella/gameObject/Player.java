package com.example.dogdyfella.gameObject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.dogdyfella.GameDisplay;
import com.example.dogdyfella.GameLoop;
import com.example.dogdyfella.R;
import com.example.dogdyfella.Utils;
import com.example.dogdyfella.gameObject.Circle;
import com.example.dogdyfella.gamepanel.HealthBar;
import com.example.dogdyfella.gamepanel.Joystick;
import com.example.dogdyfella.graphics.Sprite;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 10;
    private Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints;
    private Sprite sprite;

    public Player(Context context, Joystick joystick, double posX, double posY, double radius, Sprite sprite) {
        super(context, ContextCompat.getColor(context, R.color.player), posX, posY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.healthPoints = MAX_HEALTH_POINTS;
        this.sprite = sprite;
    }

    @Override
    public void update() {
        //update velocity based on actuator of joystick
        velX = joystick.getActuatorX()*MAX_SPEED;
        velY = joystick.getActuatorY()*MAX_SPEED;

        posX += velX;
        posY += velY;

        if(velX != 0 || velY != 0){
            double distance = Utils.getDistanceBetweenPoints(0, 0, velX,velY);
            directionX = velX/distance;
            directionY = velY/distance;
        }
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

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        //super.draw(canvas, gameDisplay);

        sprite.draw(canvas, (int) (gameDisplay.gameToDisplayCoordinatesX(getPosX()) - sprite.getWidth()/2), ((int) gameDisplay.gameToDisplayCoordinatesY(getPosY()) - sprite.getHeight()/2));
        healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints >= 0) {
            this.healthPoints = healthPoints;
        }
    }
}





