package com.rglucapstone.activefatherhood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;

/**
 * Created by Luisa Castro on 02/02/2016.
 */
public class CategorieItemActivity extends AppCompatActivity {
    private int title;
     @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie_item);
        setToolbar();

        //get id of categorie
        Intent in = getIntent();
        int val = in.getIntExtra("id", 0);
         Toast.makeText(getBaseContext(), String.valueOf(val) , Toast.LENGTH_SHORT).show();
         Toast.makeText(getBaseContext(), Integer.toString(val) , Toast.LENGTH_SHORT).show();
    }

    /* Toolbar */
    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("prueba");
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
