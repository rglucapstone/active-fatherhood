package com.rglucapstone.activefatherhood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

import com.rglucapstone.activefatherhood.AnswerItemAdapter;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.questions.ListQuestionsActivity;

/**
 * Created by ronald on 15/12/15.
 */
public class QuestionActivity extends AppCompatActivity {
    private ArrayAdapter listAnswerAdapter;
    private ListView listAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();

        TextView questionUser = (TextView) findViewById(R.id.questionUser);
        questionUser.setText(extras.getString("user"));
        TextView questionDate = (TextView) findViewById(R.id.questionDate);
        questionDate.setText(extras.getString("date"));
        TextView questionContent = (TextView) findViewById(R.id.questionContent);
        questionContent.setText(extras.getString("content"));

        listAnswerAdapter = new AnswerItemAdapter(this, new String[10]);
        listAnswer = (ListView) findViewById(R.id.listAnswer);
        listAnswer.setAdapter(listAnswerAdapter);
    }

    public void answer(View view) {
        Intent intent = new Intent(this, AnswerActivity.class);
        startActivity(intent);
    }

}
