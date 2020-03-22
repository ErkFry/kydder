package com.example.kydder;

import android.os.Bundle;
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
    TextView zahlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        resetPrims();

        findViewById(R.id.game_right).setOnClickListener(this);
        findViewById(R.id.game_wrong).setOnClickListener(this);
        zahlView = (TextView)findViewById(R.id.game_zahl);
        zahlView.setText(String.valueOf(zahl));

    }

    private void resetPrims(){
        zahl = 1;
        prims.clear();
        prims.add(1);
        prims.add(3);
        prims.add(5);
        prims.add(7);
        prims.add(11);
    }


    @Override
    public void onClick(View v) {
        if ((zahl == prims.get(0) && v.getId() == R.id.game_right) || (zahl != prims.get(0) && v.getId() == R.id.game_wrong)){
            //Richtig
            if(zahl == prims.get(0)){
                prims.remove(0);
                //prims.add(getPrim());
            }
            zahl += rnd.nextInt(3);
            if(zahl >= prims.get(0)){
                prims.remove(0);
                //prims.add(getPrim());
            }
            zahlView.setText(String.valueOf(zahl));
        }else {
            //Falsch
            resetPrims();
            zahlView.setText(String.valueOf(zahl));
        }
    }
}
