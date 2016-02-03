package com.rglucapstone.activefatherhood.fragments;

//import android.app.ListFragment;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.QuestionActivity;
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.adapters.ResultAdapter;
import com.rglucapstone.activefatherhood.data.Entity;
import com.rglucapstone.activefatherhood.data.Guru;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ronald on 02/02/16.
 */
public class SearchResultsFragment extends ListFragment {

    public HashMap<String, ArrayList> hashResults;
    public View view;
    public ResultAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        this.view = view;

        //String query = "salud";
        String query = getArguments().getString("query");
        if( query.length() == 0 )
            query = "xxx";

        Entity entity = new Entity(new searching());
        this.hashResults = entity.search(query);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Question question = (Question) getListView().getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        intent.putExtra("question_id", question.id);
        startActivity(intent);
    }

    private class searching extends RestfulClient {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            ArrayList<Question> listQuestions = new ArrayList<>();
            try {
                JSONObject d= result.getJSONObject("data");

                if( d.has("questions") ){
                    listQuestions = Question.fromJson(d.getJSONArray("questions"));
                }

                //ListView listResults = (ListView) view.findViewById(R.id.listResults);
                //adapter = new ResultAdapter(getBaseContext(), listQuestions);
                //listResults.setAdapter(adapter);
                adapter = new ResultAdapter(getActivity(), listQuestions);
                setListAdapter(adapter);


            }catch (JSONException e){
                e.printStackTrace();
            }
            RelativeLayout loadingLayout = (RelativeLayout) view.findViewById(R.id.loading_results);
            loadingLayout.setVisibility(View.GONE);
        }

    }


}
