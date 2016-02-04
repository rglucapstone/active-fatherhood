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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by ronald on 09/01/16.
 */
public class LoginActivity extends Activity{

    final Context context = this;
    private Button button;
    ArrayList selectedCategories;

    Button btnLogin;
    Button Btnregister;
    EditText inputEmail;
    EditText inputPassword;

    public User user;
    public ArrayList<User> list;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (  ( !inputEmail.getText().toString().equals("")) && ( !inputPassword.getText().toString().equals("")) ){
                    NetAsync(view);
                }else if ( ( !inputEmail.getText().toString().equals("")) ){
                    Toast.makeText(getApplicationContext(),
                            "La contraseña está vacía", Toast.LENGTH_SHORT).show();
                }
                else if ( ( !inputPassword.getText().toString().equals("")) ){
                    Toast.makeText(getApplicationContext(),
                            "El usuario está vacío", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "El usuario o contraseña no son válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    /* Validate Login */
    public void loginVerify(View view) {

        EditText username = (EditText)findViewById(R.id.input_email);
        EditText password = (EditText)findViewById(R.id.input_password);

        //Intent intent = new Intent(context, PreferencesActivity.class);
        //startActivity(intent);

       if(!username.getText().toString().equals("") && !password.getText().toString().equals("")){
            Intent intent = getIntent();
            this.user = new User(new loadUser());
            this.user.loadbyLogin(username.getText().toString());
            //Toast.makeText(getBaseContext(), "Uno", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), "Uno o más campos están vacíos", Toast.LENGTH_SHORT).show();
        }

    }

    private class loadUser extends RestfulClient {
        @Override
        protected void onPreExecute() {}

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                list = User.fromJson(result.getJSONArray("data"));
                if( list.size() > 0 ){
                    user = list.get(0);

                    TextView txt_login = (TextView) findViewById(R.id.input_email);
                    TextView txt_password = (TextView) findViewById(R.id.input_password);
                    if( txt_login.getText().toString().equals(user.login) && txt_password.getText().toString().equals(user.password) ){
                        Intent intent = new Intent(context, PreferencesActivity.class);
                        intent.putExtra("user_id", user.id);
                        startActivity(intent);
                    }else
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña ", Toast.LENGTH_SHORT).show();

                }
             }catch (JSONException e){
                e.printStackTrace();
            }
        }
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

    //*********************** Login ************************


}
