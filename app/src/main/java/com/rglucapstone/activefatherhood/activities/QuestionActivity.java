package com.rglucapstone.activefatherhood.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.adapters.AnswerItemAdapter;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 15/12/15.
 */
public class QuestionActivity extends AppCompatActivity {
    public Question question;
    public Context context;
    private TextView container;
    private ImageButton btn_like;
    private ImageButton btn_suggest;
    //public AnswerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        this.context = this;

        Intent intent = getIntent();

        //TextView txt_qanswers = (TextView) findViewById(R.id.title_answers);
        //txt_qanswers.setText(obj.user.name);
        this.question = new Question(this, new loadQuestion());
        this.question.load(intent.getStringExtra("question_id"));
        setToolbar();
        setActions();

        //listAnswerAdapter = new AnswerItemAdapter(this, new String[10]);
        //listAnswer = (ListView) findViewById(R.id.listAnswer);
        //listAnswer.setAdapter(listAnswerAdapter);
    }

    public void answer(View view) {
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("question_id", this.question.id);
        startActivity(intent);
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }*/

    /* Toolbar */
    public void setToolbar(){
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Pregunta");
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

    /* Actions */

    public void setActions(){
        /* Se oculta la hora y se muestra el icono de like */
        //container = (TextView) findViewById(R.id.txt_qdatetime);
        //container.setVisibility(View.GONE);

        btn_like = (ImageButton) findViewById(R.id.btn_like_question);
        btn_like.setVisibility(View.VISIBLE);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // function to increment like to person
                if(btn_like.getBackground().equals(R.mipmap.ico_like_light_grey_24)) {
                    btn_like.setBackgroundResource(R.mipmap.ico_like_blue_24);
                }else {
                    btn_like.setBackgroundResource(R.mipmap.ico_like_light_grey_24);
                }
            }
        });

        /***** ELIMINAR LUEGO, TEMPORAL para pruebas
        btn_suggest = (ImageButton) findViewById(R.id.btn_suggest_post);
        btn_suggest.setVisibility(View.VISIBLE);

        btn_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle(R.string.seguro_sugerir_post);
                alertDialogBuilder.setMessage(R.string.mensaje_sugerir_post)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //QuestionActivity.this.finish();
                                QuestionActivity.this.closeContextMenu();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                QuestionActivity.this.closeContextMenu();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });*/
    }

    private void setData(){
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout_item_question);

        TextView txt_qcontent = (TextView) layout.findViewById(R.id.txt_qcontent);
        txt_qcontent.setText(this.question.content);

        TextView txt_qanswers = (TextView) findViewById(R.id.title_answers);
        txt_qanswers.setText(this.question.listAnswers.size() + " respuestas");

        TextView txt_quser = (TextView) layout.findViewById(R.id.txt_quser);
        txt_quser.setText(this.question.user.name);

        //TextView txt_qdatetime = (TextView) layout.findViewById(R.id.txt_qdatetime);
        //txt_qdatetime.setText(this.question.created);

        AnswerItemAdapter adapter = new AnswerItemAdapter(this, this.question.listAnswers);
        LinearLayout layout_answers = (LinearLayout) findViewById(R.id.layout_list_answers);
        ListView list = (ListView) layout_answers.findViewById(R.id.listAnswer);
        list.setAdapter(adapter);
    }

    private class loadQuestion extends RestfulClient {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                // Populating Data into ListView
                ArrayList<Question> list = Question.fromJson(result.getJSONArray("data"));
                if( !list.isEmpty() ){
                    question = list.get(0);
                    setData();
                    //ListView listView = (ListView) findViewById(R.id.layout_list_answers);
                    //AnswerItemAdapter adapter = new AnswerItemAdapter(context, question.listAnswers);
                    //listView.setAdapter(adapter);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_question);
            loadingLayout.setVisibility(View.GONE);
        }

    }

    /* Sugerir Publicacion*/
    public void suggestPost(View view){

    }
}
