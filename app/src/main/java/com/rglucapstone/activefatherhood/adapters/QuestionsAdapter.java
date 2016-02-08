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
import com.rglucapstone.activefatherhood.data.User;

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
    private User user;

    public QuestionsAdapter(Context context, ArrayList<Question> questions, User user){
        super(context, R.layout.fragment_item_question, questions);
        this.context = context;
        this.user = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Question question = getItem(position); // Get the data item for this position

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_question, parent, false);
        }
        setData(convertView, question);
        setActions(convertView, question);
        return convertView;
    }

    // setting data
    private void setData(View v, Question q){

        // relative date
        RelativeTimeTextView date = (RelativeTimeTextView) v.findViewById(R.id.txt_question_date);
        date.setReferenceTime(q.created_ago);

        // content
        TextView txt_qcontent = (TextView) v.findViewById(R.id.txt_qcontent);
        txt_qcontent.setText(q.content);

        // total answers
        TextView txt_qanswers = (TextView) v.findViewById(R.id.txt_qanswers);
        txt_qanswers.setText(q.listAnswers.size() + " respuestas");

        // login
        TextView txt_quser = (TextView) v.findViewById(R.id.txt_quser);
        txt_quser.setText(q.user.login);

        // tags of themes
        setTags(v);
        for (int i = 1; i <= q.themes.length; i++) {
            int index = i - 1;

            int imgtag = getContext().getResources().getIdentifier("ic_question_tag" + i, "id", getContext().getPackageName());
            ImageView img_tag = (ImageView) v.findViewById(imgtag);
            img_tag.setVisibility(View.VISIBLE);

            int txttag = getContext().getResources().getIdentifier("txt_question_tag" + i, "id", getContext().getPackageName());
            TextView txt_tag = (TextView) v.findViewById(txttag);
            txt_tag.setText(q.themes[index]);
            txt_tag.setVisibility(View.VISIBLE);
        }

    }

    // setting all three tags to invisible
    private void setTags(View v){

        int total = 3;
        for (int i = 1; i <= total; i++) {
            int imgtag = getContext().getResources().getIdentifier("ic_question_tag" + i, "id", getContext().getPackageName());
            ImageView img_tag = (ImageView) v.findViewById(imgtag);
            img_tag.setVisibility(View.GONE);

            int txttag = getContext().getResources().getIdentifier("txt_question_tag" + i, "id", getContext().getPackageName());
            TextView txt_tag = (TextView) v.findViewById(txttag);
            txt_tag.setVisibility(View.GONE);
        }
    }

    // setting actions
    private void setActions(View v, final Question q){

        // link to open profile
        final RelativeLayout link_user = (RelativeLayout) v.findViewById(R.id.link_user);
        link_user.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class); //create an Intent object
                intent.putExtra("user_id", q.user.id); //add data to the Intent object
                intent.putExtra("logged_id", user.id);
                context.startActivity(intent); //start the second activity
            }
        });

        // setting profile image
        final  ImageView img_user = (ImageView) v.findViewById(R.id.ic_user); // set image user temporal
        setImageUser(img_user, q.user.id);
    }

    // setting profile image
    private void setImageUser(ImageView img_view,String id){
        switch (id){
            case "1":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            case "2":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "3":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            case "4":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "5":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "6":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "8":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "9":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "10":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "11":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "13":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "15":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "14":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "16":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            default:
                img_view.setBackgroundResource(R.drawable.ico_profile_grey);
                break;
        }
    }

}
