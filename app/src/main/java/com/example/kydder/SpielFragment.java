package com.example.kydder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.jar.Attributes;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpielFragment extends Fragment implements View.OnClickListener {


    ConstraintLayout fragment;
    private SpielFragmentListener SpielListener;

    public SpielFragment() {
        // Required empty public constructor
    }

    public interface SpielFragmentListener {
        void InputNameSent(String input);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment = (ConstraintLayout) inflater.inflate(R.layout.fragment_spiel, container, false);
        fragment.setBackgroundColor(0xffffff);
        final EditText PlayerNameEditText = (EditText) fragment.findViewById(R.id.EditText);

        Button btn_Restart = (Button) fragment.findViewById(R.id.btn_SR);
        Button btn_Homescreen = (Button) fragment.findViewById(R.id.btn_SH);
        View.OnClickListener Homescreen =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PlayerNameString = PlayerNameEditText.getText().toString();
                ((SpielActivity) getActivity()).playerName(PlayerNameString);
                ((SpielActivity) getActivity()).CloseSpielActivity();
            }
        };
        btn_Homescreen.setOnClickListener(Homescreen);
        View.OnClickListener Restart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PlayerNameString = PlayerNameEditText.getText().toString();
                //SpielListener.InputNameSent(PlayerNameString);
                ((SpielActivity) getActivity()).playerName(PlayerNameString);
                ((SpielActivity) getActivity()).StartTimer();
                ((SpielActivity) getActivity()).Einblenden();
                ((SpielActivity) getActivity()).RemoveFragment();
            }
        };
        btn_Restart.setOnClickListener(Restart);


        return fragment;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
}
