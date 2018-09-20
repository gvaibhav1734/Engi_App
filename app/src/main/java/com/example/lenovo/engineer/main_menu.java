package com.example.lenovo.engineer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


public class main_menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainMenu";
    private BottomNavigationView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_menu.this, FavSchedule.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Removing this will cause the navigation view to not respond to click events
        navigationView.bringToFront();

        View header = navigationView.getHeaderView(0);
        GoogleSignInAccount account = GoogleSignInHelper.getInstance().getAccount();

        View navView;
        navView = header.findViewById(R.id.name);
        ((TextView) navView).setText(account.getDisplayName());
        navView = header.findViewById(R.id.email);
        ((TextView) navView).setText(account.getEmail());
        navView = header.findViewById(R.id.profilePic);
        Uri imguri = account.getPhotoUrl();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this).load(imguri).apply(options).into(((ImageView) navView));

        bottomBar = findViewById(R.id.main_menu_bnv_bottom_bar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_bar_home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_menu_fl_container, new HomeFragment(), "Home")
                                .addToBackStack("Home")
                                .commit();
                        break;
                    case R.id.bottom_bar_schedule:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_menu_fl_container, new ScheduleFragment(), "Schedule")
                                .addToBackStack("Schedule")
                                .commit();
                        break;
                    case R.id.bottom_bar_maps:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_menu_fl_container, new MapsFragment(), "Maps")
                                .addToBackStack("Maps")
                                .commit();
                        break;
                }
                return true;
            }
        });
        // Set initial/default fragment
        bottomBar.setSelectedItemId(R.id.bottom_bar_home);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d(TAG, "Menu item clicked.");
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.Home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_schedule) {
            fragment = new ScheduleFragment();
        } else if (id == R.id.nav_maps) {
            fragment = new MapsFragment();
        } else if (id == R.id.nav_about) {
            fragment = new AboutFragment();
        } else if (id == R.id.committee_head) {
            fragment=new CommitteeHeadsFragment();
        } else if (id == R.id.developers) {
            fragment=new DevelopersFragment();
        } else if (id == R.id.sponsors) {
            fragment=new SponsorsFragment();
        } else if (id == R.id.logout) {
            GoogleSignInHelper.getInstance().getClient().signOut();
            Log.d(TAG, "Logout Successful");
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_menu_fl_container, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
