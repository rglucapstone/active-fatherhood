package com.rglucapstone.activefatherhood.fragments;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.activities.QuestionActivity;
import com.rglucapstone.activefatherhood.adapters.ListQuestionsAdapter;
import com.rglucapstone.activefatherhood.data.Question;

import java.util.ArrayList;

/**
 * Created by ronald on 09/01/16.
 */
public class ListQuestionsFragment extends ListFragment{

    public ListQuestionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_questions, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Question> listQuestions = new ArrayList<>();
        this.populate(listQuestions);
        ListQuestionsAdapter questionsAdapter = new ListQuestionsAdapter(getActivity(), listQuestions);
        setListAdapter(questionsAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        intent.putExtra("user", "Pedro Pablo");
        intent.putExtra("date", "hace 5 horas");
        intent.putExtra("content", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        startActivity(intent);
    }

    private void populate(ArrayList questions){
        Question q1 = new Question("Pedro Xavier", "hace 45 minutos", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q1);
        Question q2 = new Question("Telmo Riofrio", "hace 1 hora", "¿Es mi bebé corto para su edad?","Bebes","10 Me Gusta","7 Respuestas");
        questions.add(q2);
        Question q3 = new Question("Lorenzo Perez", "hace 1 hora", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh..","Bebes","1 Me Gusta","15 Respuestas");
        questions.add(q3);
        Question q4 = new Question("Rafael Nevarez", "hace 2 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ..","Bebes","10 Me Gusta","12 Respuestas");
        questions.add(q4);
        Question q5 = new Question("Daniel Gavilanes", "hace 3 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","8 Me Gusta","15 Respuestas");
        questions.add(q5);
        Question q6 = new Question("David Lolin", "hace 4 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh ..","Bebes","9 Me Gusta","4 Respuestas");
        questions.add(q6);
        Question q7 = new Question("Juan Constantine", "hace 5 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q7);
        Question q8 = new Question("Ronald Gonzales", "hace 6 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ..","Bebes","10 Me Gusta","10 Respuestas");
        questions.add(q8);
        Question q9 = new Question("Joe Sarzosa", "hace 7 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","5 Me Gusta","15 Respuestas");
        questions.add(q9);
        Question q10 = new Question("Mauricio Reina", "hace 8 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh ..","Bebes","10 Me Gusta","9 Respuestas");
        questions.add(q10);
        Question q11 = new Question("Daniel Gavilanes", "hace 3 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","8 Me Gusta","15 Respuestas");
        questions.add(q11);
        Question q12 = new Question("David Lolin", "hace 4 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh ..","Bebes","9 Me Gusta","4 Respuestas");
        questions.add(q12);
        Question q13 = new Question("Juan Constantine", "hace 5 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q13);
        Question q14 = new Question("Ronald Gonzales", "hace 6 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ..","Bebes","10 Me Gusta","10 Respuestas");
        questions.add(q14);
        Question q15 = new Question("Joe Sarzosa", "hace 7 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","5 Me Gusta","15 Respuestas");
        questions.add(q15);
    }

}
