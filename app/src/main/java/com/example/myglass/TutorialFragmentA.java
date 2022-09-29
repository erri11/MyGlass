package com.example.myglass;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TutorialFragmentA extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tutorial_a, container, false);
        // Inflate the layout for this fragment

        //fragment bottone occhiali
        Button btnFragmentOcchiali = (Button)view.findViewById(R.id.btn_occ);
        btnFragmentOcchiali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new FragmentOcchialiSoV()).commit();
            }
        });

        //fragment bottone lenti
        Button btnFragmentLenti = (Button)view.findViewById(R.id.btn_lenti);
        btnFragmentLenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new FragmentLenti()).commit();
                //   startActivity(new Intent( getContext(), FragmentLenti.class));
            }
        });

        Button buttonIndietro = (Button)view.findViewById(R.id.btn_indietro4);
        buttonIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations( R.anim.enter_left_to_right,R.anim.exit_left_to_right,
                                R.anim.enter_right_to_left,R.anim.exit_right_to_left)
                        .replace(R.id.navHostFragment, new HomeFragment()).commit();
            }
        });
        return view;
    }
}