package com.iamspd.connect3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // variables
    private int activePlayer;
    private int[] gameState;
    private int[][] winningPositions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {

        // 0 = yellow, 1 = red
        activePlayer = 0;

        // 2 means not yet played
        gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};

        // winning positions
        winningPositions = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};

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

            for (int[] winnigPosition : winningPositions){
                System.out.println(gameState[winnigPosition[0]]);
            }
        }
    }
}