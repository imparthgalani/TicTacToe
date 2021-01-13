package com.parthgalani.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GameaboutActivity extends AppCompatActivity {

    ImageView imgLogo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameabout);


        imgLogo1    =   findViewById(R.id.imgLogo1);

        imgLogo1.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),HomeActivity.class)));
    }
}