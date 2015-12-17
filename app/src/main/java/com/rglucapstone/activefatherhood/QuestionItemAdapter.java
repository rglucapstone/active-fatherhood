package com.rglucapstone.activefatherhood;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import 	java.util.ArrayList;
import android.widget.TextView;
import com.rglucapstone.activefatherhood.apis.Question;

import static android.content.Intent.getIntent;

/**
 * Created by ronald on 16/12/15.
 */
public class QuestionItemAdapter extends ArrayAdapter<Question>{
    private LayoutInflater inflater;

    public QuestionItemAdapter(Activity activity, ArrayList<Question> questions){
        super(activity, R.layout.item_question, questions);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            return inflater.inflate(R.layout.item_question, parent, false);
        }
        Question question = getItem(position);
        TextView questionUser = (TextView) convertView.findViewById(R.id.questionUser);
        TextView questionDate = (TextView) convertView.findViewById(R.id.questionDate);
        TextView questionContent = (TextView) convertView.findViewById(R.id.questionContent);

        questionUser.setText(question.user);
        questionDate.setText(question.date);
        questionContent.setText(question.content);

        //return inflater.inflate(R.layout.item_question, parent, false);
        return convertView;
    }

}
