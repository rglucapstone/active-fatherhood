package com.rglucapstone.activefatherhood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ronald on 15/12/15.
 */
public class RegisterActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
        }

    /*public void keepOn(View view) {
    Intent intent = new Intent(this, CategoriesActivity.class);
    startActivity(intent);
}*/
    public void keepOn(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
