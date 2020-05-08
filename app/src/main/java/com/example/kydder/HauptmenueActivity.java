package com.example.kydder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class HauptmenueActivity extends AppCompatActivity {

    private Button buttonSpiel;
    private Button buttonScoreboard;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Entfernt die ActionBar und den TItel
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Init krams
        getSupportActionBar().hide();


        //Scoreboard initialisierung
        ScoreSafer.readScoreboard(this);

        //Views
        logo = findViewById(R.id.main_logo);
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


        //Transition
        getWindow().setExitTransition(new Explode());

    }


    public void openSpiel(){
        Intent intentSpiel = new Intent(this, SpielActivity.class);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, logo, "logo");


        startActivity(intentSpiel, options.toBundle());
    }


    public void openScoreboard(){
        Intent intentScoraboard = new Intent(this, ScoreboardActivity.class);
        startActivity(intentScoraboard);
    }

}
