package com.rglucapstone.activefatherhood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rglucapstone.activefatherhood.R;

/**
 * Created by Luisa Castro on 27/01/2016.
 */
public class PostActivity extends AppCompatActivity {

    private TextView container;
    private LinearLayout container_report;
    private ImageButton btn_like;
    private int btn_like_status = 0;//se obtiene de base si esta pregunta me gusta, devuelve 1

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_post);
        setToolbar();
        setActions();
    }

    /* Toolbar */
    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Publicación");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /* Actions */
    public void setActions(){

        /* Se oculta la fecha y el reporte de la publicacion */
        container = (TextView) findViewById(R.id.txt_date_post);
        container_report = (LinearLayout) findViewById(R.id.container_report_post);

        container.setVisibility(View.GONE);
        container_report.setVisibility(View.GONE);

        /* Se cambia el background del icono Me gusta si este es presionado */
        btn_like = (ImageButton) findViewById(R.id.btn_like_post);
        btn_like.setVisibility(View.VISIBLE);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if( btn_like_status == 0 ) {
                    btn_like_status = 1;
                    btn_like.setBackgroundResource(R.mipmap.ico_like_blue_24);
                } else {
                    btn_like_status = 0;
                    btn_like.setBackgroundResource(R.mipmap.ico_like_grey_24);
                }
            }
        });
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

    /* Agregar Comentario */
    public void commenting(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        startActivity(intent);
    }

}