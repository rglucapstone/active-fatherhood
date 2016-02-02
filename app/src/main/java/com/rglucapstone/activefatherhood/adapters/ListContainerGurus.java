package com.rglucapstone.activefatherhood.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.activities.AskActivity;
import com.rglucapstone.activefatherhood.activities.PublicationsGuru;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Luisa Castro on 31/01/2016.
 */
public class ListContainerGurus extends BaseAdapter{

    private Context _context;
    private ArrayList<User> _listGurus;
    private final int num_starts = 4;

    public ListContainerGurus(Context context, ArrayList<User> listGurus) {
        this._listGurus = listGurus;
        _context = context;
    }
    @Override
    public int getCount() {
        return _listGurus.size();
    }

    @Override
    public Object getItem(int position) {
        //return position;
        return _listGurus.get(position);
    }

    @Override
    public long getItemId(int position) {
         return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = (User) this._listGurus.get(position);

        ViewHolder item_guru = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_items_gurus, parent, false);

            /*RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.item_guru);
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater  = (LayoutInflater) _context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = inflater .inflate(R.layout.activity_login, null);
                }
            });*/

           // convertView.setTag(holder);
        } else {
            item_guru = (ViewHolder) convertView.getTag();
        }



        TextView txt_guser = (TextView) convertView.findViewById(R.id.txt_guser);
        txt_guser.setText(user.login);

        TextView txt_grating = (TextView) convertView.findViewById(R.id.txt_grating);
        txt_grating.setText(user.kind_dad);

        RatingBar rate_guru = (RatingBar) convertView.findViewById(R.id.ratingBar);
        String str_rate = new DecimalFormat("##.##").format((user.rating*this.num_starts)/100);
        rate_guru.setRating(Float.parseFloat(str_rate));

        return convertView;
    }

    static class ViewHolder {
        LinearLayout item_guru;
    }
}
