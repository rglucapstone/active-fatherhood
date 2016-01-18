package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Question;

import java.util.ArrayList;

/**
 * Created by ronald on 13/01/16.
 */
public class ListQuestionsAdapter extends ArrayAdapter<Question>{

    private LayoutInflater inflater;

    public ArrayList<Question> questions;

    public ListQuestionsAdapter(Activity activity, ArrayList<Question> questions){
        super(activity, R.layout.fragment_item_question, questions);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            return inflater.inflate(R.layout.fragment_item_question, parent, false);
        }
        Question question = getItem(position);
        TextView txt_quser = (TextView) convertView.findViewById(R.id.txt_quser);
        TextView txt_qdatetime = (TextView) convertView.findViewById(R.id.txt_qdatetime);
        TextView txt_qcontent = (TextView) convertView.findViewById(R.id.txt_qcontent);
        TextView txt_qtags = (TextView) convertView.findViewById(R.id.txt_qtags);
        TextView txt_qlikes = (TextView) convertView.findViewById(R.id.txt_qlikes);
        TextView txt_qanswers = (TextView) convertView.findViewById(R.id.txt_qanswers);

        txt_quser.setText(question.user.name);
        txt_qdatetime.setText(question.created);
        txt_qcontent.setText(question.content);
        txt_qtags.setText("5"); //txt_qtags.setText(question.tags);
        txt_qlikes.setText("10"); //txt_qlikes.setText(question.likes);
        //txt_qanswers.setText(question.answers);

        return convertView;
    }




}
