package com.example.dogdyfella;

import com.example.dogdyfella.gameObject.GameObject;

public class GameDisplay {
    private double gameToDisplayCoordinatesOffSetX;
    private double gameToDisplayCoordinatesOffSetY;
    private double displayCenterX,displayCenterY;
    private double gameCenterX, gameCenterY;
    private GameObject centerObject;


    public GameDisplay(int widthPixels,int heightPixels, GameObject centerObject){
        this.centerObject = centerObject;

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
}
