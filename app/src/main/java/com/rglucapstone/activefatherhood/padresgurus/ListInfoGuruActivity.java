package com.rglucapstone.activefatherhood.padresgurus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rglucapstone.activefatherhood.GuruItemAdapter;
import com.rglucapstone.activefatherhood.QuestionActivity;
import com.rglucapstone.activefatherhood.QuestionItemAdapter;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.apis.PadreGuru;

import java.util.ArrayList;

/**
 * Created by ronald on 07/01/16.
 */
public class ListInfoGuruActivity extends ListActivity {
    private ArrayAdapter listInfoGuruAdapter;
    //private ListView listQuestions;
    private ArrayList<PadreGuru> arrayOfInfoGuru;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_infoguru);

        ArrayList<PadreGuru> arrayOfInfoGuru = new ArrayList<PadreGuru>();
        this.populateInfoGuru(arrayOfInfoGuru);
        listInfoGuruAdapter = new GuruItemAdapter(this, arrayOfInfoGuru);
        //listQuestionsAdapter.addAll(arrayOfQuestions);
        setListAdapter(listInfoGuruAdapter);

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
        startActivity(intent);

        //TextView t = (TextView) v.findViewById(R.id.questionContent);
        //t.setText(question.user);
    }

    private void populateInfoGuru(ArrayList arrayOfInfoGuru){
        PadreGuru q1 = new PadreGuru("Pedro Xavier", "hace 45 minutos", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que puedan tener?");
        arrayOfInfoGuru.add(q1);
        PadreGuru q2 = new PadreGuru("Telmo Riofrio", "hace 1 hora", "¿Es mi bebé corto para su edad?");
        arrayOfInfoGuru.add(q2);
        /*Question q3 = new Question("Lorenzo Perez", "hace 1 hora", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q3);
        Question q4 = new Question("Rafael Nevarez", "hace 2 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q4);
        Question q5 = new Question("Daniel Gavilanes", "hace 3 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q5);
        Question q6 = new Question("David Lolin", "hace 4 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q6);
        Question q7 = new Question("Juan Constantine", "hace 5 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q7);
        Question q8 = new Question("Ronald Gonzales", "hace 6 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q8);
        Question q9 = new Question("Joe Sarzosa", "hace 7 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q9);
        Question q10 = new Question("Mauricio Reina", "hace 8 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q10);
        Question q11 = new Question("Rafael Tamayo", "hace 9 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q11);
        Question q12 = new Question("Erwin Bravo", "hace 10 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q12);
        Question q13 = new Question("Xavier Quimi", "hace 11 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q13);
        Question q14 = new Question("Carlos Choez", "hace 12 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
        questions.add(q14);
        Question q15 = new Question("Jorge Ortega", "hace 13 horas", "Lorem ipsum dolor sit amet, consectetur adipiscing elit?");
        questions.add(q15);*/
    }


    /**/

}