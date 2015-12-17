package com.rglucapstone.activefatherhood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by ronald on 14/12/15.
 */
public class CategoriesActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.rglucapstone.activefatherhood.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void selectTopic(View view) {
        Intent intent = new Intent(this, ListQuestionsActivity.class);
        ImageButton b = (ImageButton)view;
        String buttonText = b.getContentDescription().toString();
        intent.putExtra(EXTRA_MESSAGE, buttonText);
        startActivity(intent);
    }


}
