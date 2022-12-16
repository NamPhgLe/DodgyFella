package com.example.dogdyfella.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.dogdyfella.GameLoop;
import com.example.dogdyfella.R;

public class Performance {
    private GameLoop gameLoop;
    private Context context;

    public Performance(Context context, GameLoop gameloop){
        this.context = context;
        this.gameLoop = gameloop;
    }

    public void draw(Canvas canvas) {
        drawUPS(canvas);
        drawFPS(canvas);
    }
    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(10);
        canvas.drawText("UPS" + averageUPS, 20, 10, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(10);
        canvas.drawText("FPS" + averageFPS, 20, 20, paint);
    }
}
