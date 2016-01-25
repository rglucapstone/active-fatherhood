package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.content.Context;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.content.Intent;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.HomeActivity;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.data.Question;

import java.util.ArrayList;

/**
 * Created by ronald on 13/01/16.
 */
public class QuestionsAdapter extends ArrayAdapter<Question>{

    private Context context;
    private LayoutInflater inflater;
    public ArrayList<Question> questions;

    public QuestionsAdapter(Context context, ArrayList<Question> questions){
        super(context, R.layout.fragment_item_question, questions);
        this.context = context;
        //inflater = context.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Get the data item for this position
        final Question question = getItem(position);

        //ViewHolder viewHolder; // view lookup cache stored in tag

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            //viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_question, parent, false);
        }else{
            //viewHolder = (ViewHolder) convertView.getTag();
        }


        final ImageView ic_user = (ImageView) convertView.findViewById(R.id.ic_user);
        //convertView.setTag(viewHolder);

        TextView txt_quser = (TextView) convertView.findViewById(R.id.txt_quser);
        TextView txt_qdatetime = (TextView) convertView.findViewById(R.id.txt_qdatetime);
        TextView txt_qcontent = (TextView) convertView.findViewById(R.id.txt_qcontent);
        TextView txt_qanswers = (TextView) convertView.findViewById(R.id.txt_qanswers);

        //ic_user.setTag(question.user);
        txt_quser.setText(question.user.name);
        txt_qdatetime.setText(question.created);
        txt_qcontent.setText(question.content);
        txt_qanswers.setText(question.total_answers + " comentarios");

        ic_user.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class); //create an Intent object
                intent.putExtra("user_id", question.user.id); //add data to the Intent object
                context.startActivity(intent); //start the second activity
            }
        });


        // THEMES
        /*txt_qtags.setText(question.themes[0]);
        final TextView[] themes = new TextView[3];
        LinearLayout layout_themes = (LinearLayout) convertView.findViewById(R.id.ly_themes);
        LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layout_themes.removeAllViews();
        for (int i = 0; i < question.themes.length; i++) {
            final TextView theme = new TextView(this.getContext());
            theme.setLayoutParams(lparams);
            theme.setText(question.themes[i]);
            layout_themes.addView(theme);
            themes[i] = theme;
        }*/
        return convertView;
    }




}
