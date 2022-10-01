package com.example.myglass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Occhiale[] occhiali;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button btnFragmentTutorial = (Button) view.findViewById(R.id.btn_ge);
        btnFragmentTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        .replace(R.id.navHostFragment, new TutorialFragmentA()).commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HttpClient c = new HttpClient();
        c.downloadJson("http://10.0.2.2:4444/getallCatalogue");
        occhiali = c.GetOcchiali();
        while (occhiali == null){
            occhiali = c.GetOcchiali();
        }

        ListView listView = (ListView)view.findViewById(R.id.list_allCatalogue);
        ListAdapter adapter = new ListAdapter(getActivity().getApplicationContext(), occhiali);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        FragmentOcchialiDetails occhialiDetails = new FragmentOcchialiDetails(occhiali[position], position);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(this.getId(), occhialiDetails)
                .addToBackStack(null)
                .commit();    }
}