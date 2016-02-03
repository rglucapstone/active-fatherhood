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
        intent.putExtra("viewBy","categorie");
        switch (tag){
            case "aprendizaje":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Aprendizaje");
                startActivity(intent);
                break;
            case "comportamiento":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Comportamiento");
                startActivity(intent);
                break;
            case "comunicacion":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Comunicación");
                startActivity(intent);
                break;
            case "problemas_sociales":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Problemas Sociales");
                startActivity(intent);
                break;
            case "salud":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Salud");
                startActivity(intent);
                break;
            case "alimentacion":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Alimentación");
                startActivity(intent);
                break;
            case "enfermedades":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Enfermedades");
                startActivity(intent);
                break;
            case "discapacidades":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Discapacidades");
                startActivity(intent);
                break;
            case "divorcio":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Divorcio");
                startActivity(intent);
                break;
            case "adopcion":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Adopción");
                startActivity(intent);
                break;
            case "recreacion":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Recreacion");
                startActivity(intent);
                break;
            case "sexualidad":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Sexualidad");
                startActivity(intent);
                break;
            case "solteros":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Padres Solteros");
                startActivity(intent);
                break;
            case "primerizos":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Padres primerizos");
                startActivity(intent);
                break;
            case "futuros":
                intent.putExtra("str_themes",tag);
                intent.putExtra("title","Futuros Padres");
                startActivity(intent);
                break;
        }


        //Intent intent = new Intent(this, HomeActivity.class);
        //intent.putExtra("value", enteredValue.getContext().toString());
        //intent.putExtra("id", enteredValue.getId());
       // intent.putExtra("str_themes", "babys");
       // startActivity(intent);
    }

}
