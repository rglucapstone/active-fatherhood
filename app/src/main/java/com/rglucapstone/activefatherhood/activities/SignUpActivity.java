package com.rglucapstone.activefatherhood.activities;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ronald on 09/01/16.
 */
public class SignUpActivity extends Activity {
    private Button button;
    ArrayList selectedCategories;
    final Context context = this;

    EditText inputUsername;
    EditText inputEmail;
    EditText inputPassword;
    EditText inputEdad;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputUsername = (EditText) findViewById(R.id.input_user);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_contraseña);
        inputEdad = (EditText) findViewById(R.id.input_edad);
        btnRegister = (Button) findViewById(R.id.btn_acept);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!inputUsername.getText().toString().equals("")) && (!inputPassword.getText().toString().equals("")) && (!inputEmail.getText().toString().equals(""))) {
                    if ((inputUsername.getText().toString().length() < 4)) {
                        Toast.makeText(getApplicationContext(),
                                "El usuario debe tener mínimo 5 caracteres", Toast.LENGTH_SHORT).show();
                    } else if (inputUsername.getText().toString().length() > 15) {
                        Toast.makeText(getApplicationContext(),
                                "El usuario debe tener máximo 15 caracteres", Toast.LENGTH_SHORT).show();
                    } else{
                        User user = new User(context, new sendUser());
                        user.login = inputUsername.getText().toString();
                        user.email = inputEmail.getText().toString();
                        user.password = inputPassword.getText().toString();
                        user.edad = inputEdad.getText().toString();
                        user.send();

                        //Toast.makeText(getApplicationContext(), user.login , Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Uno o más campos están vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class sendUser extends RestfulClient {
        private Toast toast;

        @Override
        protected void onPreExecute() {
            toast = Toast.makeText(context, "Registrando", Toast.LENGTH_SHORT);
            toast.show();
        }
        @Override
        protected void onPostExecute(JSONObject result) {
            toast.cancel();
            toast = Toast.makeText(context, "El usuario se registró correctamente", Toast.LENGTH_SHORT);
            context.startActivity(new Intent(context, PreferencesActivity.class));
        }
    }

    /* Button Entrar: go to Add Preference */
    public void keepOn(View view) {
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

    public void signUp(View view){
        Bundle bundle = new Bundle();
        //bundle.putString("data", toString(view.getId()));

        // RegisterFragment register = new RegisterFragment();
        // questions.setArguments(bundle);
    }

    public void goPreference(){
        /*
        *  button = (Button) findViewById(R.id.btn_acept);
        View view;
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                selectedCategories = new ArrayList();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = getLayoutInflater();
                alertDialogBuilder.setView(inflater.inflate(R.layout.activity_preferences, null))
                        // Add action buttons
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                SignUpActivity.this.finish();
                                Intent intent = new Intent(context, HomeActivity.class);
                                startActivityForResult(intent, 0);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SignUpActivity.this.closeContextMenu();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });*/
    }
}
