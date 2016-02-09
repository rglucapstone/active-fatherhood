package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Post;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Luisa Castro on 27/01/2016.
 */
public class DoPostActivity extends AppCompatActivity {
    private Button button_asking;
    final Context context = this;
    private TextView container;

    public User user;
    private Button btn_doPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_post);
        setToolbar();

        Intent intent = getIntent();
        this.user = new User(intent.getStringExtra("user_id")); // logged user

        final String source = intent.getStringExtra("source");
        final String user_request_id = intent.getStringExtra("user_request_id");
        //final String answer_request = intent.getStringExtra("user_answer_id");

        // content of post
        String content = intent.getStringExtra("content");
        if( content.length() > 0 ){
            TextView input_descripction_post = (TextView) findViewById(R.id.input_descripction_post);
            input_descripction_post.setText(content);
        }


        btn_doPost = (Button) findViewById(R.id.btn_doPost);
        btn_doPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Post post = new Post(context, new send());
                EditText title = (EditText) findViewById(R.id.input_title_post);
                post.title = title.getText().toString();
                EditText description = (EditText) findViewById(R.id.input_descripction_post);
                post.content = description.getText().toString();
                post.user = user;
                if( source.equals("notification") ){
                    post.user_request_id = user_request_id;
                }
                String prefers = checkPreferences();
                post.send(prefers);
            }
        });

        /**
         * Se oculta el titulo de preferencias

        container = (TextView) findViewById(R.id.title_preference);
        container.setVisibility(View.GONE);*/
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Agrega una publicación");
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


    private class send extends RestfulClient {

        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(context, "Enviando publicación", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            if( this.status == 201 ){
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("user_id", user.id);
                intent.putExtra("prefers", "");
                intent.putExtra("view_by", "preferences");
                context.startActivity(intent);
            }else{
                toast = Toast.makeText(context, "Error al enviar la publicación", Toast.LENGTH_SHORT);
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
