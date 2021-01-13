package com.parthgalani.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button btnFriend,btnAbout,btnComputer;
    ImageView imgparth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnFriend   =   findViewById(R.id.btnPlaywithfriend);
        btnComputer   =   findViewById(R.id.btnPlaywithComputer);
        btnAbout    =   findViewById(R.id.btnAbout);
        imgparth    =   findViewById(R.id.imgparth);

        btnComputer.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),PNComputerActivity.class)));

        btnFriend.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),PlayersnameActivity.class)));

        btnAbout.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), GameaboutActivity.class)));

        imgparth.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),DeveloperActivity.class)));

    }
}