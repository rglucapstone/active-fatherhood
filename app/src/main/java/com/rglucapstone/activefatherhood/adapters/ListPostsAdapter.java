package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Post;

import java.util.ArrayList;

/**
 * Created by ronald on 14/01/16.
 */
public class ListPostsAdapter extends ArrayAdapter<Post> {

    private LayoutInflater inflater;

    public ListPostsAdapter(Activity activity, ArrayList<Post> posts){
        super(activity, R.layout.fragment_item_post, posts);
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            return inflater.inflate(R.layout.fragment_item_post, parent, false);
        }
        Post post = getItem(position);
        TextView txt_ptitle = (TextView) convertView.findViewById(R.id.txt_ptitle);
        TextView txt_puser = (TextView) convertView.findViewById(R.id.txt_puser);
        TextView txt_pdatetime = (TextView) convertView.findViewById(R.id.txt_date_post);
        TextView txt_ptags = (TextView) convertView.findViewById(R.id.txt_ptags);
        TextView txt_plikes = (TextView) convertView.findViewById(R.id.txt_plikes);
        TextView txt_pcomments = (TextView) convertView.findViewById(R.id.txt_pcomments);

        txt_ptitle.setText(post.title);
        txt_puser.setText(post.user);
        txt_pdatetime.setText(post.datetime);
        txt_ptags.setText(post.tags);
        txt_plikes.setText(post.likes);
        txt_pcomments.setText(post.comments);

        return convertView;
    }
}
