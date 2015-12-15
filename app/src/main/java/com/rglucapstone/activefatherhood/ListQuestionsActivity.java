package com.rglucapstone.activefatherhood;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

/**
 * Created by ronald on 14/12/15.
 */
public class ListQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(CategoriesActivity.EXTRA_MESSAGE);
        setTitle(message);
        setContentView(R.layout.activity_list_questions);
    }

    public void ask(View view) {
        Intent intent = new Intent(this, AskActivity.class);
        startActivity(intent);
    }

    public void seeQuestion(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

}
