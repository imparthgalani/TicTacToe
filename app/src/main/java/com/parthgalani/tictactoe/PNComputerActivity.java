package com.parthgalani.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PNComputerActivity extends AppCompatActivity {
    private Button startGameBtn;
    private EditText player_one;
    private TextView player_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_n_computer);

        intViews();

        player_one.setSelection(player_one.getText().length());

        startGameBtn.setOnClickListener(v -> {

            String player1name = player_one.getText().toString().trim();
            String player2name = player_two.getText().toString().trim();

            if (TextUtils.isEmpty(player1name)) {
                player_one.setError("Player one name is requerd");
                return;
            }

            Intent intent=new Intent(PNComputerActivity.this,GameComputerActivity.class);
            intent.putExtra("player1name",player_one.getText().toString());
            intent.putExtra("player2name",player_two.getText().toString());
            startActivity(intent);
            finish();

        });

    }

    private void intViews() {
        startGameBtn = findViewById(R.id.btnStartgame);
        player_one   = findViewById(R.id.edtPlayer1);
        player_two   = findViewById(R.id.txtComputer);
    }
}

