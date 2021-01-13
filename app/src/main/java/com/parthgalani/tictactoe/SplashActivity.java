package com.parthgalani.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Splash  = new Intent(com.parthgalani.tictactoe.SplashActivity.this, com.parthgalani.tictactoe.HomeActivity.class);
                startActivity(Splash);
                finish();
            }
        },SPLASH_SCREEN);
    }
}