package com.rglucapstone.activefatherhood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.User;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ronald on 09/01/16.
 */
public class PreferencesActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        setToolbar();
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Preferencias");
        setSupportActionBar(toolbar);
    }

    public String verifyPreferences(){
        String prefers = "";
        HashMap<String, String> preferences = getPreferences();
        Iterator prefersIterator = preferences.keySet().iterator();
        while(prefersIterator.hasNext()) {
            String key = (String) prefersIterator.next();
            String value = preferences.get(key);
            int prefer_id = getResources().getIdentifier("check_" + key, "id", getPackageName());
            boolean isChecked = ((CheckBox) findViewById(prefer_id)).isChecked();
            if( isChecked ){
                prefers += value + ",";
            }
        }
        if (prefers.length() > 0 )
            prefers = prefers.substring(0,prefers.length()-1);

        return prefers;
    }

    public static HashMap<String, String> getPreferences(){
        HashMap<String, String> prefers = new HashMap<>();
        prefers.put("babys","1");
        prefers.put("children","2");
        prefers.put("teens", "3");
        prefers.put("young", "12");

        prefers.put("solteros", "13");
        prefers.put("primerizos", "14");
        prefers.put("futuros", "15");
        prefers.put("divorciados", "16");

        prefers.put("aprendizaje", "7");
        prefers.put("comportamiento", "8");
        prefers.put("comunicacion", "11");
        prefers.put("problemas_sociales", "9");
        prefers.put("salud", "4");
        prefers.put("alimentacion", "17");
        prefers.put("divorcio", "18");
        prefers.put("adopcion", "19");
        prefers.put("enfermedades", "10");
        prefers.put("discapacidades", "20");
        prefers.put("recreacion", "21");
        prefers.put("sexualidad", "22");
        return prefers;
    }

    public void goHome(View view){
        String prefers = verifyPreferences();

        Intent intentIn = getIntent();
        String user_id = intentIn.getStringExtra("user_id");

        //extView txt_test = (TextView)findViewById(R.id.title_preference);
        //txt_test.setText(user_id + " : " + prefers);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user_id", user_id);
        intent.putExtra("prefers", prefers);
        intent.putExtra("view_by", "preferences");
        startActivity(intent);
    }
}
