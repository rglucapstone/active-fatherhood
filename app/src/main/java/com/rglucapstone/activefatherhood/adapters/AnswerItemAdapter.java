package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Answer;
import com.rglucapstone.activefatherhood.data.Question;

import java.util.ArrayList;

/**
 * Created by ronald on 17/12/15.
 */
public class AnswerItemAdapter extends ArrayAdapter<Answer>{
    private LayoutInflater inflater;
    private Context context;

    public AnswerItemAdapter(Context context, ArrayList<Answer> answers) {
        super(context, R.layout.fragment_list_answers, answers);
        this.context = context;
    }


    /*public AnswerItemAdapter(Activity activity, String[] items){
        super(activity, R.layout.item_answer, items);
        inflater = activity.getWindow().getLayoutInflater();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Answer answer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_answer, parent, false);
        }else{ }

        TextView txt_content = (TextView) convertView.findViewById(R.id.answer_content);
        txt_content.setText(answer.content);

        TextView txt_date = (TextView) convertView.findViewById(R.id.answer_date);
        txt_date.setText(answer.created);

        TextView txt_user = (TextView) convertView.findViewById(R.id.answer_user);
        txt_user.setText(answer.user.name);

        TextView txt_likes = (TextView) convertView.findViewById(R.id.txt_likes);
        txt_likes.setText(answer.likes);

        return convertView;
    }
}
