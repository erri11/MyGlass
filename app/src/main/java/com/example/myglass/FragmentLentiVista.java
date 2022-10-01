package com.example.myglass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class FragmentLentiVista extends Fragment implements AdapterView.OnItemClickListener {

    private Occhiale[] occhiali;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lenti_vista, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HttpClient c = new HttpClient();
        c.downloadJson("http://10.0.2.2:4444/selectLentiVista");
        occhiali = c.GetOcchiali();
        while (occhiali == null) {
            occhiali = c.GetOcchiali();
        }

        ListView listView = (ListView) view.findViewById(R.id.list_lenti_vista);
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
                .commit();
    }
}