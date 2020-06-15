package com.example.kydder;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Random;
import java.util.jar.Attributes;

public class SpielActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Integer> prims = new ArrayList<>();
    Random rnd = new Random();

    int zahl = 1;
    int lastPrim = 2;
    TextView zahlView;
    TextView Time1;
    ImageView logo;
    //für findPrim
    private int Runden;
    private int potPrim;
    private int count;
    private int rest;
    private int Runden2;
    String Name;
    private int timeramount;
    Button Primzahl;
    Button Falsch;

    Fragment SpielFragment = new SpielFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Entfernt die ActionBar und den TItel
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        getSupportActionBar().hide();


        resetPrims();

        logo = findViewById(R.id.game_logo);
        findViewById(R.id.game_right).setOnClickListener(this);
        findViewById(R.id.game_wrong).setOnClickListener(this);
        zahlView = (TextView)findViewById(R.id.game_zahl);
        zahlView.setText(String.valueOf(zahl));
        Time1 = (TextView) findViewById(R.id.tVTime);
        Primzahl = findViewById(R.id.game_right);
        Falsch = findViewById(R.id.game_wrong);

        //für potPrim
        potPrim = 12;

        //Iwie anders noch
        Timer.start();
    }

    public void resetPrims(){
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
        Timer.cancel();
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
            Timer.start();
        }else {
            //Falsch
            Ausblenden();
            FragmentTransaction transactionSpielFragment = getSupportFragmentManager().beginTransaction();
            transactionSpielFragment.add(R.id.frag_container, SpielFragment);
            transactionSpielFragment.commit();


        }
    }

    public void findPrim () {
        Runden = 0;
        while (Runden == 0) {
            potPrim = potPrim + 1;
            Runden2 = 0;
            for (count = 1; count <= potPrim / 2 + 1; count += 1) {
                rest = potPrim % count;
                if (rest == 0) {
                    Runden2 = Runden2 + 1;
                }
            }
            if (Runden2 < 2) {
                Runden = 1;
            }
        }
    }

    CountDownTimer Timer = new CountDownTimer(20000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            Time1.setText(String.valueOf (millisUntilFinished / 1000) + "s");
        }

        @Override
        public void onFinish() {
           //Zeit abgelaufen alles auf Anfang
            /*ScoreSafer.addScore(new ScoreSafer.Score("Fimms", System.currentTimeMillis(), lastPrim));
            ScoreSafer.saveScoreboard(getBaseContext());*/
            Ausblenden();
            FragmentTransaction transactionSpielFragment = getSupportFragmentManager().beginTransaction();
            transactionSpielFragment.add(R.id.frag_container, SpielFragment);
            transactionSpielFragment.commit();
            Timer.cancel();
            zahlView.setText(String.valueOf(zahl));
        }
    };

    public void playerName(String playername){
        ScoreSafer.addScore(new ScoreSafer.Score(playername, System.currentTimeMillis(), zahl));
        ScoreSafer.saveScoreboard(this);
        resetPrims();
        zahlView.setText(String.valueOf(zahl));
    }

    public void CloseSpielActivity () {
        finish();
    }
    public void StartTimer() {
        Timer.start();
    }

    public void RemoveFragment() {
        FragmentTransaction transactionSpielFragment = getSupportFragmentManager().beginTransaction();
        transactionSpielFragment.remove(SpielFragment);
        transactionSpielFragment.commit();
    }

    public void Ausblenden(){
        logo.setVisibility(View.INVISIBLE);
        Falsch.setVisibility(View.INVISIBLE);
        Primzahl.setVisibility(View.INVISIBLE);
        zahlView.setVisibility(View.INVISIBLE);
        Time1.setVisibility(View.INVISIBLE);
    }

    public void Einblenden(){
        logo.setVisibility(View.VISIBLE);
        Falsch.setVisibility(View.VISIBLE);
        Primzahl.setVisibility(View.VISIBLE);
        zahlView.setVisibility(View.VISIBLE);
        Time1.setVisibility(View.VISIBLE);
    }


}
