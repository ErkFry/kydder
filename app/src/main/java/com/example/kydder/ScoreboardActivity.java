package com.example.kydder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class ScoreboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
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

    // Create new views (invoked by the layout manager)
    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_scoreboard, parent, false);
        ScoreViewHolder vh = new ScoreViewHolder(layout);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mNames.get(position));


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
    public TextView textView;
    public ScoreViewHolder(ConstraintLayout layout) {
        super(layout);
    }

}


