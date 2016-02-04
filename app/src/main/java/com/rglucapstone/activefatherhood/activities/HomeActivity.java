package com.rglucapstone.activefatherhood.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
//import android.widget.SearchView;
import android.support.v7.widget.SearchView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.HomeFragmentPagerAdapter;
import com.rglucapstone.activefatherhood.data.User;
import com.rglucapstone.activefatherhood.fragments.ListQuestionsFragment;
import com.rglucapstone.activefatherhood.fragments.ListPostsFragment;
import com.rglucapstone.activefatherhood.fragments.ListGurusFragment;
import com.rglucapstone.activefatherhood.fragments.SearchResultsFragment;

import java.lang.reflect.Field;


/**
 * Created by ronald on 09/01/16.
 */
public class HomeActivity extends AppCompatActivity {

    public String str_themes;
    public String view_by;
    public String item_categorie;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // main view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        this.str_themes = intent.getStringExtra("str_themes");
        this.view_by = intent.getStringExtra("viewBy");
        user = new User(intent.getStringExtra("user_id"));

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_action);
        setSupportActionBar(toolbar);

        //si se elige una categoria se cambia el title al toolbar
        view_by = intent.getStringExtra("viewBy");

        if(view_by!=null) {
            if (view_by.equals("categorie")) {
                item_categorie = intent.getStringExtra("title");
                getSupportActionBar().setTitle(item_categorie);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        // tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_tabs);
        setupViewPager(viewPager);
        //viewPager.setAdapter(new HomeFragmentPagerAdapter(getSupportFragmentManager(), HomeActivity.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbar_tabs);
        tabLayout.setupWithViewPager(viewPager);

        FrameLayout searchLayout = (FrameLayout) findViewById(R.id.layout_search_results);
        searchLayout.setVisibility(View.GONE);

    }

    private void showSearchView(){

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbar_tabs);
        tabLayout.setVisibility(View.GONE);

        FrameLayout searchLayout = (FrameLayout) findViewById(R.id.layout_search_results);
        searchLayout.setVisibility(View.VISIBLE);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.actionbar_search, null);
        final AutoCompleteTextView searchBox =  (AutoCompleteTextView) v.findViewById(R.id.search_box);

        MenuItem searchItem =  (MenuItem) findViewById(R.id.action_search);
        searchItem.setVisible(false);

        actionBar.setCustomView(v);*/
    }

    /**
     * Set Tabs
     * */
    private void setupViewPager(ViewPager viewPager) {

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),HomeActivity.this);

        Bundle bundle = new Bundle();
        bundle.putString("viewBy",this.view_by);
        bundle.putString("str_themes", this.str_themes);
        bundle.putString("logged_id", user.id);

        //TextView txt = (TextView) findViewById(R.id.title_preference2);
        //txt.setText(user.id + " : " + user.email);

        ListFragment questions = new ListQuestionsFragment();
        questions.setArguments(bundle);
        adapter.addFragment(questions, "Preguntas");

        ListFragment posts = new ListPostsFragment();
        posts.setArguments(bundle);
        adapter.addFragment(posts, "Publicaciones");

        Fragment gurus = new ListGurusFragment();
        gurus.setArguments(bundle);
        adapter.addFragment(gurus, "Top Padres");
        //Fragment results = new SearchResultsFragment();
        //adapter.addFragment(results, "Padres Gurús");

        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.action_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setBackgroundColor(Color.WHITE);
        final EditText txtSearch = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        //SearchView.SearchAutoComplete txtSearch = ((SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint(getResources().getString(R.string.search_hint_text));
        txtSearch.setTextColor(getResources().getColor(R.color.normalText));
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            //This sets the cursor resource ID to 0 or @null which will make it visible on white background
            mCursorDrawableRes.set(txtSearch, R.drawable.cursor_text);
        } catch (Exception e) {
        }

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //menuIsOpen = false;
                onCloseSearch();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                //menuIsOpen = true;
                //onStartSearch();
                return true;
            }
        });

        txtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    //RelativeLayout loadingLayout = (RelativeLayout) findViewById(R.id.loading_results);
                    //loadingLayout.setVisibility(View.VISIBLE);

                    Bundle bundle = new Bundle();
                    bundle.putString("query", txtSearch.getText().toString());
                    Fragment search = (Fragment) new SearchResultsFragment();
                    search.setArguments(bundle);
                    FragmentTransaction transact = getSupportFragmentManager().beginTransaction();
                    transact.replace(R.id.layout_search_results, search);
                    transact.commit();
                    return true;
                }
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
    /**
     * Select actions for menu
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                showSearchView();
                return true;
            case R.id.action_my_profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("user_id", user.id);
                intent.putExtra("logged_id", user.id);
                startActivityForResult(intent, 0);
                return true;
            case android.R.id.home:
                this.finish();
                //NavUtils.navigateUpFromSameTask(this);
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
        return super.onPrepareOptionsMenu(menu);
    }

    public void onCloseSearch(){

        TabLayout tabLayout = (TabLayout) findViewById(R.id.toolbar_tabs);
        tabLayout.setVisibility(View.VISIBLE);

        FrameLayout searchLayout = (FrameLayout) findViewById(R.id.layout_search_results);
        searchLayout.setVisibility(View.GONE);

    }

    /**
     * Do a Question
     */
    public void ask(View view) {
        Intent intent = new Intent(this, AskActivity.class);
        intent.putExtra("user_id", user.id);
        startActivity(intent);
    }

    public void post(View view) {
        Intent intent = new Intent(this, DoPost.class);
        intent.putExtra("user_id", user.id);
        startActivity(intent);
    }
}
