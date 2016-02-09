package com.rglucapstone.activefatherhood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    /* Go Item Categorie */
    public void goCategorie( View view ){
        String tag = view.getTag().toString();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("view_by","categories");
        switch (tag){
            case "aprendizaje":
                intent.putExtra("prefers","7");
                intent.putExtra("title", "Aprendizaje");
                break;
            case "comportamiento":
                intent.putExtra("prefers","8");
                intent.putExtra("title","Comportamiento");
                break;
            case "comunicacion":
                intent.putExtra("prefers","11");
                intent.putExtra("title","Comunicación");
                break;
            case "problemas_sociales":
                intent.putExtra("prefers","9");
                intent.putExtra("title","Problemas Sociales");
                break;
            case "salud":
                intent.putExtra("prefers","4");
                intent.putExtra("title","Salud");
                break;
            case "alimentacion":
                intent.putExtra("prefers","17");
                intent.putExtra("title","Alimentación");
                break;
            case "enfermedades":
                intent.putExtra("prefers","10");
                intent.putExtra("title","Enfermedades");
                break;
            case "discapacidades":
                intent.putExtra("str_themes","20");
                intent.putExtra("title","Discapacidades");
                break;
            case "divorcio":
                intent.putExtra("prefers","18");
                intent.putExtra("title","Divorcio");
                break;
            case "adopcion":
                intent.putExtra("prefers","19");
                intent.putExtra("title","Adopción");
                break;
            case "recreacion":
                intent.putExtra("prefers","21");
                intent.putExtra("title","Recreacion");
                break;
            case "sexualidad":
                intent.putExtra("prefers","22");
                intent.putExtra("title","Sexualidad");
                break;
            case "solteros":
                intent.putExtra("prefers","13");
                intent.putExtra("title","Padres Solteros");
                break;
            case "primerizos":
                intent.putExtra("prefers","14");
                intent.putExtra("title","Padres primerizos");
                break;
            case "futuros":
                intent.putExtra("prefers","15");
                intent.putExtra("title","Futuros Padres");
                break;
        }
        startActivity(intent);


        //Intent intent = new Intent(this, HomeActivity.class);
        //intent.putExtra("value", enteredValue.getContext().toString());
        //intent.putExtra("id", enteredValue.getId());
       // intent.putExtra("str_themes", "babys");
       // startActivity(intent);
    }

}
