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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toggleBusy(View.GONE);
    }

    public void login(View view) {
        EditText username = (EditText)findViewById(R.id.input_user_email);
        EditText password = (EditText)findViewById(R.id.input_password);

       if(!username.getText().toString().equals("") && !password.getText().toString().equals("")){
            User user = new User(new taskLogin());
            user.doLogin(username.getText().toString());
        }else{
            Toast.makeText(getBaseContext(), "Uno o más campos están vacíos", Toast.LENGTH_SHORT).show();
        }
    }
        //Intent intent = new Intent(context, PreferencesActivity.class);
       // startActivity(intent);


      /* if(!username.getText().toString().equals("") && !password.getText().toString().equals("")){
            Intent intent = getIntent();
            this.user = new User(new loadUser());
            this.user.loadbyLogin(username.getText().toString());
            //Toast.makeText(getBaseContext(), "Uno", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), "Uno o más campos están vacíos", Toast.LENGTH_SHORT).show();
        }*/

    public void toggleBusy(int visibility){
        RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_login);
        loadingLayout.setVisibility(visibility);
    }

    private class taskLogin extends RestfulClient {

        @Override
        protected void onPreExecute() {
            toggleBusy(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                ArrayList<User> list = User.fromJson(result.getJSONArray("data"));
                if( list.size() > 0 ){
                    User user = list.get(0);

                    TextView txt_login = (TextView) findViewById(R.id.input_user_email);
                    TextView txt_password = (TextView) findViewById(R.id.input_password);
                    if( txt_login.getText().toString().equals(user.login) && txt_password.getText().toString().equals(user.password) ){
                        toggleBusy(View.GONE);
                        Intent intent = new Intent(getApplicationContext(), PreferencesActivity.class);
                        intent.putExtra("user_id", user.id);
                        startActivity(intent);
                    }else
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto.", Toast.LENGTH_SHORT).show();
                }
             }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

}
