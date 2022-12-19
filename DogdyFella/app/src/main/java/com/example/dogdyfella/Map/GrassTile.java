package com.example.dogdyfella.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dogdyfella.graphics.Sprite;
import com.example.dogdyfella.graphics.SpriteSheet;

public class GrassTile extends Tile {
    private final Sprite grassSprite;
    private final Sprite cobbleSprite;

    public GrassTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        cobbleSprite = spriteSheet.getCobbleSprite();
        grassSprite = spriteSheet.getGrassSprite();

    }

    @Override
    public void draw(Canvas canvas) {
        cobbleSprite.draw(canvas, mapLocationRect.left,mapLocationRect.top);
        grassSprite.draw(canvas, mapLocationRect.left,mapLocationRect.top);

    }
}
