package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rglucapstone.activefatherhood.R;

/**
 * Created by Luisa Castro on 24/01/2016.
 */
public class PublicationsGuru extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_publications_guru);
        setToolbar();
    }

    /* Toolbar */
    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Publicaciones");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
