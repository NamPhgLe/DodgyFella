package com.example.dogdyfella;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

/*
 * Health bar displays player health bar to screen
 */
 class HealthBar {
     private Player player;
     private int width, height, margin;
     private Paint borderPaint, healthPaint;

    public HealthBar(Context context, Player player){
        this.player = player;
        this.width = 100;
        this.height = 20;
        this.margin = 2;

        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
        borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.healthBarHealth);
        healthPaint.setColor(healthColor);
    }

    public void draw(Canvas canvas) {
        float x = (float) player.getPosX();
        float y = (float) player.getPosY();
        float distanceToPlayer = 40;
        float healthPointsPercentage = (float) player.getHealthPoints()/player.MAX_HEALTH_POINTS;
        
        //draw health border

        float borderLeft = x - width/2,
              borderRight = x + width/2,
              borderBottom = y - distanceToPlayer,
              borderTop = borderBottom - height;
        canvas.drawRect(borderLeft, borderTop, borderRight, borderBottom, borderPaint);
        //draw health
        float healthWidth = width - 2*margin,
                healthHeight = height - 2*margin,
                healthLeft = borderLeft + margin,
                healthRight = healthLeft + healthWidth*healthPointsPercentage,
                healthBottom = borderBottom - margin,
                healthTop = healthBottom - healthHeight;

        canvas.drawRect(healthLeft, healthTop, healthRight, healthBottom, healthPaint);
    }
}
