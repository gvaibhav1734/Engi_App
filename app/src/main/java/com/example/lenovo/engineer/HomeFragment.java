package com.example.lenovo.engineer;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment{
    private static final String TAG = "HomeFragment";

    private RecyclerView recyclerView;
    private CommAdapter adapter;
    private List<CommitteeDet> committeeDetList;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] Images = {R.drawable.sunburn_nitk,
            R.drawable.final_1,
            R.drawable.final_workshop,
            R.drawable.sandbox};
    private ArrayList<Integer> ImageArray = new ArrayList<Integer>();

    public HomeFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView= rootView.findViewById(R.id.home_recycler_view);
        committeeDetList=new ArrayList<>();
        adapter=new CommAdapter(getContext(),committeeDetList);

        RecyclerView.LayoutManager mLayoutManager= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        prepCommittee();
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Engineer");

        initSlider();
    }

    private void initSlider() {
        for (int i = 0; i < Images.length; i++)
            ImageArray.add(Images[i]);

        mPager = getView().findViewById(R.id.pager);

        mPager.setAdapter(new ImageAdapter(getContext(), ImageArray));
        CircleIndicator indicator = getView().findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == Images.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);
    }

    private void prepCommittee() {
        int[] covers = new int[]
                {
                        R.drawable.tronix,
                        R.drawable.mechanical,
                        R.drawable.meta,
                        R.drawable.comps,
                        R.drawable.chemical,
                        R.drawable.mining,
                        R.drawable.civil,
                        R.drawable.technites,
                        R.drawable.heic1509a,
                        R.drawable.engi_talks_pic,
                        R.drawable.wslogo
                };
        CommitteeDet a;
            a=new CommitteeDet("Workshops",covers[10]);
        committeeDetList.add(a);
        a=new CommitteeDet("Engi Talks",covers[9]);
        committeeDetList.add(a);
        a= new CommitteeDet(getString(R.string.Tronix),covers[0]);
        committeeDetList.add(a);
        CommitteeDet c= new CommitteeDet(getString(R.string.Mech),covers[1]);
        CommitteeDet d= new CommitteeDet(getString(R.string.Meta),covers[2]);
        CommitteeDet e= new CommitteeDet(getString(R.string.Comp),covers[3]);
        CommitteeDet f= new CommitteeDet(getString(R.string.chem),covers[4]);
        CommitteeDet g= new CommitteeDet(getString(R.string.Mining),covers[8]);
        CommitteeDet h= new CommitteeDet(getString(R.string.Civil),covers[6]);
        a= new CommitteeDet(getString(R.string.Technites),covers[7]);
        committeeDetList.add(a);
        committeeDetList.add(e);
        committeeDetList.add(c);
        committeeDetList.add(d);
        committeeDetList.add(f);
        committeeDetList.add(g);
        committeeDetList.add(h);

        adapter.notifyDataSetChanged();
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
