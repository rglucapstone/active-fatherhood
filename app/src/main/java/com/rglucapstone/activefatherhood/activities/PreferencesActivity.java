package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rglucapstone.activefatherhood.R;

/**
 * Created by ronald on 09/01/16.
 */
public class PreferencesActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_preference);
        setToolbar();
    }

    /* Toolbar Preference */
    public void setToolbar(){
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Preferencias");
        setSupportActionBar(toolbar);
    }
    /* Button Continue: go to Home */
    public void goHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
