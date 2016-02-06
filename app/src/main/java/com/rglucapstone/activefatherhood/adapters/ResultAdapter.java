package com.rglucapstone.activefatherhood.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.activities.QuestionActivity;
import com.rglucapstone.activefatherhood.data.Entity;
import com.rglucapstone.activefatherhood.data.Model;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;

import java.util.ArrayList;

/**
 * Created by ronald on 02/02/16.
 */
public class ResultAdapter extends ArrayAdapter {
    private Context context;
    public User logged;

    public ResultAdapter(Context context, ArrayList results){
        super(context, R.layout.fragment_item_result, results);
        this.context = context;
        //inflater = context.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Question entity = (Question) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_result, parent, false);
        }

        TextView ly_content = (TextView) convertView.findViewById(R.id.ly_content);
        ly_content.setText(entity.content);

        RelativeTimeTextView v = (RelativeTimeTextView) convertView.findViewById(R.id.txt_date);
        v.setReferenceTime(entity.created_ago);

        TextView txt_data = (TextView) convertView.findViewById(R.id.txt_data);
        txt_data.setText(entity.listAnswers.size() + " respuestas");

        final LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.ly_activity);
        layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, QuestionActivity.class); //create an Intent object
                intent.putExtra("question_id", entity.id);
                intent.putExtra("logged_id", logged.id);
                context.startActivity(intent); //start the second activity
            }
        });

        return convertView;
    }


}
