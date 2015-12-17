package com.rglucapstone.activefatherhood.questions;

import android.app.ListFragment;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import java.lang.String;

import com.rglucapstone.activefatherhood.QuestionItemAdapter;
import com.rglucapstone.activefatherhood.R;

/**
 * Created by ronald on 16/12/15.
 */
public class ListQuestionsFragment extends ListFragment{

    private String[] stringArray;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        stringArray = new String[10];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.item_question, stringArray);
        setListAdapter(adapter);
    }


    /*@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }*/


    /*//@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        listQuestionsAdapter = new QuestionItemAdapter(this, new String[10]);
        setListAdapter(listQuestionsAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }*/

}
