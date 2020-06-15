package com.example.kydder;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ScoreSafer {

    private static final String FILE = "asd.cs";

    private static final int maxSize = 25;

    protected static ArrayList<Score> scores = new ArrayList<>();

    public static boolean saveScoreboard(Context context){
        if (scores == null)
            return false;

        try {
            FileOutputStream fos = context.openFileOutput(FILE, Context.MODE_PRIVATE);
            for (Score score : scores){
                fos.write((score.toString() + "\n").getBytes());
            }
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return true;
    }

    public static boolean readScoreboard(Context context){
        // Liste zurücksetzen
        scores.clear();

        try {
            FileInputStream fis = context.openFileInput(FILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            // Solange lesen bis alle zeilen durch sind
            String line = reader.readLine();
            while (line != null) {
                // Zeilen in Liste zwischenspeichern
                scores.add(Score.fromString(line));
                // nächste Zeile lesen
                line = reader.readLine();
            }
            reader.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static int addScore(Score score){
        for (int i = 0; i < scores.size(); i++){
            if (score.score > scores.get(i).score){
                // ahhaa match
                return intern_addScore(i, score);
            }
        }
        if(scores.size() < maxSize){
            return intern_addScore(scores.size(), score);
        }
        return 0;
    }

    private static int intern_addScore(int i, Score score){
        scores.add(i, score);

        while (scores.size() > maxSize) {
            // Solange das letzte entfernen bis die size passt
            scores.remove(maxSize);
        }

        // @Erik F und Finn daran denken hier mal asynchron eine sicherheits speicherung aufrufen

        // Platzierung
        return i+1;
    }

    public static ArrayList<Score> getScoreboard(){
        return scores;
    }


    public static void removeScore(Score score){
        scores.remove(score);
    }

    public static void removeScore(int index){
        scores.remove(index);
    }



    public static class Score{


        String name;
        long date;
        int score;

        public Score(String name, long date, int score) {
            this.name = name;
            this.date = date;
            this.score = score;
        }



        @NonNull
        @Override
        public String toString() {
            // Häääh muss eigentlich in String convertiert werden leel
            return name + ";" + date + ";" + score;
        }

        public static Score fromString(String seria){
            String data[] = seria.split(";");
            return new Score(data[0], Long.valueOf(data[1]), Integer.valueOf(data[2]));
        }
    }

}
