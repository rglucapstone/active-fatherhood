package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;

import java.util.ArrayList;

/**
 * Created by Luisa Castro on 17/12/2015.
 */
public class ProfileActivity extends Activity {
    private ArrayAdapter listQuestionsAdapter;
    private ListView listQuestions;
    private ArrayList<Question> arrayOfQuestions;
    private Context list;

    private ArrayAdapter listInfoGuruAdapter;
    private ListView listPadresGurus;
    private ArrayList<Question> arrayOfInfoGuru;

    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String id = intent.getStringExtra("user_id");

        User user = User.load(id);

        TextView txt_name = (TextView) findViewById(R.id.txt_profile_name);
        txt_name.setText(user.name);


    }

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
}
