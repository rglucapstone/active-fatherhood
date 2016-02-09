package com.rglucapstone.activefatherhood.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.data.Comment;
import com.rglucapstone.activefatherhood.data.User;

import java.util.ArrayList;

/**
 * Created by ronald on 31/01/16.
 */
public class CommentItemAdapter extends ArrayAdapter<Comment> {
    private LayoutInflater inflater;
    private Context context;
    public ImageView link_user;
    public User user_comment;

    private User logged;

    public CommentItemAdapter(Context context, ArrayList<Comment> comments, User logged) {
        super(context, R.layout.fragment_list_comments, comments);
        this.context = context;
        this.logged = logged;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Comment comment = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
        }
        setData(convertView, comment);
        setActions(convertView, comment);

        return convertView;
    }

    private void setActions(final View v, final Comment c){

        link_user = (ImageView) v.findViewById(R.id.icon_user_comment);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("user_id", c.user.id);
                intent.putExtra("logged_id", logged.id);
                context.startActivity(intent);
            }
        });
    }

    private void setData(View v, Comment c){
        TextView txt_user = (TextView) v.findViewById(R.id.txt_comment_user);
        txt_user.setText(c.user.login);

        RelativeTimeTextView date = (RelativeTimeTextView) v.findViewById(R.id.comment_date);
        date.setReferenceTime(c.created_ago);

        TextView txt_content = (TextView) v.findViewById(R.id.txt_comment_content);
        txt_content.setText(c.content);

        link_user = (ImageView) v.findViewById(R.id.icon_user_comment);
        setImageUser(link_user, c.user.id);
    }



    public void setImageUser(ImageView img_view,String id){
        //Toast.makeText(context, "hola" + id , Toast.LENGTH_SHORT).show();
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
                img_view.setBackgroundResource(R.drawable.ico_avatar_white);
                break;
        }
    }
}
