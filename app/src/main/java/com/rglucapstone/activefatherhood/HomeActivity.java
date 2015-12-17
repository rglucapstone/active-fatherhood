package com.rglucapstone.activefatherhood;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ListView;
import 	android.widget.AdapterView;
import 	android.content.Context;

import com.rglucapstone.activefatherhood.apis.Question;
import com.rglucapstone.activefatherhood.QuestionActivity;

import java.util.ArrayList;

/**
 * Created by Luisa Castro on 16/12/2015.
 */
public class HomeActivity extends AppCompatActivity {
    private ArrayAdapter listQuestionsAdapter;
    private ListView listQuestions;
    private ArrayList<Question> arrayOfQuestions;
    private Context list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

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


        final RelativeLayout content_question = (RelativeLayout) findViewById(R.id.content_question);
        final RelativeLayout content_categories = (RelativeLayout) findViewById(R.id.content_categories);
        final RelativeLayout content_tips = (RelativeLayout) findViewById(R.id.content_tips);

        final Button btn_menu_general= (Button) findViewById(R.id.menu_general);
        final Button menu_categories= (Button) findViewById(R.id.menu_categories);
        final Button menu_tips= (Button) findViewById(R.id.menu_tips);

        content_categories.setVisibility(View.INVISIBLE);
        content_tips.setVisibility(View.INVISIBLE);
        btn_menu_general.setBackgroundColor(Color.WHITE);

        btn_menu_general.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn_menu_general.setBackgroundColor(Color.WHITE);
                menu_categories.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_tips.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_categories.setVisibility(View.INVISIBLE);
                content_tips.setVisibility(View.INVISIBLE);
                content_question.setVisibility(View.VISIBLE);
               /* content_question.setVisibility((content_question.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);*/
            }
        });

        menu_categories.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu_categories.setBackgroundColor(Color.WHITE);
                btn_menu_general.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_tips.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_question.setVisibility(View.INVISIBLE);
                content_tips.setVisibility(View.INVISIBLE);
                content_categories.setVisibility(View.VISIBLE);
                /*content_categories.setVisibility((content_categories.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);*/
            }
        });

        menu_tips.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu_tips.setBackgroundColor(Color.WHITE);
                btn_menu_general.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_categories.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_question.setVisibility(View.INVISIBLE);
                content_categories.setVisibility(View.INVISIBLE);
                content_tips.setVisibility(View.VISIBLE);
               /* content_tips.setVisibility((content_categories.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);*/
            }
        });
    }
    public void profileOn(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    private void populateQuestions(ArrayList questions){
        Question q1 = new Question("Pedro Xavier", "hace 45 minutos", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que puedan tener?");
        questions.add(q1);
        Question q2 = new Question("Telmo Riofrio", "hace 1 hora", "¿Es mi bebé corto para su edad?");
        questions.add(q2);
        Question q3 = new Question("Lorenzo Perez", "hace 1 hora", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nibh metus, pulvinar non nulla vel?");
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
        questions.add(q15);
    }

    public void ask(View view) {
        Intent intent = new Intent(this, AskActivity.class);
        startActivity(intent);
    }

    /*@Override
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
    }*/
}
