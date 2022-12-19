package com.example.dogdyfella.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dogdyfella.graphics.Sprite;
import com.example.dogdyfella.graphics.SpriteSheet;

public class BrickWallTile extends Tile {
    private final Sprite sprite;

    public BrickWallTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getBrickWallSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left,mapLocationRect.top);
    }
}
