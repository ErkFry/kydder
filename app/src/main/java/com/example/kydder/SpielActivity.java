package com.example.kydder;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class SpielActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> prims = new ArrayList<>();
    Random rnd = new Random();

    int zahl = 1;
    int lastPrim = 2;
    TextView zahlView;
    TextView Time1;

    //für findPrim
    private int Runden;
    private int potPrim;
    private int count;
    private int rest;
    private int Runden2;

    private int timeramount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        resetPrims();

        findViewById(R.id.game_right).setOnClickListener(this);
        findViewById(R.id.game_wrong).setOnClickListener(this);
        zahlView = (TextView)findViewById(R.id.game_zahl);
        zahlView.setText(String.valueOf(zahl));
        Time1 = (TextView) findViewById(R.id.tVTime);

        //für potPrim
        potPrim = 12;

        Timer.start();
    }

    private void resetPrims(){
        zahl = 1;
        prims.clear();
        prims.add(2);
        prims.add(3);
        prims.add(5);
        prims.add(7);
        prims.add(11);
        potPrim = 12;
        lastPrim = 2;
    }



    @Override
    public void onClick(View v) {
        if ((zahl == prims.get(0) && v.getId() == R.id.game_right) || (zahl != prims.get(0) && v.getId() == R.id.game_wrong)){
            //Richtig
            if(zahl == prims.get(0)){   // Ist eine Primzahl
                lastPrim = zahl;
                prims.remove(0);
                findPrim();
                prims.add(potPrim);
            }
            zahl += rnd.nextInt(3)+1;
            if(zahl > prims.get(0)){
                prims.remove(0);
                findPrim();
                prims.add(potPrim);
            }
            zahlView.setText(String.valueOf(zahl));
        }else {
            //Falsch
            ScoreSafer.addScore(new ScoreSafer.Score("Fimms", System.currentTimeMillis(), lastPrim));
            ScoreSafer.saveScoreboard(this);
            resetPrims();
            zahlView.setText(String.valueOf(zahl));

        }
        Timer.cancel();
        Timer.start();

    }

    public void findPrim () {
        Runden = 0;
        while (Runden == 0) {
            potPrim = potPrim + 1;
            Runden2 = 0;
            for (count = 1; count <= potPrim; count += 1) {
                rest = potPrim % count;
                if (rest == 0) {
                    Runden2 = Runden2 + 1;
                }
            }
            if (Runden2 < 3) {
                Runden = 1;
            }
        }
    }

    CountDownTimer Timer = new CountDownTimer(20000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            Time1.setText(String.valueOf (millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            resetPrims();
        }
    };


}
