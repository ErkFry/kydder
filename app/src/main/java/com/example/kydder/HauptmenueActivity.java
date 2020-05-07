package com.example.kydder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class HauptmenueActivity extends AppCompatActivity {

    private Button buttonSpiel;
    private Button buttonScoreboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Entfernt die ActionBar und den TItel
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //Scoreboard initialisierung
        ScoreSafer.readScoreboard(this);

        buttonSpiel = (Button) findViewById(R.id.bSpiel);
        buttonSpiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpiel();
            }
        });

        buttonScoreboard = (Button) findViewById(R.id.bScoreboard);
        buttonScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScoreboard();
            }
        });



    }


    public void openSpiel(){
        Intent intentSpiel = new Intent(this, SpielActivity.class);
        startActivity(intentSpiel);
    }


    public void openScoreboard(){
        Intent intentScoraboard = new Intent(this, ScoreboardActivity.class);
        startActivity(intentScoraboard);
    }

}
