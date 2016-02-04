package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Luisa Castro on 17/01/2016.
 */
public class AskGuruActivity extends AppCompatActivity {
    private RelativeLayout container;
    private Button button_asking;
    final Context context = this;

    User user;
    User user_guru;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        Intent intent = getIntent();
        this.user_guru = new User(intent.getStringExtra("user_id"));
        this.user = new User(intent.getStringExtra("user_creator_id"));

        setToolbar();

        /* Se oculta el contenedor de seleccionar tags, cuando es una pregunta directa */
        //container = (RelativeLayout) findViewById(R.id.container_tags);
        //container.setVisibility(View.GONE);

        /**
         * Sobreescribo la accion, cuando realizo la pregunta se actualiza
         * la actividad en el perfil, como primer item
         */
        button_asking = (Button) findViewById(R.id.btn_asking);
        View view;
        button_asking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText iquestion = (EditText)findViewById(R.id.input_question);

                // instance Question object
                Question question = new Question(context, new sendQuestion());
                question.content = iquestion.getText().toString();
                question.created = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
                question.user = user;
                question.user_guru = user_guru;
                //TextView txt_test = (TextView) findViewById(R.id.txt_test);
                //txt_test.setText(question.user_guru.id + " : " + question.user.id);
                question.send();

               // SignUpActivity.this.finish();
                //Intent intent = new Intent(context, ProfileActivity.class);
                //startActivityForResult(intent, 0);
            }
        });
    }

    /* Toolbar */
    public void setToolbar(){
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Realizar Pregunta");
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

    private class sendQuestion extends RestfulClient {

        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(context, "Enviando pregunta", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast = Toast.makeText(context, "Pregunta directa enviada con éxito.", Toast.LENGTH_SHORT);
            toast.show();
            //toast.cancel();
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("user_id", user_guru.id);
            intent.putExtra("logged_id", user.id);
            context.startActivity(intent);
            //context.startActivity(new Intent(context, ProfileActivity.class));
        }

    }
}