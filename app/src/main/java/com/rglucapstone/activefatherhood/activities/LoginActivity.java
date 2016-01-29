package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.rglucapstone.activefatherhood.R;

import java.util.ArrayList;

/**
 * Created by ronald on 09/01/16.
 */
public class LoginActivity extends Activity{

    final Context context = this;
    private Button button;
    ArrayList selectedCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /* Button Entrar: go to Add Preference */
    public void preferencesOn(View view) {
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

    public void keepOn (View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void doRegister (View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void dialogPreference(){
           /*
        button = (Button) findViewById(R.id.btn_enter);
        View view;
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                // Show container dialog preferences
                selectedCategories = new ArrayList();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                //alertDialogBuilder.setTitle(R.string.message_preferences);
                LayoutInflater inflater = getLayoutInflater();
                alertDialogBuilder.setView(inflater.inflate(R.layout.activity_preferences, null))
                        // Add action buttons
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                LoginActivity.this.finish();
                                Intent intent = new Intent(context, HomeActivity.class);
                                startActivityForResult(intent, 0);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                    LoginActivity.this.closeContextMenu();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });*/
    }
}
