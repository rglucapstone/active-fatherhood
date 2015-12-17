package com.rglucapstone.activefatherhood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

/**
 * Created by ronald on 15/12/15.
 */
public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        //EditText t = (EditText) findViewById(R.id.input_question);
        //InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.showSoftInput(t, InputMethodManager.SHOW_IMPLICIT);
        //t.requestFocus();
    }

    public void answering(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }


}
