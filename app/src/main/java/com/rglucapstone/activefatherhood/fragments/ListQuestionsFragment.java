package com.rglucapstone.activefatherhood.fragments;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

/**
 * Created by ronald on 09/01/16.
 */
public class ListQuestionsFragment extends ListFragment{

    @Override
    public void onStart(){

        super.onStart();
        //ImageView icUser = (ImageView) getActivity().findViewById(R.id.ic_user);
        /*
        icUser.setOnClickListener(new OnClickListener(){

            public void onClick(View view){
                EditText et= (EditText)context.findViewById(R.id.txt_edit);
                Intent intent=new Intent(getActivity(), ProfileActivity.class); //create an Intent object
                intent.putExtra("text", et.getText().toString()); //add data to the Intent object
                startActivity(intent); //start the second activity
            }

        });*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_questions, container, false);
        ArrayList<Question> questions = new ArrayList<>(); // Construct the data source
        QuestionsAdapter adapter = new QuestionsAdapter(getActivity(), questions); // Create the adapter to convert the array to views
        setListAdapter(adapter); // Attach the adapter to a ListView
        adapter.addAll(Question.getAll()); // Populating Data into ListView
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setViewProfile(){

    }

    /*@Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        intent.putExtra("user", "Pedro Pablo");
        intent.putExtra("date", "hace 5 horas");
        intent.putExtra("content", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        startActivity(intent);
    }*/

}
