package com.example.dogdyfella.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dogdyfella.graphics.Sprite;
import com.example.dogdyfella.graphics.SpriteSheet;

public class RainOneTile extends Tile {
    private final Sprite sprite;

    public RainOneTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getRainOneSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left,mapLocationRect.top);
    }
}
