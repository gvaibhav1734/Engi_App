package com.example.lenovo.engineer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ScheduleFragment extends Fragment {
    private static final String TAG = "ScheduleFragment";
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProgressBar progressBar;
    ScheduleListAdapter day1Adapter, day2Adapter, day3Adapter, day4Adapter, day5Adapter;
    private String sharedPrefFile = "com.example.android.engineer";
    private SharedPreferences mPreferences;
    private String DEF=null;
    private boolean allowRefresh=true;

    public ScheduleFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Read Shared Preference
        mPreferences = getActivity().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        viewPager = rootView.findViewById(R.id.schedule_vp_container);
        tabLayout = rootView.findViewById(R.id.schedule_tl_tabs);
        progressBar = rootView.findViewById(R.id.schedule_list_pb_progress);
        tabLayout.setupWithViewPager(viewPager);
        day1Adapter = new ScheduleListAdapter(getActivity());
        day2Adapter = new ScheduleListAdapter(getActivity());
        day3Adapter = new ScheduleListAdapter(getActivity());
        day4Adapter = new ScheduleListAdapter(getActivity());
        //day5Adapter = new ScheduleListAdapter(getActivity());
        viewPager.setAdapter(new CustomPagerAdapter(getActivity()));
        makeRequest();

        getActivity().setTitle("Schedule");
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("OnResume", "Is in Resume");
        day1Adapter = new ScheduleListAdapter(getActivity());
        day2Adapter = new ScheduleListAdapter(getActivity());
        day3Adapter = new ScheduleListAdapter(getActivity());
        day4Adapter = new ScheduleListAdapter(getActivity());
        //day5Adapter = new ScheduleListAdapter(getActivity());
        viewPager.setAdapter(new CustomPagerAdapter(getActivity()));
        makeRequest();

    }

    public void makeRequest() {
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest listRequest = new JsonArrayRequest(
                Request.Method.GET,
                getString(R.string.EVENT_LIST_URL),
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "listRequest success Volley : " + response.toString());
                        Type listType = new TypeToken<Entry>() {
                        }.getType();
                        for (int i =0 ; i<response.length(); i++) {
                            Entry entry = new Entry();
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                entry.setDay(jsonObject.getInt("Day"));
                                entry.setID(jsonObject.getInt("ID"));
                                entry.setContent(jsonObject.getString("Content"));
                                entry.setLocation(jsonObject.getString("Location"));
                                entry.setTime(jsonObject.getString("Time"));
                                entry.setImage(
                                        jsonObject.getString("Image")
                                        .replace("\\/","/")
                                );
                                entry.setRegister_link(
                                        jsonObject.getString("register_link")
                                                .replace("\\/","/")
                                );
                                entry.setRegister_event(jsonObject.getInt("register_event"));
                                entry.setCommittee(jsonObject.getString("Committee"));
                                entry.setName(jsonObject.getString("Name"));
                                if(mPreferences.getString(String.valueOf(entry.getID()), "b").equals("b"))
                                {
                                    //Checks if the event is already liked by user
                                    entry.setLiked(false);

                                }
                                else {
                                    entry.setLiked(true);
                                }
                            } catch (JSONException error) {
                                Log.e(TAG, "JSON error " + error.getMessage());
                            }
                            switch (entry.getDay()) {
                                case 1:
                                    day1Adapter.addEntry(entry);
                                    break;
                                case 2:
                                    day2Adapter.addEntry(entry);
                                    break;
                                case 3:
                                    day3Adapter.addEntry(entry);
                                    break;
                                case 4:
                                    day4Adapter.addEntry(entry);
                                    break;
//                                case 5:
//                                    day5Adapter.addEntry(entry);
//                                    break;
                            }
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "listRequest Volley error : " + error.getMessage());
                        Snackbar.make(getView(),"Connection lost.",Snackbar.LENGTH_INDEFINITE)
                                .setAction("Retry", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        makeRequest();
                                    }
                                })
                                .show();
                    }
                }
        );
        VolleyHelper.getInstance(getContext()).addToRequestQueue(listRequest);
    }

    class CustomPagerAdapter extends PagerAdapter {
        private static final int DAY1 = 0;
        private static final int DAY2 = 1;
        private static final int DAY3 = 2;
        private static final int DAY4 = 3;
        private static final int DAY5 = 4;
        private String titles[] = {"Day 1", "Day 2", "Day 3", "Day 4"};
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
            switch (position) {
                case DAY1:
                    recyclerView.setAdapter(day1Adapter);
                    break;
                case DAY2:
                    recyclerView.setAdapter(day2Adapter);
                    break;
                case DAY3:
                    recyclerView.setAdapter(day3Adapter);
                    break;
                case DAY4:
                    recyclerView.setAdapter(day4Adapter);
                    break;
                case DAY5:
                    recyclerView.setAdapter(day5Adapter);
                    break;
            }
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
