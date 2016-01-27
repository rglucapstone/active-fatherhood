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

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Question;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ronald on 15/12/15.
 */
public class AskActivity extends AppCompatActivity {
    private Button button_asking;
    final Context context = this;
    private TextView container;

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
                EditText iquestion = (EditText)findViewById(R.id.input_question);
                String content = iquestion.getText().toString();
                String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                int user_id = 2;
                Question q = new Question(content, date.toString(), Integer.toString(user_id));
                if( q.send() == true ){
                    Intent intent = new Intent(context, HomeActivity.class);
                    startActivity(intent);
                    //startActivityForResult(intent, 0);
                }
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
}
