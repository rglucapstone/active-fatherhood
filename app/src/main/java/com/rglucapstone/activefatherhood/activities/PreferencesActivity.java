package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.User;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ronald on 09/01/16.
 */
public class PreferencesActivity extends AppCompatActivity {

    public HashMap<String, String> preferences;
    public String str_prefers;
    public User user;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_preference);

        Intent intent = getIntent();
        user = new User(intent.getStringExtra("user_id"));

        //TextView txt = (TextView) findViewById(R.id.title_preference);
        //txt.setText(user.id + " : " + user.email);


        setToolbar();
        setPreferences();
    }

    /* Toolbar Preference */
    public void setToolbar(){
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Preferencias");
        setSupportActionBar(toolbar);
    }


    // / Button Continue: go to Home
    public void goHome(View view){

        verifyPreferences();
        //TextView test = (TextView) findViewById(R.id.test);
        //test.setText(this.str_prefers);

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user_id", user.id);
        intent.putExtra("str_themes", str_prefers);
        intent.putExtra("viewBy", "preference");
        startActivity(intent);
    }

    public void setPreferences(){
        this.preferences = new HashMap<>();
        this.preferences.put("babys","1");
        this.preferences.put("children","2");
        this.preferences.put("teens","3");
        this.preferences.put("young", "12");

        this.preferences.put("solteros", "13");
        this.preferences.put("primerizos", "14");
        this.preferences.put("futuros", "15");
        this.preferences.put("divorciados", "16");

        this.preferences.put("aprendizaje", "7");
        this.preferences.put("comportamiento", "8");
        this.preferences.put("comunicacion", "11");
        this.preferences.put("problemas_sociales", "9");
        this.preferences.put("salud", "4");
        this.preferences.put("alimentacion", "17");
        this.preferences.put("divorcio", "18");
        this.preferences.put("adopcion", "19");
        this.preferences.put("enfermedades", "10");
        this.preferences.put("discapacidades", "20");
        this.preferences.put("recreacion", "21");
        this.preferences.put("sexualidad", "22");

    }

    public String verifyPreferences(){
        String prefers = "";
        Iterator prefersIterator = this.preferences.keySet().iterator();
        while(prefersIterator.hasNext()) {
            String key = (String) prefersIterator.next();
            String value = this.preferences.get(key);
            int prefer_id = getResources().getIdentifier("check_" + key, "id", getPackageName());
            boolean isChecked = ((CheckBox) findViewById(prefer_id)).isChecked();
            if( isChecked ){
                prefers += value + ",";
            }
        }
        if (prefers.length() > 0 )
            prefers = prefers.substring(0,prefers.length()-1);

        this.str_prefers = prefers;
        return prefers;
    }
}
