
package com.example.dogdyfella.gameObject;


import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dogdyfella.GameLoop;
import com.example.dogdyfella.R;

public class Enemy extends Circle {
    private static final double SPEED_PIXELS_PER_SECONDS = Player.SPEED_PIXELS_PER_SECOND * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECONDS / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;

    public Enemy(Context context, Player player, double posX, double posY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), posX, posY, radius);
        this.player = player;
    }
    public Enemy(Context context, Player player) {
        super(context,
                ContextCompat.getColor(context,
                R.color.enemy),
                Math.random()*1000,
                Math.random()*1000,
                15);
        this.player = player;
    }
    //checks if enemy should spawn (for number of spawns per min)
    public static boolean readyToSpawn() {
        if(updatesUntilNextSpawn <= 0){
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn--;
            return false;
        }
    }

    @Override
    public void update() {
        double distanceToPlayerX = player.getPosX() - posX;
        double distanceToPlayerY = player.getPosY() - posY;

        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        if(distanceToPlayer > 0){
            velX = directionX*MAX_SPEED;
            velY = directionY*MAX_SPEED;
        } else {
            velX = 0;
            velY = 0;
        }

        posX += velX;
        posY += velY;
    }
}

