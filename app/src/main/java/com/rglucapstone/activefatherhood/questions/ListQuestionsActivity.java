package com.rglucapstone.activefatherhood.questions;

import android.app.ListActivity;
import android.widget.ListView;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import 	java.util.ArrayList;

import com.rglucapstone.activefatherhood.QuestionActivity;
import com.rglucapstone.activefatherhood.QuestionItemAdapter;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.apis.Question;

import android.widget.TextView;

/**
 * Created by ronald on 14/12/15.
 */
public class ListQuestionsActivity extends ListActivity {
    private ArrayAdapter listQuestionsAdapter;
    //private ListView listQuestions;
    private ArrayList<Question> arrayOfQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        ArrayList<Question> arrayOfQuestions = new ArrayList<Question>();
        this.populateQuestions(arrayOfQuestions);
        listQuestionsAdapter = new QuestionItemAdapter(this, arrayOfQuestions);
        //listQuestionsAdapter.addAll(arrayOfQuestions);
        setListAdapter(listQuestionsAdapter);

        //listQuestions = (ListView) findViewById(R.id.listQuestions);
        //listQuestions.setAdapter(listQuestionsAdapter);
        //listQuestions = (ListView) findViewById(R.id.list);

        /*stringArray = new String[20];
        for(int i=0; i < stringArray.length; i++){
            stringArray[i] = "String " + i;
        }
        listQuestionsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);*/
        //listQuestions = (ListView) findViewById(R.id.listQuestions);
        //listQuestions.setAdapter(listQuestionsAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, QuestionActivity.class);
        //i.putExtra("id", user.getUserAccountId()+"");
        //Question question = this.arrayOfQuestions.get(position);
        //Question question = getItem(position);
        intent.putExtra("user", "hola mundo1");
        intent.putExtra("date", "hola mundo2");
        intent.putExtra("content", "hola mundo3");
        intent.putExtra("tagEnfermedades", "hola mundo4");
        intent.putExtra("tagBebes", "hola mundo5");
        startActivity(intent);

        //TextView t = (TextView) v.findViewById(R.id.questionContent);
        //t.setText(question.user);
    }

    private void populateQuestions(ArrayList questions){
        Question q1 = new Question("Pedro Xavier", "hace 45 minutos", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q1);
        Question q2 = new Question("Telmo Riofrio", "hace 1 hora", "¿Es mi bebé corto para su edad?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q2);
        Question q3 = new Question("Lorenzo Perez", "hace 1 hora", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q3);
        Question q4 = new Question("Rafael Nevarez", "hace 2 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q4);
        Question q5 = new Question("Daniel Gavilanes", "hace 3 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q5);
        Question q6 = new Question("David Lolin", "hace 4 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh ..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q6);
        Question q7 = new Question("Juan Constantine", "hace 5 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q7);
        Question q8 = new Question("Ronald Gonzales", "hace 6 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q8);
        Question q9 = new Question("Joe Sarzosa", "hace 7 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q9);
        Question q10 = new Question("Mauricio Reina", "hace 8 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh ..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q10);
        Question q11 = new Question("Rafael Tamayo", "hace 9 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q11);
        Question q12 = new Question("Erwin Bravo", "hace 10 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q12);
        Question q13 = new Question("Xavier Quimi", "hace 11 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q13);
        Question q14 = new Question("Carlos Choez", "hace 12 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum..","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q14);
        Question q15 = new Question("Jorge Ortega", "hace 13 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q15);
    }


    /**/

}
