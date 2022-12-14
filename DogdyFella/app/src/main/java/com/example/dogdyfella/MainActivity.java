package com.example.dogdyfella;

import androidx.appcompat.app.AppCompatActivity;


import android.animation.Animator;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
/**
 * MainActivity is entry point for application
 */
public class MainActivity extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreated()");
        super.onCreate(savedInstanceState);


        //Set windows to full screen
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //Set content view to game, so object can render to screen
        game = new Game(this);
        setContentView(game);
    }
    protected void onStart() {
        Log.d("MainActivity.java", "onStart()");
        super.onStart();
    }
    protected void onResume() {
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }
    protected void onPause() {
        Log.d("MainActivity.java", "onPause()");
        game.pause();
        super.onPause();
    }
    protected void onStop() {
        Log.d("MainActivity.java", "onPause()");
        super.onStop();
    }
    protected void onDestroy() {
        Log.d("MainActivity.java", "onDestroy()");
        super.onDestroy();
    }
    public void onBackPressed() {
        Log.d("MainActivity.java", "onBackPressed()");
        //disables back press
        //super.onBackPressed();
    }

}