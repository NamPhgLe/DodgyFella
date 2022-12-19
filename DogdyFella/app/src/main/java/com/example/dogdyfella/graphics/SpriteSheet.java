package com.example.dogdyfella.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.dogdyfella.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 32;
    private static final int SPRITE_HEIGHT_PIXELS = 32;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }
    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[8];
        for(int i =0, j =1; i < spriteArray.length; i++, j++){
            spriteArray[i] = new Sprite(this, new Rect(i*64, 0, j*64, 64));
        }

        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getCobbleSprite() {
        return getSpriteByIndex(2, 0);
    }
    public Sprite getGrassSprite() {
        return getSpriteByIndex(2, 1);
    }
    public Sprite getShortGrassSprite() {
        return getSpriteByIndex(2, 2);
    }
    public Sprite getBrickWallSprite() {
        return getSpriteByIndex(2, 3);
    }
    public Sprite getOceanSprite() {
        return getSpriteByIndex(2, 4);
    }
    public Sprite getBeachTideSprite() {
        return getSpriteByIndex(2, 5);
    }
    public Sprite getRainOneSprite() {
        return getSpriteByIndex(2, 6);
    }
    public Sprite getRainTwoSprite() {
        return getSpriteByIndex(3, 0);
    }



    private Sprite getSpriteByIndex(int idxRow, int idxCol){
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
        ));
    }


}
