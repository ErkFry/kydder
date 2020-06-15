package com.example.kydder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ScoreboardActivity extends AppCompatActivity {

    //Objekte
    RecyclerView score_list;
    ScoreAdapter score_adapater;
    ImageView PfeilZurück;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Entfernt die ActionBar und den TItel
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        //Getting Views
        score_list = findViewById(R.id.score_recycler);

        //Load Scoreboard
        ArrayList<ScoreSafer.Score> scores = ScoreSafer.getScoreboard();
        /*String[] names = new String[]{"Eheerrik", "Fimms", "chrisso", "Mausa"};
        String[] dates = new String[]{"ahsd", "sasd", "idk", "alistinkt"};
        String[] scores = new String[]{"2089", "41", "9", "7"};*/
        score_adapater = new ScoreAdapter(scores);

        //Setting things up
        score_adapater.notifyDataSetChanged();
        score_list.setLayoutManager(new LinearLayoutManager(this));
        score_list.setAdapter(score_adapater);

        //Pfeil als zurück möglichkeit
        PfeilZurück = findViewById(R.id.score_back);
        PfeilZurück.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
     }

}

class ScoreAdapter extends RecyclerView.Adapter<ScoreViewHolder> {


    private ArrayList<String> mNames;
    private ArrayList<String> mScores;
    private ArrayList<String> mDates;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ScoreAdapter(String[] names, String[] scores, String[] dates) {
        mNames = new ArrayList<>(Arrays.asList(names));
        mScores = new ArrayList<>(Arrays.asList(scores));
        mDates = new ArrayList<>(Arrays.asList(dates));
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ScoreAdapter(ArrayList<ScoreSafer.Score> scores) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        mNames = new ArrayList<>();
        mScores = new ArrayList<>();
        mDates = new ArrayList<>();
        for (ScoreSafer.Score score: scores) {
            mNames.add(score.name);
            mScores.add(String.valueOf(score.score));
            mDates.add(sdf.format(new Date(score.date)));
        }


    }

    // Create new views (invoked by the layout manager)
    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_scoreboard, parent, false);
        ScoreViewHolder vh = new ScoreViewHolder(layout);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.place.setText(String.valueOf(position+1));
        holder.name.setText(mNames.get(position));
        holder.date.setText(mDates.get(position));
        holder.score.setText(mScores.get(position));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mNames.size();
    }
}


// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
class ScoreViewHolder extends RecyclerView.ViewHolder {

    // each data item is just a string in this case
    public TextView place;
    public TextView score;
    public TextView date;
    public TextView name;

    public ScoreViewHolder(ConstraintLayout layout) {
        super(layout);
        place = layout.findViewById(R.id.score_place);
        score = layout.findViewById(R.id.score_score);
        date = layout.findViewById(R.id.score_date);
        name = layout.findViewById(R.id.score_name);
    }

}


