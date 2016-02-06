package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.adapters.ResultAdapter;
import com.rglucapstone.activefatherhood.adapters.SuggestsAdapter;
import com.rglucapstone.activefatherhood.data.Post;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.Suggest;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Luisa Castro on 24/01/2016.
 */
public class DirectQuestionsActivity extends AppCompatActivity {

    public User user;
    public ResultAdapter adapter;
    public ArrayList<Question> list;
    public Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_questions);
        setToolbar();
        this.context = this;

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");

        //TextView txt_questions = (TextView) findViewById(R.id.title_preference);
        //txt_questions.setText(user_id);

        this.user = new User(new loadQuestions());
        this.user.id = user_id;
        this.user.loadQuestions("direct");
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Preguntas directas");
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


    private class loadQuestions extends RestfulClient{

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                if( this.status == 200 ){
                    list = Question.fromJson(result.getJSONArray("data"));
                    ListView list_questions = (ListView) findViewById(R.id.listDirectQuestions);
                    adapter = new ResultAdapter(context, list);
                    adapter.logged = user;
                    list_questions.setAdapter(adapter);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

        }

    }


}
