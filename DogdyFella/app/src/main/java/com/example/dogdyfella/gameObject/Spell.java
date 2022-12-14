package com.example.dogdyfella.gameObject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dogdyfella.GameLoop;
import com.example.dogdyfella.R;

public class Spell extends Circle{
    public static final double SPEED_PIXELS_PER_SECONDS =  800.0;
    public  static final double MAX_SPEED = SPEED_PIXELS_PER_SECONDS / GameLoop.MAX_UPS;
    public Spell(Context context, Player spellCaster) {
        super(context,
                ContextCompat.getColor(context,
                        R.color.spell),
                spellCaster.getPosX(),
                spellCaster.getPosY(),
                10);
        velX = spellCaster.getDirectionX()*MAX_SPEED;
        velY = spellCaster.getDirectionY()*MAX_SPEED;

    }


    @Override
    public void update() {
        posX += velX;
        posY += velY;
    }
}
