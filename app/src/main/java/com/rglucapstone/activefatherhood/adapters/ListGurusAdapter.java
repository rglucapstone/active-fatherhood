package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.data.Question;

import android.widget.TextView;
import android.content.Context;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Created by ronald on 14/01/16.
 */
public class ListGurusAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<String> _listTags;
    private HashMap<String, ArrayList<Guru>> _listGurus;

    public ListGurusAdapter(Context context, ArrayList<String> listTags, HashMap<String, ArrayList<Guru>> listGurus) {
        this._context = context;
        this._listTags = listTags;
        this._listGurus = listGurus;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listGurus.get(this._listTags.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listGurus.get(this._listTags.get(groupPosition)).size();
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Guru guru = (Guru) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.view_items_gurus, null);
        }
/*
        TextView txt_guser = (TextView) convertView.findViewById(R.id.txt_guser);
        TextView txt_gpost = (TextView) convertView.findViewById(R.id.txt_gpost);
        TextView txt_gvotes = (TextView) convertView.findViewById(R.id.txt_gvotes);

        txt_guser.setText(guru.user);
       // txt_gpost.setText(guru.posts)txt_gvotes.setText(guru.votes);*/

        return convertView;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listTags.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listTags.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String tag = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.view_categories_gurus, null);
        }

        TextView txt_tag = (TextView) convertView.findViewById(R.id.txt_tag);
        txt_tag.setText(tag);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
