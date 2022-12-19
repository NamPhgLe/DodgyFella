package com.example.dogdyfella.Map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dogdyfella.graphics.SpriteSheet;

abstract class Tile {
    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        COBBLE_TILE,
        GRASS_TILE,
        SHORT_GRASS_TILE,
        BRICK_WALL_TILE,
        OCEAN_TILE,
        BEACH_TIDE_TILE,
        RAIN1_TILE,
        RAIN2_TILE,
    }
    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch(TileType.values()[idxTileType]){
            case COBBLE_TILE:
                return new CobbleTile(spriteSheet, mapLocationRect);
            case GRASS_TILE:
                return new GrassTile(spriteSheet, mapLocationRect);
            case SHORT_GRASS_TILE:
                return new ShortGrassTile(spriteSheet, mapLocationRect);
            case BRICK_WALL_TILE:
                return new BrickWallTile(spriteSheet, mapLocationRect);
            case OCEAN_TILE:
                return new OceanTile(spriteSheet, mapLocationRect);
            case BEACH_TIDE_TILE:
                return new BeachTideTile(spriteSheet, mapLocationRect);
            case RAIN1_TILE:
                return new RainOneTile(spriteSheet, mapLocationRect);
            case RAIN2_TILE:
                return new RainTwoTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
