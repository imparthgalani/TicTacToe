package com.parthgalani.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DeveloperActivity extends AppCompatActivity {
    ImageView imgLogo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        imgLogo1 = findViewById(R.id.imgLogo1);

        imgLogo1.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),HomeActivity.class)));


    }

}