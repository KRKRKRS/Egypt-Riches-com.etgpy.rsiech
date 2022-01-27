package com.etgpy.rsiech;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp1.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView opponentImg;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        opponentImg = findViewById(R.id.opponent_img);
        resultText = findViewById(R.id.result_text);

        img1.setTag(R.mipmap.paper1);
        img2.setTag(R.mipmap.scissors1);
        img3.setTag(R.mipmap.rock1);

        Opponent opponent = new Opponent();

        img1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int opponentChoice = opponent.getRandomImageRes();
                opponentImg.setImageResource(opponentChoice);
                opponentImg.setTag(opponentChoice);

                if (opponentImg.getTag().equals(v.getTag())) {
                    resultText.setText("DRAW");
                } else if (opponentImg.getTag().equals(img3.getTag())){
                    resultText.setText("WIN");
                } else {
                    resultText.setText("LOSE");
                }
            }
        });


        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int opponentChoice = opponent.getRandomImageRes();

                opponentImg.setImageResource(opponentChoice);
                opponentImg.setTag(opponentChoice);

                if (opponentImg.getTag().equals(v.getTag())) {
                    resultText.setText("DRAW");
                } else if (opponentImg.getTag().equals(img1.getTag())){
                    resultText.setText("WIN");
                } else {
                    resultText.setText("LOSE");
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int opponentChoice = opponent.getRandomImageRes();

                opponentImg.setImageResource(opponentChoice);
                opponentImg.setTag(opponentChoice);

                if (opponentImg.getTag().equals(v.getTag())) {
                    resultText.setText("DRAW");
                } else if (opponentImg.getTag().equals(img2.getTag())){
                    resultText.setText("WIN");
                } else {
                    resultText.setText("LOSE");
                }
            }
        });

    }



    public class Opponent {

        int currentIndex = 0;
        int [] allImages = {R.mipmap.paper1, R.mipmap.rock1, R.mipmap.scissors1};


        void nextImg () {
            currentIndex++;
            if (currentIndex == allImages.length)
                currentIndex = 0;
        }

        int getRandomImageRes () {
            Random rand = new Random();
            int randFromArray = rand.nextInt(allImages.length);
            return allImages[randFromArray];

        }
    }



}