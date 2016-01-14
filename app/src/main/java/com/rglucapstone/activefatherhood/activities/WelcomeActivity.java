package com.rglucapstone.activefatherhood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;

import com.rglucapstone.activefatherhood.R;

/**
 * Created by ronald on 09/01/16.
 */
public class WelcomeActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void keepOn(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void showLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void doRegister(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
