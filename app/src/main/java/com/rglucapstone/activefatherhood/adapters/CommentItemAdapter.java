package com.rglucapstone.activefatherhood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Comment;

import java.util.ArrayList;

/**
 * Created by ronald on 31/01/16.
 */
public class CommentItemAdapter extends ArrayAdapter<Comment> {
    private LayoutInflater inflater;
    private Context context;

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

        return convertView;
    }
}
