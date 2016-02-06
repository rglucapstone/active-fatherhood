package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.adapters.ResultAdapter;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Luisa Castro on 17/12/2015.
 */
public class ProfileActivity extends Activity {
    private ArrayAdapter listQuestionsAdapter;
    private ListView listQuestions;

    private Context context;

    private ArrayAdapter listInfoGuruAdapter;
    private ListView listPadresGurus;
    private ArrayList<Question> arrayOfInfoGuru;

    private final int num_starts = 4;

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    public User user;
    public ArrayList<User> list;
    public ImageView img_user;

    public User logged;
    public String user_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.context = this;

        Intent intent = getIntent();
        this.user = new User(this, new loadUser());
        user_id = intent.getStringExtra("user_id");
        this.user.load(user_id);

        this.logged = new User(intent.getStringExtra("logged_id"));


        //set photo user (temporal)
        img_user = (ImageView) this.findViewById(R.id.img_profile);
        this.user.setImageUser(img_user, user_id);

    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        return view;
    }*/

    /* Action Back*/
    public void backProfile(View view) {
        this.finish();
    }

    /* Editar Datos */
    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    /* Preguntar */
    public void askGuru(View view){
        Intent intent = new Intent(this, AskGuruActivity.class);
        intent.putExtra("user_id", this.user.id);
        intent.putExtra("user_creator_id", this.logged.id);
        startActivity(intent);
    }

    public void asking(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void publicationsGuru(View view) {
        Intent intent = new Intent(this, PublicationsGuru.class);
        intent.putExtra("user_id", this.logged.id);
        startActivity(intent);
    }

    public void directQuestions(View view) {
        Intent intent = new Intent(this, DirectQuestionsActivity.class);
        intent.putExtra("user_id", this.logged.id);
        startActivity(intent);
    }

    private void setData(){

        //TextView txt_test = (TextView) findViewById(R.id.txt_test);
        //txt_test.setText(this.logged.id + " : "+this.user.id);

        TextView txt_name = (TextView) findViewById(R.id.txt_profile_name);
        TextView txt_goodfather = (TextView) findViewById(R.id.txt_profile_goodfather);
        txt_name.setText(this.user.name);
        txt_goodfather.setText(this.user.buen_padre);

        TextView txt_questions = (TextView) findViewById(R.id.txt_questions);
        txt_questions.setText(this.user.total_questions);

        TextView txt_answers = (TextView) findViewById(R.id.txt_answers);
        txt_answers.setText(this.user.total_answers);

        TextView txt_posts = (TextView) findViewById(R.id.txt_posts);
        txt_posts.setText(this.user.total_posts);

        TextView txt_profile_age = (TextView) findViewById(R.id.txt_profile_age);
        txt_profile_age.setText(this.user.edad + " años");

        TextView txt_me_gusta = (TextView) findViewById(R.id.txt_likes);
        txt_me_gusta.setText(this.user.total_likes);

        TextView guru_progress_text = (TextView) findViewById(R.id.guru_progress_text);
        guru_progress_text.setText(this.user.guru.name);

        RatingBar rate_guru = (RatingBar) findViewById(R.id.rating_guru);
        String str_rate = new DecimalFormat("##.##").format((Float.valueOf(user.rating)*this.num_starts)/100);
        rate_guru.setRating(Float.parseFloat(str_rate));

        TextView txt_profile_rating = (TextView) findViewById(R.id.txt_profile_rating);
        txt_profile_rating.setText(user.rating + " %");

        //guru_progress_text.setText(Integer.toString(this.user.themes.length));

        setThemes();
        if( this.user.themes.length > 0 ){
            for (int i = 1; i <= this.user.themes.length; i++) {
                int index = i - 1;

                int imgtag = getBaseContext().getResources().getIdentifier("ic_themes_tag" + i, "id",
                        getBaseContext().getPackageName());
                ImageView img_tag = (ImageView) findViewById(imgtag);
                img_tag.setVisibility(View.VISIBLE);

                int txttag = getBaseContext().getResources().getIdentifier("txt_themes_tag" + i, "id",
                        getBaseContext().getPackageName());
                TextView txt_tag = (TextView) findViewById(txttag);
                txt_tag.setText(this.user.themes[index]);
                txt_tag.setVisibility(View.VISIBLE);
            }
        }

        User user = new User(new loadQuestions());
        user.id = this.user.id;
        user.loadQuestions("normal");

    }


    public void setThemes(){

        int total = 3;
        for (int i = 1; i <= total; i++) {
            int imgtag = getBaseContext().getResources().getIdentifier("ic_themes_tag" + i, "id",
                    getBaseContext().getPackageName());
            ImageView img_tag = (ImageView) findViewById(imgtag);
            img_tag.setVisibility(View.GONE);

            int txttag = getBaseContext().getResources().getIdentifier("txt_themes_tag" + i, "id",
                    getBaseContext().getPackageName());
            TextView txt_tag = (TextView) findViewById(txttag);
            txt_tag.setVisibility(View.GONE);
        }
    }

    private class loadUser extends RestfulClient {

        @Override
        protected void onPreExecute() {
            //list.clear();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                // Populating Data into ListView
                list = User.fromJson(result.getJSONArray("data"));
                if( !list.isEmpty() ){
                    user = list.get(0);
                    setData();
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            if( user.id.toString().equals(logged.id.toString()) || user.guru.level < 3  ){
                LinearLayout btnAsk = (LinearLayout) findViewById(R.id.btn_ask_guru);
                btnAsk.setVisibility(View.GONE);
            }

            if( !(user.id.toString().equals(logged.id.toString())) ){
                LinearLayout btnPosts = (LinearLayout) findViewById(R.id.ly_actions_profile);
                btnPosts.setVisibility(View.GONE);
            }

            RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_profile);
            loadingLayout.setVisibility(View.GONE);

        }

    }


    private class loadQuestions extends RestfulClient{

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            ArrayList<Question> listQuestions = new ArrayList<>();

            ListView list_questions = (ListView) findViewById(R.id.list_questions);
            ResultAdapter adapter = new ResultAdapter(context, listQuestions);
            adapter.logged = logged;
            list_questions.setAdapter(adapter);
            try {
                if( this.status == 200 ){
                    listQuestions = Question.fromJson(result.getJSONArray("data"));
                    adapter.addAll(listQuestions);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            //RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_questions);
            //loadingLayout.setVisibility(View.GONE);
        }

    }
}
