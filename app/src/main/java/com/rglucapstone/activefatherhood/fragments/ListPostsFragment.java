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
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.data.Post;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 09/01/16.
 */
public class ListPostsFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_posts, container, false);
        Post post = new Post(new load());
        post.find(getArguments().getString("prefers"), getArguments().getString("view_by"));
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        User user = new User(getArguments().getString("logged_id"));
        Post post = (Post) getListView().getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), PostActivity.class);
        intent.putExtra("post_id", post.id);
        intent.putExtra("logged_id", user.id);
        startActivity(intent);
    }

    // task to load posts
    private class load extends RestfulClient {

        @Override
        protected void onPostExecute(JSONObject result) {
            User user = new User(getArguments().getString("logged_id"));
            ArrayList<Post> list = new ArrayList<>();
            PostsAdapter adapter = new PostsAdapter(getActivity(), list, user);
            setListAdapter(adapter);
            try {
                if( this.status == 200 ){
                    list = Post.fromJson(result.getJSONArray("data"));
                    adapter.addAll(list);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            RelativeLayout loadingLayout = (RelativeLayout) getView().findViewById(R.id.loading_posts);
            loadingLayout.setVisibility(View.GONE);
        }

    }
}
