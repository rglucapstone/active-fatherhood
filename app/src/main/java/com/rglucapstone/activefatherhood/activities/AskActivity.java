package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Question;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ronald on 15/12/15.
 */
public class AskActivity extends AppCompatActivity {
    final Context context = this;

    private Button button_asking;
    private TextView container;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        setToolbar();


        /**
         * Se oculta el titulo de preferencias
         */
        container = (TextView) findViewById(R.id.title_preference);
        container.setVisibility(View.GONE);

        // Luego de realizar la pregunta la muestra en el listado
        button_asking = (Button) findViewById(R.id.btn_asking);
        View view;
        button_asking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // setting data to send question
                EditText iquestion = (EditText) findViewById(R.id.input_question);
                int user_id = 2;

                // instance Question object
                Question question = new Question(context, new sendQuestion());
                question.content = iquestion.getText().toString();
                question.created = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                question.user = new User(Integer.toString(user_id));
                question.send();
            }
        });
    }

    /* Toolbar */
    public void setToolbar(){
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Agregar pregunta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /* Pedndiente revisar uso*/
    public void asking(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
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

    private class sendQuestion extends RestfulClient {

        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(context, "Enviando pregunta", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            context.startActivity(new Intent(context, HomeActivity.class));
        }

    }
}
