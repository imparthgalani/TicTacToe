package com.parthgalani.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayersnameActivity extends AppCompatActivity {

    private Button startGameBtn;
    private EditText player_one,player_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playersname);

        intViews();

        player_one.setSelection(player_one.getText().length());
        player_two.setSelection(player_two.getText().length());

        startGameBtn.setOnClickListener(v -> {

            String player1name = player_one.getText().toString().trim();
            String player2name = player_two.getText().toString().trim();

            if (TextUtils.isEmpty(player1name)) {
                player_one.setError("Player one name is requerd");
                return;
            }

            if (TextUtils.isEmpty(player2name)) {
                player_two.setError("Player two name is requerd");
                return;
            }

            Intent intent=new Intent(PlayersnameActivity.this, GamefriendsActivity.class);
            intent.putExtra("player1name",player_one.getText().toString());
            intent.putExtra("player2name",player_two.getText().toString());
            startActivity(intent);

        });

    }

    private void intViews() {
        startGameBtn = findViewById(R.id.btnStartgame);
        player_one   = findViewById(R.id.edtPlayer1);
        player_two   = findViewById(R.id.edtPlayer2);
    }
}

