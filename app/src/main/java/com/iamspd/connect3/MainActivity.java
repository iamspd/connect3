package com.iamspd.connect3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // widgets
    private LinearLayout winnerLayout;
    private TextView winnerText;
    private GridLayout gameBoard;

    // constants
    private static final String TAG = "MainActivity";

    // variables

    // 0 = yellow, 1 = red
    int activePlayer = 0;

    // 2 means not yet played
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // winning positions
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // someone has won!
                    String winner = "Yellow";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Red";
                    }

                    winnerLayout = findViewById(R.id.winnerLayout);
                    winnerLayout.setVisibility(View.VISIBLE);

                    winnerText = findViewById(R.id.tvWinner);
                    winnerText.setText("Player " + winner + " has won!");

                }
            }
        }
    }

    public void startOver(View view) {

        // making the LinearLayout invisible
        winnerLayout = findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);

        // resetting the variables to the initial stage
        activePlayer = 0;
        Arrays.fill(gameState, 2);

        // resetting the columns of the gameBoard to blank
        gameBoard = findViewById(R.id.gameBoard);
        for (int i = 0; i < gameBoard.getChildCount(); i++) {
            ((ImageView) gameBoard.getChildAt(i)).setImageResource(0);
        }


    }
}

