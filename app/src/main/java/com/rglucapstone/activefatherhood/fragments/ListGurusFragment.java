package com.rglucapstone.activefatherhood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.ListFragment;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import java.util.HashMap;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.ListGurusAdapter;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.data.Question;

import java.util.ArrayList;

/**
 * Created by ronald on 09/01/16.
 */
public class ListGurusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_gurus, null);
        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.exp_list_gurus);

        ArrayList<String> listTags = new ArrayList<>();
        HashMap<String, ArrayList<Guru>> listGurus = new HashMap<>();
        prepareListData(listTags, listGurus);

        elv.setAdapter(new ListGurusAdapter(getActivity(), listTags, listGurus));
        return v;
    }

    private void prepareListData(ArrayList tags, HashMap gurus) {

        tags.add("Salud");
        ArrayList<Guru> salud = new ArrayList<>();
        Guru g1 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        salud.add(g1);
        Guru g2 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        salud.add(g2);
        Guru g3 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        salud.add(g3);

        tags.add("Adolescentes");
        ArrayList<Guru> adolescentes = new ArrayList<>();
        Guru g4 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g4);
        Guru g5 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g5);
        Guru g6 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g6);

        gurus.put(tags.get(0), salud);
        gurus.put(tags.get(1), adolescentes);

    }
}
