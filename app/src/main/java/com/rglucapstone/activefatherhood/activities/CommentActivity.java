package com.rglucapstone.activefatherhood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.rglucapstone.activefatherhood.R;

import java.util.ArrayList;

/**
 * Created by Luisa Castro on 27/01/2016.
 */
public class CommentActivity extends AppCompatActivity {
    private LinearLayout button;
    ArrayList selectedCategories;
    final Context context = this;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        toolbar.setTitle("Agregar comentario");
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

    /**
     * Enviar Comentario y Actualizar la publicación
     * @param view
     */
    public void comment(View view) {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }
}
