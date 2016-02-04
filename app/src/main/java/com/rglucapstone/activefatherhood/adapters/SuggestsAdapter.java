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
import com.rglucapstone.activefatherhood.activities.DoPost;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.Suggest;
import com.rglucapstone.activefatherhood.data.User;

import java.util.ArrayList;

/**
 * Created by ronald on 04/02/16.
 */
public class SuggestsAdapter extends ArrayAdapter{
    private Context context;
    public View view;
    public User logged;


    public SuggestsAdapter(Context context, ArrayList<Suggest> s){
        super(context, R.layout.fragment_item_suggest, s);
        this.context = context;
        //inflater = context.getWindow().getLayoutInflater();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Get the data item for this position
        final Suggest suggest = (Suggest) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_suggest, parent, false);
        }

        this.view = convertView;

        TextView txt_qcontent = (TextView) convertView.findViewById(R.id.txt_qcontent);
        txt_qcontent.setText(suggest.content);

        RelativeTimeTextView v = (RelativeTimeTextView) convertView.findViewById(R.id.txt_question_date);
        v.setReferenceTime(suggest.created_ago);

        TextView txt_quser = (TextView) convertView.findViewById(R.id.txt_quser);
        txt_quser.setText(suggest.user_request.login);


        final LinearLayout lnk_suggest = (LinearLayout) convertView.findViewById(R.id.item_suggest);
        lnk_suggest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, DoPost.class); //create an Intent object
                intent.putExtra("user_id", logged.id); //add data to the Intent object
                intent.putExtra("content", suggest.content); //add data to the Intent object
                intent.putExtra("source", "notification");
                intent.putExtra("user_request_id", suggest.user_request.id);
                intent.putExtra("user_answer_id", suggest.answer_id);
                context.startActivity(intent); //start the second activity
            }
        });


        return convertView;
    }
}
