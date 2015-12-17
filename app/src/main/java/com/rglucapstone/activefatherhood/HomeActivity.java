package com.rglucapstone.activefatherhood;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Luisa Castro on 16/12/2015.
 */
public class HomeActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        final RelativeLayout content_question = (RelativeLayout) findViewById(R.id.content_question);
        final RelativeLayout content_categories = (RelativeLayout) findViewById(R.id.content_categories);
        final RelativeLayout content_tips = (RelativeLayout) findViewById(R.id.content_tips);

        final Button btn_menu_general= (Button) findViewById(R.id.menu_general);
        final Button menu_categories= (Button) findViewById(R.id.menu_categories);
        final Button menu_tips= (Button) findViewById(R.id.menu_tips);

        content_categories.setVisibility(View.INVISIBLE);
        content_tips.setVisibility(View.INVISIBLE);
        btn_menu_general.setBackgroundColor(Color.WHITE);

        btn_menu_general.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn_menu_general.setBackgroundColor(Color.WHITE);
                menu_categories.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_tips.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_categories.setVisibility(View.INVISIBLE);
                content_tips.setVisibility(View.INVISIBLE);
                content_question.setVisibility(View.VISIBLE);
               /* content_question.setVisibility((content_question.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);*/
            }
        });

        menu_categories.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu_categories.setBackgroundColor(Color.WHITE);
                btn_menu_general.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_tips.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_question.setVisibility(View.INVISIBLE);
                content_tips.setVisibility(View.INVISIBLE);
                content_categories.setVisibility(View.VISIBLE);
                /*content_categories.setVisibility((content_categories.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);*/
            }
        });

        menu_tips.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu_tips.setBackgroundColor(Color.WHITE);
                btn_menu_general.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));
                menu_categories.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_no_button));

                content_question.setVisibility(View.INVISIBLE);
                content_categories.setVisibility(View.INVISIBLE);
                content_tips.setVisibility(View.VISIBLE);
               /* content_tips.setVisibility((content_categories.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);*/
            }
        });
    }
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
