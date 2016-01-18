package com.rglucapstone.activefatherhood.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Prueba;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView texto = (TextView) findViewById(R.id.txt_main);
        String names = "";
        /*ArrayList<Question> list = Question.getAll();
        for(int i = 0; i < list.size() ; i++){
            names = names + "OBJECT" + i + ":" + list.get(i).content + " / " + list.get(i).created + "\n";
        }
        texto.setText(names);*/



        /*try {

            RestfulClient rest = new RestfulClient(this, "GET", "/pruebas");
            JSONArray data = rest.execute().get();
            try {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject pobject = data.getJSONObject(i);
                    names = names + "OBJ" + i + ":" + pobject.getString("nombre") + " / " + pobject.getString("abreviatura") + "\n";
                }
                texto.setText(names);
            } catch (JSONException e) {
                // manage exceptions}

            }

        } catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }*/
    }
}


