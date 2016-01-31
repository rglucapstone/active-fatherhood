package com.rglucapstone.activefatherhood.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.ListFragment;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;

import java.util.HashMap;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.ListGurusAdapter;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.data.Question;

import java.util.ArrayList;

/**
 * Created by ronald on 09/01/16.
 */
public class ListGurusFragment_old extends Fragment {

    public static ArrayList<Integer> check = new ArrayList<Integer>();
    @Override
    //public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_gurus, null);
        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.exp_list_gurus);

        // View v = inflater.inflate(R.layout.fragment_list_gurus, null);
        ArrayList<String> listTags = new ArrayList<>();
        HashMap<String, ArrayList<Guru>> listGurus = new HashMap<>();
        prepareListData(listTags, listGurus);

        //ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.exp_list_gurus);
        elv.setChildDivider(getResources().getDrawable(R.color.backgroundContainer));
        elv.setDivider(getResources().getDrawable(R.color.lightGrey));
        elv.setDividerHeight(25);
        elv.setFooterDividersEnabled(true);
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

        tags.add("Educación");
        ArrayList<Guru> adolescentes = new ArrayList<>();
        Guru g4 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g4);
        Guru g5 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g5);
        Guru g6 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g6);

        tags.add("Problemas Sociales");
        ArrayList<Guru> problemas_sociales = new ArrayList<>();
        Guru g7 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g7);
        Guru g8 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g8);
        Guru g9 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g9);

        tags.add("Sexualidad");
        ArrayList<Guru> sexualidad = new ArrayList<>();
        Guru g10 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g10);
        Guru g11 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g11);
        Guru g12 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g12);

        tags.add("Disciplina");
        ArrayList<Guru> disciplina = new ArrayList<>();
        Guru g13 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g13);
        Guru g14 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g14);
        Guru g15 = new Guru("Pedro Xavier", "13 publicaciones", "3 votos");
        adolescentes.add(g15);

        gurus.put(tags.get(0), salud);
        gurus.put(tags.get(1), adolescentes);
        gurus.put(tags.get(2), problemas_sociales);
        gurus.put(tags.get(3), sexualidad);
        gurus.put(tags.get(4), disciplina);

    }
}
