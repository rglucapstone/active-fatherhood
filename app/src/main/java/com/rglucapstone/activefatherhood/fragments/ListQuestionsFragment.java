package com.rglucapstone.activefatherhood.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.EditProfileActivity;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.activities.QuestionActivity;
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ronald on 09/01/16.
 */
public class ListQuestionsFragment extends ListFragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_questions, container, false);

        // load questions
        Question question = new Question(new load());
        question.find("normal", getArguments().getString("prefers"), getArguments().getString("view_by"));
        return view;
    }

    // task to load questions
    private class load extends RestfulClient{

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            User user = new User(getArguments().getString("logged_id"));
            ArrayList<Question> list = new ArrayList<>();
            QuestionsAdapter adapter = new QuestionsAdapter(getActivity(), list, user);
            setListAdapter(adapter);
            try {
                if( this.status == 200 ){
                    list = Question.fromJson(result.getJSONArray("data"));
                    adapter.addAll(list);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            RelativeLayout loadingLayout = (RelativeLayout) getView().findViewById(R.id.loading_questions);
            loadingLayout.setVisibility(View.GONE);
        }

    }

    // click over each question
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        User user = new User(getArguments().getString("logged_id"));
        Question question = (Question) getListView().getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        intent.putExtra("question_id", question.id);
        intent.putExtra("logged_id", user.id);
        startActivity(intent);
    }

}
