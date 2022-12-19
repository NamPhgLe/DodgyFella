package com.example.dogdyfella;

import android.graphics.Rect;

import com.example.dogdyfella.gameObject.GameObject;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final int widthPixels;
    private final int heightPixels;
    private double gameToDisplayCoordinatesOffSetX;
    private double gameToDisplayCoordinatesOffSetY;
    private double displayCenterX,displayCenterY;
    private double gameCenterX, gameCenterY;
    private GameObject centerObject;


    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject){
        this.centerObject = centerObject;
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        this.DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);
        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }
    public void update(){
        gameCenterX = centerObject.getPosX();
        gameCenterY = centerObject.getPosY();

        gameToDisplayCoordinatesOffSetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffSetY = displayCenterY - gameCenterY;
    }
    public double gameToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinatesOffSetX;
    }
    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinatesOffSetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (gameCenterX - widthPixels/2),
                (int) (gameCenterY - heightPixels/2),
                (int) (gameCenterX + widthPixels/2),
                (int) (gameCenterY + heightPixels/2)
        );
    }
}
