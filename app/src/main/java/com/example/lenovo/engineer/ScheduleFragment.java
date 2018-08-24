package com.example.lenovo.engineer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScheduleFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ScheduleFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        viewPager = rootView.findViewById(R.id.schedule_vp_container);
        tabLayout = rootView.findViewById(R.id.schedule_tl_tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new CustomPagerAdapter(getActivity()));
        return rootView;
    }

    class CustomPagerAdapter extends PagerAdapter {
        private String titles[] = {"Day 1", "Day 2", "Day 3", "Day 4", "Day 5"};
        private int layouts[] = {R.layout.schedule_list_1, R.layout.schedule_list_2,
                R.layout.schedule_list_3, R.layout.schedule_list_4, R.layout.schedule_list_5};
        private Context context;

        CustomPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = inflater.inflate(layouts[position], container, false);
            container.addView(rootView);
            RecyclerView recyclerView = rootView.findViewById(R.id.schedule_list_rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            return rootView;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return o == view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
