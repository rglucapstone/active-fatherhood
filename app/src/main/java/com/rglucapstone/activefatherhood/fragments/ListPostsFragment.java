package com.rglucapstone.activefatherhood.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.ListFragment;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.PostActivity;
import com.rglucapstone.activefatherhood.activities.QuestionActivity;
import com.rglucapstone.activefatherhood.adapters.PostsAdapter;
import com.rglucapstone.activefatherhood.data.Post;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 09/01/16.
 */
public class ListPostsFragment extends ListFragment {

    public View view;
    public ArrayList<Post> list;
    public PostsAdapter adapter;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_posts, container, false);
        this.view = view;

        Post post = new Post(new loadPosts());
        String str_themes = getArguments().getString("str_themes");
        if( str_themes.length() > 0 )
            this.list = post.find(str_themes);
        else
            this.list = post.getAll();

        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Post post = (Post) getListView().getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), PostActivity.class);
        intent.putExtra("post_id", post.id);
        startActivity(intent);
    }

    private class loadPosts extends RestfulClient {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {

            try {
                adapter = new PostsAdapter(getActivity(), list);
                setListAdapter(adapter);
                list = Post.fromJson(result.getJSONArray("data"));
                adapter.addAll(list);
            }catch (JSONException e){
                e.printStackTrace();
            }

            RelativeLayout loadingLayout = (RelativeLayout) view.findViewById(R.id.loading_posts);
            loadingLayout.setVisibility(View.GONE);
        }

    }
}
