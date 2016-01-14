package com.rglucapstone.activefatherhood.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.HomeFragmentPagerAdapter;
import com.rglucapstone.activefatherhood.fragments.ListQuestionsFragment;
import com.rglucapstone.activefatherhood.fragments.ListPostsFragment;
import com.rglucapstone.activefatherhood.fragments.ListGurusFragment;


/**
 * Created by ronald on 09/01/16.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // main view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        setSupportActionBar(toolbar);

        // tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_tabs);
        setupViewPager(viewPager);
        //viewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(), HomeActivity.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbar_tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),HomeActivity.this);
        adapter.addFragment(new ListQuestionsFragment(), "Preguntas");
        adapter.addFragment(new ListPostsFragment(), "Publicaciones");
        adapter.addFragment(new ListGurusFragment(), "Padres Gurús");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);


        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchActionBarItem = menu.findItem(R.id.action_buscar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchActionBarItem);
        //SearchView searchView = (SearchView) menu.findItem(R.id.action_buscar).getActionView();

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default

       /* SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_buscar).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);*/

        return true;
    }

    public void ask(View view) {
        Intent intent = new Intent(this, AskActivity.class);
        startActivity(intent);
    }

    public void profileOn(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
