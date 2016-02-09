package com.rglucapstone.activefatherhood.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.internal.ImageRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.sync.RestfulClient;

import java.io.InputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;
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

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;

/**
 * Created by ronald on 09/01/16.
 */
public class LoginActivity extends FragmentActivity {
    private LoginButton loginBtn;
    private TextView username;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        toggleBusy(View.GONE);

        loginBtn = (LoginButton) findViewById(R.id.login_button);
        loginBtn.setReadPermissions("email");
        // If using in a fragment
        callbackManager = CallbackManager.Factory.create();
        loginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Toast.makeText(getBaseContext(), "Facebook ok", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), PreferencesActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getBaseContext(), "Los siento, hubo un problema con tu conexión con Facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        String user_id = accessToken.getUserId();
        Toast.makeText(getBaseContext(), "user id:" + user_id, Toast.LENGTH_SHORT).show();

        //get Profile Image
        //Bitmap profile_image = getFacebookProfilePicture(user_id);

        if (accessToken.getCurrentAccessToken() != null) {
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                       // public void onCompleted(JSONObject me, GraphResponse response) {
                        public void onCompleted(JSONObject me, GraphResponse response) {
                            if (AccessToken.getCurrentAccessToken() != null) {
                                if (me != null) {
                                    //String profileImageUrl = ImageRequest.getProfilePictureUri(me.optString("id"), 500, 500).toString();
                                    String fb_name = me.optString("name");
                                    String fb_email = me.optString("email");
                                    String fb_birthday = me.optString("birthday");
                                    //String email = me.asMap().get("email").toString();
                                    //Log.i("fb", fbName);
                                    Toast.makeText(getBaseContext(), "user name:" + fb_name, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getBaseContext(), "user email:" + fb_email, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getBaseContext(), "user birthday:" + fb_birthday, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
            GraphRequest.executeBatchAsync(request);
        }

    }

    /*public void getProfileInformation() {
        AsyncFacebookRunner mAsyncRunner = null;
        mAsyncRunner.request("me", new RequestListener() {

            public void onComplete(String response, Object state) {
                Log.d("Profile", response);
                String json = response;
                try {
                    JSONObject profile = new JSONObject(json);
                    // getting name of the user
                    final String name = profile.getString("name");
                    // getting email of the user
                    final String email = profile.getString("email");
                    // getting email of the user
                    final String id = profile.getString("id");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Name: " + name + "\nEmail: " + email + "\nId: " + id, Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void onIOException(IOException e, Object state) { }
            public void onFileNotFoundException(FileNotFoundException e, Object state) {}
            public void onMalformedURLException(MalformedURLException e, Object state) {}
            public void onFacebookError(FacebookError e, Object state) { }
        });

    }*/

    public static Bitmap getFacebookProfilePicture(String userID){
        Bitmap bitmap=null;
        final String nomimg = "https://graph.facebook.com/"+ userID +"/picture?type=large";
        URL imageURL = null;

        try {
            imageURL = new URL(nomimg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects( true );
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            //img_value.openConnection().setInstanceFollowRedirects(true).getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {

            e.printStackTrace();
        }
        return bitmap;
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
                if( this.status == 200 ){
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
                        }else {
                            toggleBusy(View.GONE);
                            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    toggleBusy(View.GONE);
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto.", Toast.LENGTH_SHORT).show();
                }

             }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

}
