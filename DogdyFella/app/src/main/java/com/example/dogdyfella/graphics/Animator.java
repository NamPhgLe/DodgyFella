package com.example.dogdyfella.graphics;

import android.graphics.Canvas;

import com.example.dogdyfella.GameDisplay;
import com.example.dogdyfella.gameObject.Player;
import com.example.dogdyfella.gameObject.PlayerState;

public class Animator {
    private static final int MAX_UPDATE_BEFORE_NEXT_MOVE_FRAME = 5;
    private Sprite[] playerSpriteArray;
    private int idkNotMovingFrame = 0;
    private int idxMovingFrame = 1;
    private int updatesBeforeNextMoveFrame;
    public Animator(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch(player.getPlayerState().getState()){
                case NOT_MOVING:

                    drawFrame(canvas,gameDisplay, player, playerSpriteArray[idkNotMovingFrame]);
                    break;
                case STARTED_MOVING:
                    updatesBeforeNextMoveFrame = MAX_UPDATE_BEFORE_NEXT_MOVE_FRAME;
                    drawFrame(canvas,gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                    break;
                case IS_MOVING:
                    updatesBeforeNextMoveFrame--;
                    if(updatesBeforeNextMoveFrame == 0){
                        updatesBeforeNextMoveFrame = MAX_UPDATE_BEFORE_NEXT_MOVE_FRAME;
                        toggleIdxMovingFrame();
                    }
                    drawFrame(canvas,gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                    break;
                default:
                    break;
        }
    }

    private void toggleIdxMovingFrame() {
        if(idxMovingFrame <= 3){
            idxMovingFrame++;
        } else {
            idxMovingFrame = 1;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite){
        sprite.draw(canvas, (int) (gameDisplay.gameToDisplayCoordinatesX(player.getPosX()) - sprite.getWidth()/2), ((int) gameDisplay.gameToDisplayCoordinatesY(player.getPosY()) - sprite.getHeight()/2));

    }
}
