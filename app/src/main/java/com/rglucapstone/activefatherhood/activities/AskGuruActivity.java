package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.rglucapstone.activefatherhood.R;

import java.util.ArrayList;

/**
 * Created by Luisa Castro on 17/01/2016.
 */
public class AskGuruActivity extends AppCompatActivity {
    private RelativeLayout container;
    private Button button_asking;
    final Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Realizar Pregunta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /** Se oculta el contenedor de seleccionar tags, cuando es una
         * pregunta directa
         */
        //container = (RelativeLayout) findViewById(R.id.container_tags);
        //container.setVisibility(View.GONE);

        /**
         * Sobreescribo la accion, cuando realizo la pregunta se actualiza
         * la actividad en el perfil, como primer item
         */
        button_asking = (Button) findViewById(R.id.btn_asking);
        View view;
        button_asking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
               // SignUpActivity.this.finish();
                Intent intent = new Intent(context, ProfileActivity.class);
                startActivityForResult(intent, 0);
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
}