package com.rglucapstone.activefatherhood.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    public CommentItemAdapter(Context context, ArrayList<Comment> comments) {
        super(context, R.layout.fragment_list_comments, comments);
        this.context = context;
    }


    /*public AnswerItemAdapter(Activity activity, String[] items){
        super(activity, R.layout.item_answer, items);
        inflater = activity.getWindow().getLayoutInflater();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Comment comment = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, parent, false);
        }else{ }

        TextView txt_user = (TextView) convertView.findViewById(R.id.txt_comment_user);
        txt_user.setText(comment.user.login);

        TextView txt_date = (TextView) convertView.findViewById(R.id.txt_comment_date);
        txt_date.setText(comment.created);

        TextView txt_content = (TextView) convertView.findViewById(R.id.txt_comment_content);
        txt_content.setText(comment.content);

        //TextView txt_likes = (TextView) convertView.findViewById(R.id.txt_likes);
        //txt_likes.setText(answer.likes);

        // Set image user (temporal)
        link_user = (ImageView) convertView.findViewById(R.id.iconUser);
        user_comment = comment.user;
        setImageUser(link_user,user_comment.id);

        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("user_id", user_comment.id);
                context.startActivity(intent);
            }
        });

        return convertView;
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
