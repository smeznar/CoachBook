package com.smeznar.coachbook.activities_and_fragments;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.smeznar.coachbook.API.DatabaseHelper;
import com.smeznar.coachbook.API.ExerciseApi;
import com.smeznar.coachbook.R;
import com.smeznar.coachbook.interfaces.IOnDatabaseChangedListener;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity {

    private ExerciseApi data;

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private FragmentManager fragmentManager;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = findViewById(R.id.drawer_layout);

        nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        configureToolbar();
        mDatabaseHelper = new DatabaseHelper(this, new IOnDatabaseChangedListener() {
            @Override
            public void onDatabaseChanged(Realm realm) {
                data.setDatabase(realm);
            }
        });
        data = new ExerciseApi(mDatabaseHelper.getDatabase());

        Fragment fragment = null;
        try{
            fragment = SelectCategoryFragment.class.newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = SelectCategoryFragment.class;
        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                fragmentClass = ProfileFragment.class;
                break;
            case R.id.nav_exercises:
                fragmentClass = SelectCategoryFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.nav_about:
                fragmentClass = AboutFragment.class;
                break;
            case R.id.nav_sign_out:
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    private void configureToolbar() {
        try{
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar;
            actionbar = getSupportActionBar();
            if (actionbar != null) {
                actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
                actionbar.setDisplayHomeAsUpEnabled(true);
            }
        } catch (Exception e){
            Log.e("ERROR Toolbar",e.getMessage());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public DatabaseHelper getDatabaseHelper() {
        return mDatabaseHelper;
    }

    public ExerciseApi getApi(){
        return data;
    }

}
