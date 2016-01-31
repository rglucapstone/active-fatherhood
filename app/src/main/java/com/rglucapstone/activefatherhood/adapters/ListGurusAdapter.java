package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.data.Question;

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
    private ArrayList<String> _listTags;
    private HashMap<String, ArrayList<Guru>> _listGurus;
    private LayoutInflater mInflater;

    public ListGurusAdapter(Context context, ArrayList<String> listTags, HashMap<String, ArrayList<Guru>> listGurus) {
        this._context = context;
        this._listTags = listTags;
        this._listGurus = listGurus;
        //mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return this._listTags.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listGurus.get(this._listTags.get(groupPosition)).size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return this._listTags.get(groupPosition);
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
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //final ExpandableListView elv = (ExpandableListView) convertView.findViewById(R.id.exp_list_gurus);

        String tag = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.view_categories_gurus, null);
        }

        TextView txt_tag = (TextView) convertView.findViewById(R.id.txt_tag);
        txt_tag.setText(tag);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        //Guru guru = (Guru) getChild(groupPosition, childPosition);
        ArrayList<Guru> listGurus = (ArrayList) this._listGurus.get(this._listTags.get(groupPosition));
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
               // text.setText((String) (gridView.getItemAtPosition(position)));
               // Log.i("ITEM_CLICKED", "" + (String) (gridView.getItemAtPosition(position)));
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
