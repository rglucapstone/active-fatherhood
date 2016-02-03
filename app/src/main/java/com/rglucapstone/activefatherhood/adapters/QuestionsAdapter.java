package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.content.Context;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.content.Intent;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.HomeActivity;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.data.Question;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ronald on 13/01/16.
 */
public class QuestionsAdapter extends ArrayAdapter<Question>{

    private Context context;
    private LayoutInflater inflater;
    public ArrayList<Question> questions;
    public Question question;
    public View view;

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

        this.view = convertView;


        final ImageView ic_user = (ImageView) convertView.findViewById(R.id.ic_user);
        //convertView.setTag(viewHolder);

        // username - login
        TextView txt_quser = (TextView) convertView.findViewById(R.id.txt_quser);
        txt_quser.setText(question.user.login);


        //TextView txt_question_date = (TextView) convertView.findViewById(R.id.txt_question_date);
        RelativeTimeTextView v = (RelativeTimeTextView) convertView.findViewById(R.id.txt_question_date);
        v.setReferenceTime(question.created_ago);
        //txt_question_date.setText(question.created);

        //TextView txt_qdatetime = (TextView) convertView.findViewById(R.id.txt_qdatetime);
        TextView txt_qcontent = (TextView) convertView.findViewById(R.id.txt_qcontent);
        TextView txt_qanswers = (TextView) convertView.findViewById(R.id.txt_qanswers);

        //ic_user.setTag(question.user);

        //txt_qdatetime.setText(question.created);
        txt_qcontent.setText(question.content);
        txt_qanswers.setText(question.listAnswers.size() + " respuestas");

        setTags();
        for (int i = 1; i <= question.themes.length; i++) {
            int index = i - 1;

            int imgtag = getContext().getResources().getIdentifier("ic_question_tag" + i, "id", getContext().getPackageName());
            ImageView img_tag = (ImageView) convertView.findViewById(imgtag);
            img_tag.setVisibility(View.VISIBLE);

            int txttag = getContext().getResources().getIdentifier("txt_question_tag" + i, "id", getContext().getPackageName());
            TextView txt_tag = (TextView) convertView.findViewById(txttag);
            txt_tag.setText(question.themes[index]);
            txt_tag.setVisibility(View.VISIBLE);
        }

        final RelativeLayout link_user = (RelativeLayout) convertView.findViewById(R.id.link_user);
        link_user.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                //TextView txt_qanswers = (TextView) view.findViewById(R.id.txt_qanswers);
                //txt_qanswers.setText(question.user.id);

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

    public void setTags(){

        int total = 3;
        for (int i = 1; i <= total; i++) {
            int imgtag = getContext().getResources().getIdentifier("ic_question_tag" + i, "id", getContext().getPackageName());
            ImageView img_tag = (ImageView) this.view.findViewById(imgtag);
            img_tag.setVisibility(View.GONE);

            int txttag = getContext().getResources().getIdentifier("txt_question_tag" + i, "id", getContext().getPackageName());
            TextView txt_tag = (TextView) this.view.findViewById(txttag);
            txt_tag.setVisibility(View.GONE);

        }

    }




}
