package com.example.lenovo.engineer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;


import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {
    private List<Event> eventList=new ArrayList<>();
    private Eventadapter mAdapter;
    private String str1,str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras= getIntent().getExtras();
        setContentView(R.layout.activity_events);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(extras!=null)
        {
            str=extras.getString("title")+" Events";
            str1=extras.getString("title");
            getSupportActionBar().setTitle(str);
        }
        RecyclerView recyclerView=findViewById(R.id.event_recycler);
        mAdapter=new Eventadapter(getApplicationContext(),eventList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        toolbar.showOverflowMenu();
        prepareEventData();
        recyclerView.setAdapter(mAdapter);



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    private void prepareEventData()
    {
        Event event;

       if(str1.equals("Tronix"))
       {
           event=new Event("Foxhunt",getString(R.string.foxhunt),R.drawable.event_back);
           eventList.add(event);
           event=new Event("Symphony",getString(R.string.Symphony),R.drawable.event_back);
           eventList.add(event);
           event=new Event("Trade-Off",getString(R.string.Trade_off),R.drawable.event_back);
           eventList.add(event);
           event=new Event("TrailBlazer",getString(R.string.TrailBlazer),R.drawable.event_back);
           eventList.add(event);
           event=new Event("Light of Seven",getString(R.string.Light_of_seven),R.drawable.event_back);
           eventList.add(event);
           event=new Event("Automata",getString(R.string.Automata),R.drawable.event_back);
           eventList.add(event);

       }
        if(str1.equals("Mechanical"))
        {
            event=new Event("Robowars",getString(R.string.Robowars),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Bot Hockey",getString(R.string.bot_hockey),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Perfect Machine",getString(R.string.perfect_machine),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Wright Flight",getString(R.string.wright_flight),R.drawable.event_back);
            eventList.add(event);

        }
        if(str1.equals("Technites"))
        {
            event=new Event("Bot Football",getString(R.string.bot_foot),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Laser Projects",getString(R.string.laser_pro2) +
                    "\n" +
                    getString(R.string.laser_pro),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Contraption",getString(R.string.contr),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Virtual Sandbox",getString(R.string.virt_sand),R.drawable.event_back);
            eventList.add(event);

        }

        if(str1.equals("Metallurgy"))
        {
            event=new Event("MetaMagic",getString(R.string.meta_mag),R.drawable.event_back);
            eventList.add(event);
            event=new Event("MindBend",getString(R.string.mindbend),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Sem Tem Expo",getString(R.string.sem_tem),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Constarch Event",getString(R.string.constarch),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Thermite Demo",getString(R.string.thermite),R.drawable.event_back);
            eventList.add(event);
            event=new Event("GA embrittlement of AL",getString(R.string.ga),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Blast Furnace Model",getString(R.string.blast_furnce),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Foil Amalgamation",getString(R.string.foil),R.drawable.event_back);
            eventList.add(event);

        }
        if(str1.equals("Civil"))
        {
            event=new Event("Colossus",getString(R.string.col),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Mind Bend",getString(R.string.mind_bend),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Architect",getString(R.string.arch),R.drawable.event_back);
            eventList.add(event);

        }
        if(str1.equals("Reflux"))
        {
            event=new Event("Chemical Breakdown",getString(R.string.chem_break),R.drawable.event_back);
            eventList.add(event);
            event=new Event("IDP",getString(R.string.idp),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Capture",getString(R.string.capture),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Chem-E-Talks",getString(R.string.chem_e_talks),R.drawable.event_back);
            eventList.add(event);

        }
        if(str1.equals("BizWaves"))
        {
            event=new Event("GAME OF FINANCE","Finance Event",R.drawable.event_back);
            eventList.add(event);
            event=new Event("Thrive","Human Resource Event",R.drawable.event_back);
            eventList.add(event);
            event=new Event("Vipanan","Marketing Event",R.drawable.event_back);
            eventList.add(event);
            event=new Event("BizLytics","Analytics Event",R.drawable.event_back);
            eventList.add(event);
            event=new Event("Light of Seven",getString(R.string.Light_of_seven),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Automata",getString(R.string.Automata),R.drawable.event_back);
            eventList.add(event);

        }
        if(str1.equals("Comp"))
        {
            event=new Event("Inscription",getString(R.string.inscr),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Kode Kombat",getString(R.string.kode_komb),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Marathon",getString(R.string.marath),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Rectify",getString(R.string.rect),R.drawable.event_back);
            eventList.add(event);
            event=new Event("ECTF",getString(R.string.ectf),R.drawable.event_back);
            eventList.add(event);
        }
        if(str1.equals("Astro"))
        {
            event=new Event("Antariksh Attraction",getString(R.string.antar),R.drawable.event_back);
            eventList.add(event);
            event=new Event("StarWars",getString(R.string.starwars),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Interstellar",getString(R.string.Interst),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Endeavour",getString(R.string.endev),R.drawable.event_back);
            eventList.add(event);
            event=new Event("Starry Night",getString(R.string.starrynight),R.drawable.event_back);
            eventList.add(event);
        }
        mAdapter.notifyDataSetChanged();
    }
}

