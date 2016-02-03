package com.rglucapstone.activefatherhood.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.data.Entity;
import com.rglucapstone.activefatherhood.data.Model;
import com.rglucapstone.activefatherhood.data.Question;

import java.util.ArrayList;

/**
 * Created by ronald on 02/02/16.
 */
public class ResultAdapter extends ArrayAdapter {
    private Context context;

    public ResultAdapter(Context context, ArrayList results){
        super(context, R.layout.fragment_item_result, results);
        this.context = context;
        //inflater = context.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Get the data item for this position
        final Question entity = (Question) getItem(position);

        //ViewHolder viewHolder; // view lookup cache stored in tag

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_result, parent, false);
        }

        TextView txt_content = (TextView) convertView.findViewById(R.id.txt_content);
        txt_content.setText(entity.content);

        TextView txt_date = (TextView) convertView.findViewById(R.id.txt_date);
        txt_date.setText(entity.created);

        TextView txt_user = (TextView) convertView.findViewById(R.id.txt_user);
        txt_user.setText(entity.user.login);

        return convertView;
    }


}
