package com.example.gameconnect4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0= yellow, 1= red
    private int active_player = 0;
    int[] state = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameactive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setCion(View view) {

        ImageView view1 = (ImageView) view;


        int getTag = Integer.parseInt(view1.getTag().toString());

        System.out.println(view1.getTag().toString());
        if (state[getTag] == 2&& gameactive) {

            view1.setTranslationY(-1000f);
            state[getTag] = active_player;


            if (active_player == 0) {
                view1.setImageResource(R.drawable.yellow);
                active_player = 1;
            } else {
                view1.setImageResource(R.drawable.red);
                active_player = 0;

            }
            view1.animate().translationYBy(1000f).rotation(360).setDuration(300);
            final LinearLayout layout = (LinearLayout) findViewById(R.id.tryAgainLayout);
            for (int[] winnimgpostion : winningPositions) {
                if (state[winnimgpostion[0]] == state[winnimgpostion[1]]
                        && state[winnimgpostion[1]] == state[winnimgpostion[2]]
                        && state[winnimgpostion[0]] != 2) {

                    gameactive =false;
                    String winner = "Red";
                    if (state[winnimgpostion[0]] == 0) {
                        winner = "Yellow";

                    }
                    TextView txt_win = findViewById(R.id.WinnerMassage);

                    txt_win.setText(winner + " has win!");


                    layout.setVisibility(View.VISIBLE);
                }
                else {

                    boolean gameOver = true;
                    for (int i : state){
                        if (i==2) gameOver= false;
                    }

                    if (gameOver){
                        TextView txt_win = findViewById(R.id.WinnerMassage);

                        txt_win.setText( " It's A Draw");

                       // final LinearLayout layout = (LinearLayout) findViewById(R.id.tryAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }


    }

    public void TryAgain(View view) {

        gameactive = true;
        final LinearLayout layout = (LinearLayout) findViewById(R.id.tryAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        active_player = 0;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.GL);
          gridLayout = findViewById(R.id.GL);

        for (int i = 0; i < state.length; i++) {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
            state[i]=2;
        }
    }
}