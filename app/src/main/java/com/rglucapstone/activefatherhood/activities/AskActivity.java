package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ronald on 15/12/15.
 */
public class AskActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        this.context = this;
        setToolbar();
        User user = new User(getIntent().getStringExtra("user_id"));
        User user_guru = new User(getIntent().getStringExtra("user_guru_id"));
        setActions(user, user_guru);
    }

    private void setActions(final User u, final User ug){

        Button button_asking = (Button) findViewById(R.id.btn_asking);
        button_asking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Question question = new Question(context, new sendQuestion());
                EditText iquestion = (EditText) findViewById(R.id.input_question);
                question.content = iquestion.getText().toString();
                question.created = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                question.user = u;
                question.user_guru = ug;
                String prefers = checkPreferences();
                question.send(prefers);
            }
        });

    }

    private void setToolbar(){
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Agregar pregunta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // task to send a question
    private class sendQuestion extends RestfulClient {

        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(context, "Enviando pregunta", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            if( this.status == 201 ){
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("user_id", getIntent().getStringExtra("user_id"));
                intent.putExtra("prefers", "");
                intent.putExtra("view_by", "preferences");
                context.startActivity(intent);
            }else{
                toast = Toast.makeText(context, "Error al enviar pregunta", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    private final String checkPreferences(){
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

    private static HashMap<String, String> getPreferences(){
        HashMap<String, String> prefers = new HashMap<>();
        prefers.put("babys","1");
        prefers.put("children","2");
        prefers.put("teens", "3");
        prefers.put("aprendizaje", "7");
        prefers.put("comportamiento", "8");
        prefers.put("comunicacion", "11");
        prefers.put("problemas_sociales", "9");
        prefers.put("salud", "4");
        prefers.put("alimentacion", "17");
        prefers.put("divorcio", "18");
        prefers.put("enfermedades", "10");
        prefers.put("discapacidades", "20");
        prefers.put("recreacion", "21");
        prefers.put("sexualidad", "22");
        return prefers;
    }
}
