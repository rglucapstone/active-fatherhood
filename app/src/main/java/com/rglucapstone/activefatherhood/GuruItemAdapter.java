package com.rglucapstone.activefatherhood;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.apis.PadreGuru;

import java.util.ArrayList;

/**
 * Created by ronald on 07/01/16.
 */
public class GuruItemAdapter extends ArrayAdapter<PadreGuru> {
    private LayoutInflater inflater;

    public GuruItemAdapter(Activity activity, ArrayList<PadreGuru> infoguru){
        super(activity, R.layout.item_padre_guru, infoguru);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            return inflater.inflate(R.layout.item_padre_guru, parent, false);
        }
        PadreGuru infoguru = getItem(position);
        TextView guruUser = (TextView) convertView.findViewById(R.id.guruUser);
        //TextView questionDate = (TextView) convertView.findViewById(R.id.questionDate);
        //TextView questionContent = (TextView) convertView.findViewById(R.id.questionContent);

        guruUser.setText(infoguru.user);
        //questionDate.setText(question.date);
        //questionContent.setText(question.content);

        //return inflater.inflate(R.layout.item_question, parent, false);
        return convertView;
    }

}