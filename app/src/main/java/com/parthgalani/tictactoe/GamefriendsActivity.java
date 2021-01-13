package com.parthgalani.tictactoe;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GamefriendsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgHome, imgScoreboard, imgSingle;
    Button btnResetscore;

    private TextView player1ScoreTV, player2ScoreTV, matchDrawScoreTv;
    private Button resetBtn;
    private Button[][] btns = new Button[3][3];

    private int player1Points = 0, player2Points = 0, matchDrawPoints = 0;
    private int roundCount = 0;
    private Boolean player1Tern = true;
    private String Player1Name, Player2Name;
    public String my;

    private SoundPool soundPool;
    private int knife, funny, win;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamefriends);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }
        knife = soundPool.load(this, R.raw.knife, 1);
        funny = soundPool.load(this, R.raw.funny, 1);
        win = soundPool.load(this, R.raw.cartoon, 1);

        imgHome = findViewById(R.id.imgHome);
        imgScoreboard = findViewById(R.id.imgScorebord);
        imgSingle = findViewById(R.id.imgSingle);

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

        imgScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DeveloperActivity.class));
                finish();
            }
        });

        imgSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PNComputerActivity.class));
                finish();
            }
        });

        initViews();

        Player1Name = getIntent().getStringExtra("player1name");
        Player2Name = getIntent().getStringExtra("player2name");
        player1ScoreTV.setText(Player1Name + " (X) : 0");
        player2ScoreTV.setText(Player2Name + " (O) : 0");

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });

        btnResetscore = findViewById(R.id.btnResetscore);
        btnResetscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    private void initViews() {

        player1ScoreTV = findViewById(R.id.p1TV);
        player2ScoreTV = findViewById(R.id.p2TV);
        matchDrawScoreTv = findViewById(R.id.dTv);
        resetBtn = findViewById(R.id.resetBtn);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String id_name = "btn_" + i + j; //btn_00 ,btn_01,btn_02
                int btnId = this.getResources().getIdentifier(id_name, "id", getPackageName());
                btns[i][j] = findViewById(btnId); //findViewById(R.id.btn_00);
                btns[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (player1Tern) {
            ((Button) v).setText("x");
            soundPool.play(knife, 1, 1, 0, 0, 1);
            ((Button) v).setTextColor(this.getResources().getColor(R.color.colorBackground));
            ((Button) v).setEnabled(false);
        } else {
            ((Button) v).setText("O");
            soundPool.play(funny, 1, 1, 0, 0, 1);
            ((Button) v).setTextColor(this.getResources().getColor(R.color.colorSymbol0));
            v.setEnabled(false);

        }
        roundCount++;

        if (checkForWin()) {
            if (player1Tern) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Tern = !player1Tern;
        }

    }

    private void draw() {
        matchDrawPoints++;
        updatePointText();
        //Toast.makeText(this,"Match draw", Toast.LENGTH_SHORT).show();
        askForAnotherGame("Match Draw!");
        my = "Match Draw";
    }

    private void player2Wins() {
        player2Points++;
        //  Toast.makeText(this, player2Name+" wins", Toast.LENGTH_SHORT).show();
        updatePointText();
        my = Player2Name;
        askForAnotherGame(my + " Wins");
    }

    private void player1Wins() {
        player1Points++;
        //  Toast.makeText(this, player2Name+" wins", Toast.LENGTH_SHORT).show();
        updatePointText();
        //askForAnotherGame(Player1Name+" Wins");
        my = Player1Name;
        askForAnotherGame(my + " Wins");
    }

    private void resetBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btns[i][j].setText("");
                btns[i][j].setEnabled(true);
            }
        }
        roundCount = 0;
        player1Tern = true;
    }


    private void updatePointText() {
        player1ScoreTV.setText(Player1Name + " (X) : " + player1Points);
        player2ScoreTV.setText(Player2Name + " (O) : " + player2Points);
        matchDrawScoreTv.setText("Match Draw : " + matchDrawPoints);

    }

    private boolean checkForWin() {
        String field[][] = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = btns[i][j].getText().toString();
            }
        }
        //comparing columns while changing rows
        for (int i = 0; i < 3; i++) {
            //        00       =         01    &&      00      =           02  row1
            //        10       =         11    &&      10      =           12  row2
            //        00       =         01    &&      00      =           02  row3
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !(field[i][0].equals(""))) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) //comparing rows while changing columns
        {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !(field[0][i].equals(""))) {
                return true;
            }
        }
        //comparing \ diagonal
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && (!field[0][0].equals(""))) {
            return true;
        }

        //comparing reverse diagonal
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && (!field[0][2].equals(""))) {
            return true;
        }
        return false;
    }

    public void resetGame() {
        player1Points = 0;
        player2Points = 0;
        matchDrawPoints = 0;
        resetBoard();
        updatePointText();
    }

    private void askForAnotherGame(String msg) {

        soundPool.play(win, 1, 1, 0, 0, 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(com.parthgalani.tictactoe.GamefriendsActivity.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(com.parthgalani.tictactoe.GamefriendsActivity.this).inflate(
                R.layout.message_dialog,
                (ConstraintLayout) findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.textTitle)).setText(msg);
        ((TextView) view.findViewById(R.id.textMessage)).setText(getResources().getString(R.string.message_Play));
        ((TextView) view.findViewById(R.id.buttonNo)).setText(getResources().getString(R.string.no));
        ((TextView) view.findViewById(R.id.buttonYes)).setText(getResources().getString(R.string.yes));
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //  Toast.makeText(GamefriendsActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                resetBoard();
            }
        });
        view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //  Toast.makeText(GamefriendsActivity.this, "No", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(com.parthgalani.tictactoe.GamefriendsActivity.this, PlayersnameActivity.class));
                finish();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}