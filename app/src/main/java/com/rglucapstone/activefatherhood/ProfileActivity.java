package com.rglucapstone.activefatherhood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rglucapstone.activefatherhood.apis.Question;

import java.util.ArrayList;

/**
 * Created by Luisa Castro on 17/12/2015.
 */
public class ProfileActivity extends Activity {
    private ArrayAdapter listQuestionsAdapter;
    private ListView listQuestions;
    private ArrayList<Question> arrayOfQuestions;
    private Context list;

    private ArrayAdapter listInfoGuruAdapter;
    private ListView listPadresGurus;
    private ArrayList<Question> arrayOfInfoGuru;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(CategoriesActivity.EXTRA_MESSAGE);
        setTitle(message);
        setContentView(R.layout.activity_profile);

        ArrayList<Question> arrayOfQuestions = new ArrayList<Question>();
        this.populateQuestions(arrayOfQuestions);
        listQuestionsAdapter = new QuestionItemAdapter(this, arrayOfQuestions);
        listQuestions = (ListView) findViewById(R.id.listQuestions);
        listQuestions.setAdapter(listQuestionsAdapter);
        //setListAdapter(listQuestionsAdapter);

        list = this;
        listQuestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                Intent intent = new Intent(list, QuestionActivity.class);
                //i.putExtra("id", user.getUserAccountId()+"");
                //Question question = getItem(position);
                intent.putExtra("user", "Pedro Pablo");
                intent.putExtra("date", "hace 5 horas");
                intent.putExtra("content", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
                startActivity(intent);

                //TextView t = (TextView) findViewById(R.id.questionContent);
                //t.setText("hola mundo");

                //Toast.makeText(getApplicationContext(), "TV Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateQuestions(ArrayList questions){
        Question q1 = new Question("Pedro Xavier", "hace 45 minutos", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que puedan tener?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q1);
        Question q2 = new Question("Telmo Riofrio", "hace 1 hora", "¿Es mi bebé corto para su edad?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q2);
        Question q3 = new Question("Lorenzo Perez", "hace 1 hora", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q3);
        Question q4 = new Question("Rafael Nevarez", "hace 2 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q4);
        Question q5 = new Question("Daniel Gavilanes", "hace 3 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q5);
        Question q6 = new Question("David Lolin", "hace 4 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q6);
        Question q7 = new Question("Juan Constantine", "hace 5 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q7);
        Question q8 = new Question("Ronald Gonzales", "hace 6 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q8);
        Question q9 = new Question("Joe Sarzosa", "hace 7 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q9);
        Question q10 = new Question("Mauricio Reina", "hace 8 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q10);
        Question q11 = new Question("Rafael Tamayo", "hace 9 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q11);
        Question q12 = new Question("Erwin Bravo", "hace 10 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q12);
        Question q13 = new Question("Xavier Quimi", "hace 11 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q13);
        Question q14 = new Question("Carlos Choez", "hace 12 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q14);
        Question q15 = new Question("Jorge Ortega", "hace 13 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?","Bebes","10 Me Gusta","15 Respuestas");
        questions.add(q15);
    }

}
