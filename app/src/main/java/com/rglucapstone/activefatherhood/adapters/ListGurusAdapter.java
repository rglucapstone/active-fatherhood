package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.Theme;
import com.rglucapstone.activefatherhood.data.User;

import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Created by ronald on 14/01/16.
 */
public class ListGurusAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<Theme> _listThemes;
    private HashMap<Theme, ArrayList<User>> _listGurus;
    private LayoutInflater mInflater;
    public User logged;

    public ListGurusAdapter(Context context, ArrayList<Theme> listThemes, HashMap<Theme, ArrayList<User>> listGurus) {
        this._context = context;
        this._listThemes = listThemes;
        this._listGurus = listGurus;
        //mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return this._listThemes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listGurus.get(this._listThemes.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listThemes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listGurus.get(this._listThemes.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //final ExpandableListView elv = (ExpandableListView) convertView.findViewById(R.id.exp_list_gurus);
        Theme theme = (Theme) getGroup(groupPosition);
        int total_gurus = getChildrenCount(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.view_categories_gurus, null);
        }
        TextView txt_tag = (TextView) convertView.findViewById(R.id.txt_tag);
        txt_tag.setText(theme.name);

        TextView txt_total_gurus = (TextView) convertView.findViewById(R.id.txt_total_gurus);
        txt_total_gurus.setText(" (" + total_gurus + ")");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ArrayList<User> listGurus = (ArrayList) this._listGurus.get(this._listThemes.get(groupPosition));
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.container_items_gurus, null);
        }

        GridView  gridView = (GridView) convertView.findViewById(R.id.gridView);
        gridView.setNumColumns(4);
        gridView.setHorizontalSpacing(4);

        ListContainerGurus adapter = new ListContainerGurus(_context, listGurus);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = listGurus.get(position);
                Intent intent = new Intent(_context, ProfileActivity.class);
                intent.putExtra("user_id", user.id);
                intent.putExtra("logged_id", logged.id);
                _context.startActivity(intent);
            }
        });

        /*int totalHeight = 0;
        for (int size = 0; size < adapter.getCount(); size++) {
            RelativeLayout relativeLayout = (RelativeLayout) adapter.getView(
                    size, null, gridView);
            TextView textView = (TextView) relativeLayout.getChildAt(0);
            textView.measure(0, 0);
            totalHeight += textView.getMeasuredHeight();
        }
        gridView.SetHeight(totalHeight);*/

        //ExpandableListView eplv = (ExpandableListView) convertView.findViewById(R.id.exp_list_gurus);
        //eplv.expandGroup(groupPosition);

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
