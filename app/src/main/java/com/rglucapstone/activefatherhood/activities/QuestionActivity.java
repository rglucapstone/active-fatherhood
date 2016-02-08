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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.rglucapstone.activefatherhood.data.Answer;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ronald on 15/12/15.
 */
public class QuestionActivity extends AppCompatActivity {

    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        toggleAnswer(false);
        setToolbar();

        Question question = new Question(new load());
        question.load(getIntent().getStringExtra("question_id"));
    }

    /*private void initialize(){

        // hide answer layout
        LinearLayout answerLayout = (LinearLayout) findViewById(R.id.layout_answer);
        EditText input = (EditText) answerLayout.findViewById(R.id.input_answer);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
        answerLayout.setVisibility(View.GONE);

    }*/

    // setting toolbar
    private void setToolbar(){
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Pregunta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // setting actions
    private void setActions(final Question q, final User logged){
        final Context context = this;

        // load profile of user answer
        RelativeLayout link_user = (RelativeLayout) findViewById(R.id.link_question_user);
        link_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class); //create an Intent object
                intent.putExtra("user_id", q.user.id); //add data to the Intent object
                intent.putExtra("logged_id", logged.id); //add data to the Intent object
                context.startActivity(intent); //start the second activity
            }
        });

        // send answer
        Button btn_answer = (Button) findViewById(R.id.btn_answering);
        btn_answer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText textasnswer = (EditText) findViewById(R.id.input_answer);
                if ( validateAnswer(textasnswer.getText().toString()) ) {
                    Answer answer = new Answer(new sendAnswer());
                    answer.content = textasnswer.getText().toString();
                    answer.created = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    answer.user = logged;
                    answer.question_id = q.id;
                    answer.send();
                }
            }
        });

        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toggleAnswer(false);
            }
        });
    }

    // verify answer
    private boolean validateAnswer(String txtanswer){

        if( txtanswer.equals("") ){
            Toast.makeText(getBaseContext(), "Respuesta en blanco", Toast.LENGTH_SHORT).show();
            return false;
        }

        if( txtanswer.length() > 150 ){
            Toast.makeText(getBaseContext(), "Respuesta maximo de 150 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // initialize tags of themes
    private void setTags(Context context){
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

    // setting data
    private void setData(Question q){
        Context context = getApplicationContext();
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_item_question);

        // content
        TextView txt_qcontent = (TextView) layout.findViewById(R.id.txt_qcontent);
        txt_qcontent.setText(q.content);

        // total of answers
        TextView txt_qanswers = (TextView) findViewById(R.id.title_answers);
        txt_qanswers.setText(q.listAnswers.size() + " respuestas");

        // user
        TextView txt_quser = (TextView) layout.findViewById(R.id.txt_quser);
        txt_quser.setText(q.user.login);

        // relative date
        RelativeTimeTextView v = (RelativeTimeTextView) layout.findViewById(R.id.txt_question_date);
        v.setReferenceTime(q.created_ago);

        // set image user temporal
        final ImageView img_user = (ImageView) findViewById(R.id.ic_user);
        setImageUser(img_user, q.user.id);

        // set tags of thmes
        setTags(context);
        for (int i = 1; i <= q.themes.length; i++) {
            int index = i - 1;

            int imgtag = context.getResources().getIdentifier("ic_question_tag" + i, "id", context.getPackageName());
            ImageView img_tag = (ImageView) layout.findViewById(imgtag);
            img_tag.setVisibility(View.VISIBLE);

            int txttag = context.getResources().getIdentifier("txt_question_tag" + i, "id", context.getPackageName());
            TextView txt_tag = (TextView) layout.findViewById(txttag);
            txt_tag.setText(q.themes[index]);
            txt_tag.setVisibility(View.VISIBLE);
        }

        // set list of answers
        User logged = new User(getIntent().getStringExtra("logged_id"));
        setAnswers(q.listAnswers, logged);
    }

    // setting list of answers to adapter
    private void setAnswers(ArrayList<Answer> answers, User logged){
        AnswerItemAdapter adapter = new AnswerItemAdapter(this, answers, question, logged);
        LinearLayout layout_answers = (LinearLayout) findViewById(R.id.layout_list_answers);
        ListView list = (ListView) layout_answers.findViewById(R.id.listAnswer);
        list.setAdapter(adapter);
    }

    // visibility of views on answering
    private void toggleAnswer(boolean toggle){

        if( toggle ){
            LinearLayout layout = (LinearLayout) findViewById(R.id.container_answers);
            layout.setVisibility(View.GONE);

            LinearLayout btn_answer = (LinearLayout) findViewById(R.id.btn_do_answer);
            btn_answer.setVisibility(View.GONE);

            LinearLayout answerLayout = (LinearLayout) findViewById(R.id.layout_answer);
            EditText input = (EditText) answerLayout.findViewById(R.id.input_answer);
            if(input.requestFocus()){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(input, 0);
            }
            answerLayout.setVisibility(View.VISIBLE);

        }else{
            LinearLayout layout = (LinearLayout) findViewById(R.id.container_answers);
            layout.setVisibility(View.VISIBLE);

            LinearLayout btn_answer = (LinearLayout) findViewById(R.id.btn_do_answer);
            btn_answer.setVisibility(View.VISIBLE);

            LinearLayout answerLayout = (LinearLayout) findViewById(R.id.layout_answer);
            EditText input = (EditText) answerLayout.findViewById(R.id.input_answer);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
            answerLayout.setVisibility(View.GONE);
        }

    }

    // load answer view
    public void answer(View view) {
        toggleAnswer(true);
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

    // task to load question
    private class load extends RestfulClient {

        @Override
        protected void onPostExecute(JSONObject result) {
            User user = new User(getIntent().getStringExtra("logged_id"));
            //Question question;
            try {
                // Populating Data into ListView
                ArrayList<Question> list = Question.fromJson(result.getJSONArray("data"));
                if( !list.isEmpty() ){
                    question = list.get(0);
                    setData(question);
                    setActions(question, user);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            // hide busy
            RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_question);
            loadingLayout.setVisibility(View.GONE);
        }
    }

    // task to send answer
    private class sendAnswer extends RestfulClient {

        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(getApplicationContext(), "Enviando respuesta", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            Question q = new Question(new loadAnswers());
            q.loadAnswers(getIntent().getStringExtra("question_id"));
        }

    }

    // task to realod answers after make an answer
    private class loadAnswers extends RestfulClient {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            User logged = new User(getIntent().getStringExtra("logged_id"));
            try {
                ArrayList<Answer> list = Answer.fromJson(result.getJSONArray("data"));
                setAnswers(list, logged);

                TextView txt_answers = (TextView) findViewById(R.id.title_answers);
                txt_answers.setText(list.size() + " respuestas");

                toggleAnswer(false);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

    // setting image profile
    private void setImageUser(ImageView img_view,String id){
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
