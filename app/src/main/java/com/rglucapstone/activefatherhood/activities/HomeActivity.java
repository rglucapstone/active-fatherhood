package com.rglucapstone.activefatherhood.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.HomeFragmentPagerAdapter;
import com.rglucapstone.activefatherhood.fragments.ListQuestionsFragment;
import com.rglucapstone.activefatherhood.fragments.ListPostsFragment;
import com.rglucapstone.activefatherhood.fragments.ListGurusFragment;


/**
 * Created by ronald on 09/01/16.
 */
public class HomeActivity extends AppCompatActivity {

    public String str_themes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // main view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        this.str_themes = intent.getStringExtra("str_themes");
        //TextView test = (TextView) findViewById(R.id.test);
        //test.setText(themes);

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

    /**
     * Set Tabs
     * */
    private void setupViewPager(ViewPager viewPager) {

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),HomeActivity.this);

        Bundle bundle = new Bundle();
        bundle.putString("str_themes", this.str_themes);

        ListFragment questions = new ListQuestionsFragment();
        questions.setArguments(bundle);
        adapter.addFragment(questions, "Preguntas");

        ListFragment posts = new ListPostsFragment();
        posts.setArguments(bundle);
        adapter.addFragment(posts, "Publicaciones");

        Fragment gurus = new ListGurusFragment();
        gurus.setArguments(bundle);
        adapter.addFragment(gurus, "Padres Gurús");

        viewPager.setAdapter(adapter);
    }

    /**
     * Actions for menu
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchActionBarItem = menu.findItem(R.id.action_buscar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchActionBarItem);

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        return true;
    }
    /**
     * Select actions for menu
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buscar:
                return true;
            case R.id.action_my_profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivityForResult(intent, 0);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.menu_categories:
                goCategories(getBaseContext());
                return true;
            case R.id.menu_preferences:
                goPreferences(getBaseContext());
                return true;
            case R.id.menu_get_out:
                Toast.makeText(getBaseContext(), "Cerrar Sesión", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Ir a Categorias
     * @param context
     */
    public void goCategories(Context context){
        Intent intent = new Intent(context, CategoriesActivity.class);
        startActivityForResult(intent, 0);
    }

    /** Cambiar Preferencias
     * @param context
     */
    public void goPreferences(Context context){
        Intent intent = new Intent(context, PreferencesActivity.class);
        startActivityForResult(intent, 0);
    }
    /**
     * Function to change default icon search
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchViewMenuItem = menu.findItem(R.id.action_buscar);
        SearchView mSearchView = (SearchView) searchViewMenuItem.getActionView();
        int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
        ImageView v = (ImageView) mSearchView.findViewById(searchImgId);
        v.setImageResource(R.mipmap.ic_search_white_24dp);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Do a Question
     */
    public void ask(View view) {
        Intent intent = new Intent(this, AskActivity.class);
        startActivity(intent);
    }

    /**
     * Do a Post
     */
    public void post(View view) {
        Intent intent = new Intent(this, DoPost.class);
        startActivity(intent);
    }

    public void onPreExecute(){

    }
}
