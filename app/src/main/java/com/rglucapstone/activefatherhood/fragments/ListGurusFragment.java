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
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.ListGurusAdapter;
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.Theme;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ronald on 09/01/16.
 */
public class ListGurusFragment extends Fragment {

    public ArrayList<String> listThemes;
    public HashMap<String, ArrayList<Guru>> listGurus;

    public ExpandableListView elv;
    public ListGurusAdapter adapter;
    public View view;

    public static ArrayList<Integer> check = new ArrayList<Integer>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_gurus, null);
        this.view = v;

        this.elv = (ExpandableListView) v.findViewById(R.id.exp_list_gurus);
        this.elv.setChildDivider(getResources().getDrawable(R.color.backgroundContainer));
        this.elv.setDivider(getResources().getDrawable(R.color.backgroundContainer));
        this.elv.setDividerHeight(20);
        this.elv.setFooterDividersEnabled(true);

        Guru guru = new Guru(new loadGurus());
        guru.getAll();

        return v;
    }

    private class loadGurus extends RestfulClient {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                ArrayList<Theme> listThemes = new ArrayList<>();
                HashMap<Theme, ArrayList<User>> hashGurus = Guru.fromJsontoHash(result.getJSONArray("data"));
                Iterator ithemes = hashGurus.keySet().iterator();
                while(ithemes.hasNext()) {
                    Theme t = (Theme) ithemes.next();
                    listThemes.add(t);
                }
                adapter = new ListGurusAdapter(getActivity(), listThemes, hashGurus);
                elv.setAdapter(adapter);
                //User u = hashGurus.get("comportamiento").get(0);
                //TextView txt_quser = (TextView) view.findViewById(R.id.txt_test);
                //txt_quser.setText(u.name);
            }catch (JSONException e){
                e.printStackTrace();
            }
            RelativeLayout loadingLayout = (RelativeLayout) view.findViewById(R.id.loading_gurus);
            loadingLayout.setVisibility(View.GONE);
        }

    }
}
