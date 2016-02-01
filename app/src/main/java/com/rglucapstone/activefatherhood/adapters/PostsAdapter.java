package com.rglucapstone.activefatherhood.adapters;

import android.app.Activity;
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
import com.rglucapstone.activefatherhood.data.Post;

import java.util.ArrayList;

/**
 * Created by ronald on 14/01/16.
 */
public class PostsAdapter extends ArrayAdapter<Post> {

    private LayoutInflater inflater;
    private Context context;
    public View view;

    public PostsAdapter(Context context, ArrayList<Post> posts){
        super(context, R.layout.fragment_item_post, posts);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_post, parent, false);
            //return inflater.inflate(R.layout.fragment_item_post, parent, false);
        }
        final Post post = getItem(position);

        this.view = convertView;

        TextView txt_ptitle = (TextView) convertView.findViewById(R.id.txt_ptitle);
        txt_ptitle.setText(post.title);

        TextView txt_qcontent = (TextView) convertView.findViewById(R.id.txt_qcontent);
        txt_qcontent.setText(post.content);

        //TextView txt_pdatetime = (TextView) convertView.findViewById(R.id.txt_date_post);
        //txt_pdatetime.setText(post.created);
        RelativeTimeTextView v = (RelativeTimeTextView) convertView.findViewById(R.id.txt_post_date);
        v.setReferenceTime(post.created_ago);

        TextView txt_puser = (TextView) convertView.findViewById(R.id.txt_puser);
        txt_puser.setText(post.user.login);

        TextView txt_post_likes = (TextView) convertView.findViewById(R.id.txt_post_likes);
        txt_post_likes.setText(post.likes + " me gusta");

        TextView txt_post_comments = (TextView) convertView.findViewById(R.id.txt_post_comments);
        txt_post_comments.setText(post.listComments.size() + " comentarios");


        setTags();
        for (int i = 1; i <= post.themes.length; i++) {
            int index = i - 1;

            int imgtag = getContext().getResources().getIdentifier("ic_post_tag" + i, "id", getContext().getPackageName());
            ImageView img_tag = (ImageView) convertView.findViewById(imgtag);
            img_tag.setVisibility(View.VISIBLE);

            int txttag = getContext().getResources().getIdentifier("txt_post_tag" + i, "id", getContext().getPackageName());
            TextView txt_tag = (TextView) convertView.findViewById(txttag);
            txt_tag.setText(post.themes[index]);
            txt_tag.setVisibility(View.VISIBLE);
        }



        final ImageView link_user = (ImageView) convertView.findViewById(R.id.ic_post_user);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(context, ProfileActivity.class); //create an Intent object
                intent.putExtra("user_id", post.user.id); //add data to the Intent object
                context.startActivity(intent); //start the second activity
            }
        });

        return convertView;
    }

    public void setTags(){

        int total = 3;
        for (int i = 1; i <= total; i++) {
            int imgtag = getContext().getResources().getIdentifier("ic_post_tag" + i, "id", getContext().getPackageName());
            ImageView img_tag = (ImageView) this.view.findViewById(imgtag);
            img_tag.setVisibility(View.GONE);

            int txttag = getContext().getResources().getIdentifier("txt_post_tag" + i, "id", getContext().getPackageName());
            TextView txt_tag = (TextView) this.view.findViewById(txttag);
            txt_tag.setVisibility(View.GONE);

        }

    }
}