package com.rglucapstone.activefatherhood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ronald on 15/12/15.
 */
public class AskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Intent intent = getIntent();
        String message = intent.getStringExtra(CategoriesActivity.EXTRA_MESSAGE);
        setTitle(message);*/
        setContentView(R.layout.activity_ask);
    }

    public void asking(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
