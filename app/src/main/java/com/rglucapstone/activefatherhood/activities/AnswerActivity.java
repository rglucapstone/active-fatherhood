package com.rglucapstone.activefatherhood.activities;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Answer;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ronald on 15/12/15.
 */
public class AnswerActivity extends AppCompatActivity {
    public Answer answer;
    private LinearLayout button;
    ArrayList selectedCategories;
    final Context context = this;
    private Button button_answering;
    public User logged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();
        this.answer = new Answer();
        this.answer.question_id = intent.getStringExtra("question_id");

        this.logged = new User(intent.getStringExtra("logged_id"));
        //EditText txt = (EditText) findViewById(R.id.input_answer);
        //txt.setText(intent.getStringExtra("question_id"));


        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Agregar respuesta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Luego de realizar la pregunta la muestra en el listado
        button_answering = (Button) findViewById(R.id.btn_answering);
        button_answering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText textasnswer = (EditText) findViewById(R.id.input_answer);

                Answer ans = new Answer(context, new sendAnswer());
                ans.content = textasnswer.getText().toString();
                ans.created = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                ans.user = logged;
                ans.question_id = answer.question_id;
                ans.send();
            }
        });

        /*button = (LinearLayout) findViewById(R.id.btn_suggest_publication);
        View view;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                selectedCategories = new ArrayList();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage(R.string.message_suggest_publication)
                .setPositiveButton(R.string.btn_aceptar_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AnswerActivity.this.finish();
                        /*Intent intent = new Intent(context, HomeActivity.class);
                        startActivityForResult(intent, 0);
                    }
                })
                .setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });*/
    }

    public void answering(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        //intent.putExtra("question_id", this.answersquestion.id);
        startActivity(intent);
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

    /**
     * Dialog tu ask suggest publication
     */
    public void seguroSugerirPublication(){

    }

    private class sendAnswer extends RestfulClient {

        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(context, "Enviando respuesta", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            Intent intent = new Intent(context, QuestionActivity.class);
            intent.putExtra("question_id", answer.question_id);
            context.startActivity(intent);
        }

    }

}
