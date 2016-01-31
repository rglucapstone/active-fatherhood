package com.rglucapstone.activefatherhood.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.activities.AskActivity;
import com.rglucapstone.activefatherhood.activities.PublicationsGuru;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Luisa Castro on 31/01/2016.
 */
public class ListContainerGurus extends BaseAdapter{

    private Context _context;
    private ArrayList<Guru> _listGurus;

    public ListContainerGurus(Context context, ArrayList<Guru> listGurus) {
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
        Guru guru = (Guru) this._listGurus.get(position);

        ViewHolder item_guru = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        txt_guser.setText(guru.user);


       // TextView txt_gpost = (TextView) convertView.findViewById(R.id.txt_gpost);
        return convertView;
    }

    static class ViewHolder {
        LinearLayout item_guru;
    }
}
