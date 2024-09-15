package com.example.tictactoe_game;

import android.os.Bundle;
import android.view.View;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    boolean isToast = false;

    // Player representation
        // 0 - X    &    1 - O
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State of 0 & 1 & 2
    // 0 -> X
    // 1 -> O
    // 2 -> null
    int [][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, // rows
                             {0,3,6}, {1,4,7}, {2,5,8}, // columns
                             {0,4,8}, {2,4,6}   // diagonals
    };

    public void playerTap (View view) {




        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive) {
            gameReset(view);
        }
        if(gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // Here, check the condition that who has won the game?
        for(int[] winningPositions : winPositions) {
            if(gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                    gameState[winningPositions[0]]  != 2 ) {
                    String winnerStr;
                    if(gameState[winningPositions[0]] == 0) {
                        winnerStr = "X has Won🏆";
                        gameActive = false;
                    }
                    else {
                        winnerStr = "O has Won🏆";
                        gameActive = false;
                    }

                //  Update the status bar for winner announcement
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
            }
        }

        if(gameActive == true) {
            int checkFillAllBlocks = 0;
            for(int i=0; i<gameState.length; i++) {
//                      if (gameState[i] == 0 || gameState[i] == 1) {
                if(gameState[i] != 2) {
                    checkFillAllBlocks++;
                }
            }
            if(checkFillAllBlocks == 9) {
                TextView status = findViewById(R.id.status);
                status.setText("Match Tie 👊");
                gameActive = false;
//                    Toast.makeText(this, "Game Start when Tap on any " +
//                                    "Box and X's Turn",
//                            Toast.LENGTH_SHORT).show();


//                    Toast toast = Toast.makeText(this, "Game Start when Tap" +
//                                    " on any " +
//                                    "Box and X's Turn",
//                            Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 100, 400);
//                    toast.show();
            }
        }

    }

    private void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;

        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.img1)).setImageResource(0);
        ((ImageView) findViewById(R.id.img2)).setImageResource(0);
        ((ImageView) findViewById(R.id.img3)).setImageResource(0);
        ((ImageView) findViewById(R.id.img4)).setImageResource(0);
        ((ImageView) findViewById(R.id.img5)).setImageResource(0);
        ((ImageView) findViewById(R.id.img6)).setImageResource(0);
        ((ImageView) findViewById(R.id.img7)).setImageResource(0);
        ((ImageView) findViewById(R.id.img8)).setImageResource(0);
        ((ImageView) findViewById(R.id.img9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X\'s Turn");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}