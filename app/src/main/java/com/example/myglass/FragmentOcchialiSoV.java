package com.example.myglass;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOcchialiSoV extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_occhiali_so_v, container, false);

        //fragment occhiali da vista
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button buttonOccVista = (Button)view.findViewById(R.id.btn_occVista);
        buttonOccVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations( R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new FragmentUDVista()).commit();
            }
        });

        Button buttonOccSole = (Button)view.findViewById(R.id.btn_occSole);
        buttonOccSole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new FragmentUDSole()).commit();
            }
        });

        Button buttonIndietro = (Button)view.findViewById(R.id.btn_indietro2);
        buttonIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =  getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations( R.anim.enter_left_to_right,R.anim.exit_left_to_right,
                                R.anim.enter_right_to_left,R.anim.exit_right_to_left)
                        .replace(R.id.navHostFragment, new TutorialFragmentA()).commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}