package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

/**
 * Created by Luisa Castro on 27/01/2016.
 */
public class DoPost extends AppCompatActivity {
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
        this.user = new User(intent.getStringExtra("user_id"));
        String content = intent.getStringExtra("content");
        final String source = intent.getStringExtra("source");
        final String user_request = intent.getStringExtra("user_request_id");
        final String answer_request = intent.getStringExtra("user_answer_id");


        TextView input_descripction_post = (TextView) findViewById(R.id.input_descripction_post);
        input_descripction_post.setText(content);

        btn_doPost = (Button) findViewById(R.id.btn_doPost);
        View view;
        btn_doPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText title = (EditText) findViewById(R.id.input_title_post);
                EditText description = (EditText) findViewById(R.id.input_descripction_post);

                // instance Question object
                Post post = new Post(context, new sendPost());
                post.title = title.getText().toString();
                post.content = description.getText().toString();
                post.user = new User(user.id);
                if( source.equals("notification") ){
                    post.user_request = user_request;
                    post.answer_request = answer_request;
                }
                post.send();
            }
        });

        /**
         * Se oculta el titulo de preferencias

        container = (TextView) findViewById(R.id.title_preference);
        container.setVisibility(View.GONE);*/
    }

    /* Toolbar */
    public void setToolbar(){
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Agrega una publicación");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /* Action Back*/
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

    private class sendPost extends RestfulClient {

        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(context, "Enviando publicación", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            Intent intent = new Intent(context, HomeActivity.class);
            intent.putExtra("user_id", user.id);
            intent.putExtra("str_themes", "");
            intent.putExtra("viewBy", "preference");
            context.startActivity(intent);
        }

    }
}
