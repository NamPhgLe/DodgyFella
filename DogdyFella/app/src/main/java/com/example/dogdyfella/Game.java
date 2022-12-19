package com.example.dogdyfella;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.dogdyfella.Map.Tilemap;
import com.example.dogdyfella.gameObject.Circle;
import com.example.dogdyfella.gameObject.Enemy;
import com.example.dogdyfella.gameObject.Player;
import com.example.dogdyfella.gameObject.Spell;
import com.example.dogdyfella.gamepanel.GameOver;
import com.example.dogdyfella.gamepanel.Joystick;
import com.example.dogdyfella.gamepanel.Performance;
import com.example.dogdyfella.graphics.Animator;
import com.example.dogdyfella.graphics.SpriteSheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Game manages all objects in the game and is responsible for running all states and rendering of game
 */
class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Tilemap tilemap;
    private Joystick joystick;
    private Player player;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private int joystickPointerId = 0;
    private int numberOfSpellsToCast =0;
    private GameOver gameOver;
    private Performance performance;
    private GameDisplay gameDisplay;

    public Game(Context context) {
        super(context);

        //get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);


        //Initialize game panels
        performance = new Performance(context, gameLoop);
        gameOver = new GameOver(context);
        joystick = new Joystick(context,100,500, 60, 30);

        //Initialize game objects
        SpriteSheet spriteSheet = new SpriteSheet(context);
        Animator animator = new Animator(spriteSheet.getPlayerSpriteArray());
        player = new Player(context, joystick, 500, 500, 15, animator);

       //Initialize display and center it around player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        setFocusable(true);

        //Initialize Tilemap
        tilemap = new Tilemap(spriteSheet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //Handel touch event actions
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(joystick.getIsPressed()) {
                    numberOfSpellsToCast++;
                } else if(joystick.isPressed((double) event.getX(), (double) event.getY())){
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    numberOfSpellsToCast++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed() == true) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerId == event.getPointerId(event.getActionIndex())){
                    joystick.setIsPressed(false);
                    joystick.restActuator();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){
            gameLoop = new GameLoop(this,holder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("Game.java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tilemap.draw(canvas, gameDisplay);

        performance.draw(canvas);
        joystick.draw(canvas);
        player.draw(canvas, gameDisplay);

        for(Enemy enemy : enemyList){
            enemy.draw(canvas, gameDisplay);
        }
        for(Spell spell : spellList){
            spell.draw(canvas, gameDisplay);
        }

        //End Screen if player is dead
        if(player.getHealthPoints() <= 0) {
            gameOver.draw(canvas);
        }
    }


    public void update(){

        if(player.getHealthPoints() <= 0){
            return;
        }
        //update game state
        joystick.update();
        player.update();

        //spawn enemy
        if(Enemy.readyToSpawn()){
            enemyList.add(new Enemy(getContext(), player));
        }
        while(numberOfSpellsToCast > 0) {
            spellList.add(new Spell(getContext(), player));
            numberOfSpellsToCast--;
        }

        for(Enemy enemy : enemyList) {
            enemy.update();
        }

        for(Spell spell : spellList){
            spell.update();
        }

        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while(iteratorEnemy.hasNext()){
            Circle enemy = iteratorEnemy.next();
            if(Circle.isColliding(enemy, player)){
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints()- 1);
                continue;
            }
            Iterator<Spell> iteratorSpell = spellList.iterator();
            while(iteratorSpell.hasNext()){
                Circle spell = iteratorSpell.next();
                if(Circle.isColliding(spell, enemy)){
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }
        }
        gameDisplay.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
