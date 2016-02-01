package com.rglucapstone.activefatherhood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;

/**
 * Created by Luisa Castro on 01/02/2016.
 */
public class CategoriesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setToolbar();
    }

    /* Toolbar */
    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Categorías");
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

    /* Aprendizaje */
    public void goCategorie( View view ){
        ImageButton enteredValue = (ImageButton)findViewById(R.id.btn_aprendizaje);
        Intent intent = new Intent(this, CategorieItemActivity.class);
        intent.putExtra("value", enteredValue.getContext().toString());
        intent.putExtra("id", enteredValue.getId());
        startActivity(intent);
    }

}
