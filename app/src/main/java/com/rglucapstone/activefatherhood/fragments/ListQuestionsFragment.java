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

import java.util.ArrayList;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.EditProfileActivity;
import com.rglucapstone.activefatherhood.activities.ProfileActivity;
import com.rglucapstone.activefatherhood.activities.QuestionActivity;
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ronald on 09/01/16.
 */
public class ListQuestionsFragment extends ListFragment
{
    public Context context;
    public View view;
    public ArrayList<Question> list;
    public QuestionsAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list_questions, container, false);
        this.view = view;

        String str_themes = getArguments().getString("str_themes");
        Question question = new Question(new loadQuestions());
        if( str_themes.length() > 0 )
            this.list = question.find(str_themes);
        else
            this.list = question.getAll();

        //TextView test = (TextView) view.findViewById(R.id.test);
        //test.setText(question.content);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private class loadQuestions extends RestfulClient{

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {

                // Create the adapter to convert the array to views
                adapter = new QuestionsAdapter(getActivity(), list);
                setListAdapter(adapter); // Attach the adapter to a ListView
                // Populating Data into ListView
                list = Question.fromJson(result.getJSONArray("data"));
                adapter.addAll(list);

            }catch (JSONException e){
                e.printStackTrace();
            }

            RelativeLayout loadingLayout = (RelativeLayout) view.findViewById(R.id.loading_questions);
            loadingLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Question question = (Question) getListView().getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        intent.putExtra("question_id", question.id);
        startActivity(intent);
    }

}
