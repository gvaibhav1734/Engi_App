package com.example.lenovo.engineer;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private CommAdapter adapter;
    private List<CommitteeDet> committeeDetList;
    private static final String TAG = "HomeActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        //Adding Drawer to layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.home_nav_view);
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
        //End of drawer


        initCollapsingToolbar();
        recyclerView=(RecyclerView) findViewById(R.id.home_recycler_view);
        committeeDetList=new ArrayList<>();
        adapter=new CommAdapter(this,committeeDetList);

        RecyclerView.LayoutManager mLayoutManager= new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        prepCommitte();
        recyclerView.setAdapter(adapter);
        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }



    private void prepCommitte() {
        int[] covers = new int[]
                {
                        R.drawable.album1,
                        R.drawable.album2,
                        R.drawable.album3,
                        R.drawable.album4,
                        R.drawable.album5,
                        R.drawable.album6,
                        R.drawable.album7,
                        R.drawable.album8,
                        R.drawable.album9,
                        R.drawable.album10,
                        R.drawable.album11
                };
        CommitteeDet a= new CommitteeDet(getString(R.string.committee1),covers[0]);
        committeeDetList.add(a);
        a= new CommitteeDet(getString(R.string.committee1),covers[1]);
        committeeDetList.add(a);
        CommitteeDet c= new CommitteeDet(getString(R.string.committee1),covers[2]);
        CommitteeDet d= new CommitteeDet(getString(R.string.committee1),covers[3]);
        CommitteeDet e= new CommitteeDet(getString(R.string.committee1),covers[4]);
        CommitteeDet f= new CommitteeDet(getString(R.string.committee1),covers[5]);
        CommitteeDet g= new CommitteeDet(getString(R.string.committee1),covers[6]);
        CommitteeDet h= new CommitteeDet(getString(R.string.committee1),covers[7]);



        committeeDetList.add(c);
        committeeDetList.add(d);
        committeeDetList.add(e);
        committeeDetList.add(f);
        committeeDetList.add(g);
        committeeDetList.add(h);

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "Menu item clicked.");
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.Home) {
            fragment = new Home();
        } else if (id == R.id.nav_schedule) {
            fragment = new ScheduleFragment();

        } else if (id == R.id.nav_maps) {
            fragment = new MapsFragment();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
