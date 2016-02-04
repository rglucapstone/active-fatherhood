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
public class PublicationsGuru extends AppCompatActivity {

    public User user;
    public SuggestsAdapter adapter;
    public ArrayList<Suggest> list;
    public Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications_guru);
        setToolbar();
        this.context = this;

        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");

        //TextView txt_questions = (TextView) findViewById(R.id.txt_questions);
        //txt_questions.setText(user_id);

        this.user = new User(this, new loadSuggestions());
        this.user.id = user_id;
        this.user.load_suggest();
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Publicaciones");
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


    private class loadSuggestions extends RestfulClient {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {

            try {
                if( this.status == 200 ){
                    list = Suggest.fromJson(result.getJSONArray("data"));
                    //String s = list.get(0).content;

                    //TextView txt_post_user = (TextView) findViewById(R.id.txt_post_user);
                    //txt_post_user.setText(s);

                    ListView lv = (ListView) findViewById(R.id.listSuggests);
                    adapter = new SuggestsAdapter(context, list);
                    adapter.logged = user;
                    lv.setAdapter(adapter);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }


            /*


            RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_suggests);
            loadingLayout.setVisibility(View.GONE);*/
        }

    }


}
