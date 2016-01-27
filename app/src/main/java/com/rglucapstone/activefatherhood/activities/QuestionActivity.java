package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    private ArrayAdapter listAnswerAdapter;
    private ListView listAnswer;
    public Question question;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        this.context = this;

        Intent intent = getIntent();
        this.question = new Question(this, new loadQuestion());
        this.question.load(intent.getStringExtra("question_id"));

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Pregunta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //listAnswerAdapter = new AnswerItemAdapter(this, new String[10]);
        //listAnswer = (ListView) findViewById(R.id.listAnswer);
        //listAnswer.setAdapter(listAnswerAdapter);
    }

    public void answer(View view) {
        Intent intent = new Intent(this, AnswerActivity.class);
        startActivity(intent);
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buscar:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void setData(){
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout_item_question);

        TextView txt_quser = (TextView) layout.findViewById(R.id.txt_quser);
        TextView txt_qdatetime = (TextView) layout.findViewById(R.id.txt_qdatetime);
        TextView txt_qcontent = (TextView) layout.findViewById(R.id.txt_qcontent);
        TextView txt_qanswers = (TextView) findViewById(R.id.title_answers);

        txt_quser.setText(this.question.user.name);
        txt_qdatetime.setText(this.question.created);
        txt_qcontent.setText(this.question.content);
        txt_qanswers.setText(this.question.total_answers + " respuestas");

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
}
