package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rglucapstone.activefatherhood.R;

/**
 * Created by ronald on 17/12/15.
 */
public class AnswerItemAdapter extends ArrayAdapter{
    private LayoutInflater inflater;

    public AnswerItemAdapter(Activity activity, String[] items){
        super(activity, R.layout.item_answer, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return inflater.inflate(R.layout.item_answer, parent, false);
    }
}
