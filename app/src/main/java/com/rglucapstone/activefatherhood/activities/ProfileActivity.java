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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.QuestionsAdapter;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONException;
import org.json.JSONObject;

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

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    public User user;
    public ArrayList<User> list;
    public ImageView img_user;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        this.user = new User(this, new loadUser());
        this.user.load(intent.getStringExtra("user_id"));

        //************************ *Set photo user (parche) ***************************************
        final String usr_id = this.user.id;
        final ImageView usr_img = (ImageView) this.findViewById(R.id.img_profile);
        //Toast.makeText(getBaseContext(), "id:" + this.user.id + "", Toast.LENGTH_SHORT).show();
       /* switch (usr_id){
            case "1":
                usr_img.setBackgroundResource(R.drawable.padre4);
                break;
            case "2":
                usr_img.setBackgroundResource(R.drawable.padre5);
                break;
            case "3":
                usr_img.setBackgroundResource(R.drawable.padre8);
                break;
            case "4":
                usr_img.setBackgroundResource(R.drawable.padre10);
                break;
            default:
                usr_img.setBackgroundResource(R.drawable.padre2);
                break;
        }*/
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
        startActivity(intent);
    }

    /* Realizar Pregunta */
    public void asking(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    /* Ver Publicaciones */
    public void publicationsGuru(View view) {
        Intent intent = new Intent(this, PublicationsGuru.class);
        startActivity(intent);
    }

    private void setData(){
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

        TextView txt_me_gusta = (TextView) findViewById(R.id.txt_likes);
        txt_me_gusta.setText(this.user.total_likes);

        //txt_goodfather.setText(Integer.toString(this.list.size()));
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

            RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_profile);
            loadingLayout.setVisibility(View.GONE);
        }

    }
}
