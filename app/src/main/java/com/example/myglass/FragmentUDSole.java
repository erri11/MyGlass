package com.example.myglass;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentUDSole extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_u_d_sole, container, false);
        // Inflate the layout for this fragment

        //fragment occhiali da sole
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button buttonFragmentSol = (Button)view.findViewById(R.id.btn_occUs);
        buttonFragmentSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new FragmentOcchialiSoleU()).commit();
            }
        });

        //fragment occhiali da vista
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button buttonFragmentVis = (Button)view.findViewById(R.id.btn_occDs);
        buttonFragmentVis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new FragmentOcchialiSoleD()).commit();
            }
        });

        //fragment occhiali da vista
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button buttonIndietro3 = (Button)view.findViewById(R.id.btn_indietro3);
        buttonIndietro3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(R.anim.enter_left_to_right,R.anim.exit_left_to_right,
                                R.anim.enter_right_to_left,R.anim.exit_right_to_left)
                        .replace(R.id.navHostFragment, new FragmentOcchialiSoV()).commit();
            }
        });
        return view;
    }
}