package com.rglucapstone.activefatherhood.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.rglucapstone.activefatherhood.R;
import com.facebook.FacebookSdk;

/**
 * Created by ronald on 09/01/16.
 */
public class WelcomeActivity extends FragmentActivity {
    Button btnPosition;
    BadgeView badge1;

    private LoginButton loginBtn;
    private TextView username;
    //private UiLifecycleHelper uiHelper;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_welcome);

      /*  loginBtn = this.findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

        /**** Example Badge para Profile *****
        btnPosition = (Button) findViewById(R.id.btn_login);
        badge1 = new BadgeView(this, btnPosition);
        badge1.setText("0");
        //badge1.setBadgePosition(BadgeView.POSITION_CENTER);
        //badge1.toggle();
        btnPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (badge1.isShown()) {
                    badge1.increment(1);
                } else {
                    badge1.show();
                }
            }
        });*/
    }

    // Ir a la vista de Ingreso
    public void showLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // Ir a la vista de Creacion de Usuario
    public void doSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
