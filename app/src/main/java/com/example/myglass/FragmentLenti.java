package com.example.myglass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentLenti extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lenti, container, false);

        Button buttonLentiSole = (Button)view.findViewById(R.id.btn_lentiSole);
        buttonLentiSole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations( R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new FragmentLentiSole()).commit();
            }
        });

        Button buttonLentiVista = (Button)view.findViewById(R.id.btn_lentiVista);
        buttonLentiVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new FragmentLentiVista()).commit();
            }
        });

        //fragment bottone occhiali
        Button btnIndietro1 = (Button)view.findViewById(R.id.btn_indietro1);
        btnIndietro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(   R.anim.enter_left_to_right,R.anim.exit_left_to_right,
                                R.anim.enter_right_to_left,R.anim.exit_right_to_left)
                        .replace(R.id.navHostFragment, new TutorialFragmentA()).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}