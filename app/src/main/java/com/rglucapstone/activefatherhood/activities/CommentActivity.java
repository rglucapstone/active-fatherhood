package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Comment;
import com.rglucapstone.activefatherhood.data.Post;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luisa Castro on 27/01/2016.
 */
public class CommentActivity extends AppCompatActivity {

    private User logged;
    private Post post;

    private LinearLayout button;
    ArrayList selectedCategories;
    final Context context = this;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // show keyboard by default
        EditText input = (EditText) findViewById(R.id.input_comment);
        if(input.requestFocus()){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(input, 0);
        }

        setToolbar();
        logged = new User(getIntent().getStringExtra("logged_id"));
        post = new Post(getIntent().getStringExtra("post_id"));
        setActions(logged, post);

    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Agregar comentario");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setActions(final User u, final Post p){

        Button button_comment = (Button) findViewById(R.id.btn_comment);
        button_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Comment c = new Comment(new send());
                EditText content = (EditText) findViewById(R.id.input_comment);
                c.content = content.getText().toString();
                c.created = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                c.user = u;
                c.post = p;
                c.send();
            }
        });

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
            toast = Toast.makeText(context, "Enviando comentario", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            if( this.status == 201 ){
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("logged_id", logged.id);
                intent.putExtra("post_id", post.id);
                context.startActivity(intent);
            }else{
                toast = Toast.makeText(context, "Error al enviar comentario", Toast.LENGTH_SHORT);
                toast.show();
            }

        }

    }

}
