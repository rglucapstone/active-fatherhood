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
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.data.Post;
import com.rglucapstone.activefatherhood.data.User;

import java.util.ArrayList;

/**
 * Created by ronald on 14/01/16.
 */
public class PostsAdapter extends ArrayAdapter<Post> {

    private User user;


    private LayoutInflater inflater;
    private Context context;
    public View view;
    public User logged;
    public User user_post;
    public String user_id;
    public ImageView link_user;

    public PostsAdapter(Context context, ArrayList<Post> posts, User user){
        super(context, R.layout.fragment_item_post, posts);
        this.context = context;
        this.user = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Post post = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_item_post, parent, false);
        }
        setData(convertView, post);
        setActions(convertView, post);
        return convertView;
    }

    private void setActions(final View v, final Post p){

        link_user = (ImageView) v.findViewById(R.id.ic_post_user);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class); //create an Intent object
                intent.putExtra("user_id", p.user.id); //add data to the Intent object
                intent.putExtra("logged_id", user.id);
                context.startActivity(intent); //start the second activity
            }
        });
        setImageUser(link_user, p.user.id);

    }

    private void setData(View v, Post p){

        TextView txt_ptitle = (TextView) v.findViewById(R.id.txt_ptitle);
        txt_ptitle.setText(p.title);

        TextView txt_qcontent = (TextView) v.findViewById(R.id.txt_qcontent);
        txt_qcontent.setText(p.content);

        RelativeTimeTextView vdate = (RelativeTimeTextView) v.findViewById(R.id.txt_post_date);
        vdate.setReferenceTime(p.created_ago);

        TextView txt_puser = (TextView) v.findViewById(R.id.txt_puser);
        txt_puser.setText(p.user.login);

        TextView txt_post_likes = (TextView) v.findViewById(R.id.txt_post_likes);
        txt_post_likes.setText(p.likes.size() + " me gusta");

        TextView txt_post_comments = (TextView) v.findViewById(R.id.txt_post_comments);
        txt_post_comments.setText(p.listComments.size() + " comentarios");

        setTags(v);
        for (int i = 1; i <= p.themes.length; i++) {
            int index = i - 1;

            int imgtag = getContext().getResources().getIdentifier("ic_post_tag" + i, "id", getContext().getPackageName());
            ImageView img_tag = (ImageView) v.findViewById(imgtag);
            img_tag.setVisibility(View.VISIBLE);

            int txttag = getContext().getResources().getIdentifier("txt_post_tag" + i, "id", getContext().getPackageName());
            TextView txt_tag = (TextView) v.findViewById(txttag);
            txt_tag.setText(p.themes[index]);
            txt_tag.setVisibility(View.VISIBLE);
        }

    }

    public void setTags(View v){
        int total = 3;
        for (int i = 1; i <= total; i++) {
            int imgtag = getContext().getResources().getIdentifier("ic_post_tag" + i, "id", getContext().getPackageName());
            ImageView img_tag = (ImageView) v.findViewById(imgtag);
            img_tag.setVisibility(View.GONE);

            int txttag = getContext().getResources().getIdentifier("txt_post_tag" + i, "id", getContext().getPackageName());
            TextView txt_tag = (TextView) v.findViewById(txttag);
            txt_tag.setVisibility(View.GONE);
        }
    }

    public void setImageUser(ImageView img_view,String id){
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
