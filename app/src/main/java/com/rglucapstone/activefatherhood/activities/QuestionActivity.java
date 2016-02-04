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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
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
    public User logged;

    public User user_question;
    public ImageView ic_user;
    //public AnswerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        this.context = this;

        Intent intent = getIntent();

        this.logged = new User(intent.getStringExtra("logged_id"));

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
        intent.putExtra("logged_id", this.logged.id);
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

        final RelativeLayout link_user = (RelativeLayout) findViewById(R.id.link_question_user);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class); //create an Intent object
                intent.putExtra("user_id", question.user.id); //add data to the Intent object
                intent.putExtra("logged_id", logged.id); //add data to the Intent object
                context.startActivity(intent); //start the second activity
            }
        });

    }

    private void setData(){
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout_item_question);

        TextView txt_qcontent = (TextView) layout.findViewById(R.id.txt_qcontent);
        txt_qcontent.setText(this.question.content);

        TextView txt_qanswers = (TextView) findViewById(R.id.title_answers);
        txt_qanswers.setText(this.question.listAnswers.size() + " respuestas");

        TextView txt_quser = (TextView) layout.findViewById(R.id.txt_quser);
        txt_quser.setText(this.question.user.login);

        RelativeTimeTextView v = (RelativeTimeTextView) layout.findViewById(R.id.txt_question_date);
        v.setReferenceTime(question.created_ago);

        // set image user temporal
        final ImageView img_user = (ImageView) findViewById(R.id.ic_user);
        User user_question = question.user;
        setImageUser(img_user, user_question.id);


        //TextView txt_qdatetime = (TextView) layout.findViewById(R.id.txt_qdatetime);
        //txt_qdatetime.setText(this.question.created);

        setTags();
        for (int i = 1; i <= this.question.themes.length; i++) {
            int index = i - 1;

            int imgtag = context.getResources().getIdentifier("ic_question_tag" + i, "id", context.getPackageName());
            ImageView img_tag = (ImageView) layout.findViewById(imgtag);
            img_tag.setVisibility(View.VISIBLE);

            int txttag = context.getResources().getIdentifier("txt_question_tag" + i, "id", context.getPackageName());
            TextView txt_tag = (TextView) layout.findViewById(txttag);
            txt_tag.setText(question.themes[index]);
            txt_tag.setVisibility(View.VISIBLE);
        }

        AnswerItemAdapter adapter = new AnswerItemAdapter(this, this.question.listAnswers);
        adapter.logged = this.logged;
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

    public void setTags(){

        int total = 3;
        for (int i = 1; i <= total; i++) {
            int imgtag = context.getResources().getIdentifier("ic_question_tag" + i, "id", context.getPackageName());
            ImageView img_tag = (ImageView) findViewById(imgtag);
            img_tag.setVisibility(View.GONE);

            int txttag = context.getResources().getIdentifier("txt_question_tag" + i, "id", context.getPackageName());
            TextView txt_tag = (TextView) findViewById(txttag);
            txt_tag.setVisibility(View.GONE);

        }
    }

    public void setImageUser(ImageView img_view,String id){
        switch (id){
            case "1":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            case "2":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "3":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            case "4":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "5":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "6":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "8":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "9":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "10":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "11":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "13":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "15":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "14":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "16":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            default:
                img_view.setBackgroundResource(R.drawable.ico_profile_grey);
                break;
        }
    }
}
