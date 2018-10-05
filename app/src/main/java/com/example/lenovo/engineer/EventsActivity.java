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

    private void prepareEventData()
    {
        Event event;

        if(str1.equals("Workshops"))
        {
            event=new Event("Ethical Hacking","This workshop has been designed to help students learn Ethical Hacking",R.drawable.ethical_hacking);
            eventList.add(event);
            event=new Event("Digital Marketing and SEO","We bring you a workshop where you would get to know all the secrets behind social media marketing and analytics.\n",R.drawable.digital_marketing);
            eventList.add(event);
            event=new Event("Big Data and Hadoop","Dive into the world of open source tech Hadoop and learn the intricacies of the art!",R.drawable.bigdata);
            eventList.add(event);
            event=new Event("Bridge Design and Analysis","Learn about bridge design and analysis",R.drawable.bridge);
            eventList.add(event);
            event=new Event("Furious 5 Robotics","We present the Robotics Workshop where you get to learn all the crazy stuff, including building actual robots!",R.drawable.robotics);
            eventList.add(event);
            event=new Event("Arduino Based Quadcopter Design","Get your hands dirty by building and flying your own Arduino based Quadcopter",R.drawable.quadcoptor);
            eventList.add(event);
            event=new Event("Mercedes Benz Live Engine Training","Learn all the basics of automobile engineering, from Engine transmission and overhauling to cutting edge technology ",R.drawable.liveengine);
            eventList.add(event);
            event=new Event("Photography","Start a fulfilling creative pursuit today with our workshop on photography fundamentals",R.drawable.photography);
            eventList.add(event);
        }
        if(str1.equals("Engi Talks"))
        {
            event=new Event("Grace Samson","HR, Head of Informatica India" ,R.drawable.grace_samson);
            eventList.add(event);
            event=new Event("Dr. Pankaj Joshi","Globally renowned scientist Astrophysicist senior professor at TIFR",R.drawable.pankaj);
            eventList.add(event);
            event=new Event("Henry Menezes","Former Indian Football Team \nCEO-Western Football Assoc",R.drawable.henry_menezes);
            eventList.add(event);
            event=new Event("Natasha Doshi","Model Actress",R.drawable.event_back);
            eventList.add(event);
            event=new Event("SN Mukherjee","Retired Major General Indian Army ",R.drawable.sn_mukherjee);
            eventList.add(event);
            event=new Event("Rahul Tyagi","CEO, Luciders Indian leading Cyber Security Expert" ,R.drawable.rahul_tyagi_curr);
            eventList.add(event);
        }

       if(str1.equals("Tronix"))
       {
           event=new Event("Foxhunt",getString(R.string.foxhunt),R.drawable.foxhunt1);
           eventList.add(event);
           event=new Event("Symphony",getString(R.string.Symphony),R.drawable.symphony);
           eventList.add(event);
           event=new Event("Trade-Off",getString(R.string.Trade_off),R.drawable.tradeoff);
           eventList.add(event);
           event=new Event("TrailBlazer",getString(R.string.TrailBlazer),R.drawable.trologo);
           eventList.add(event);
           event=new Event("Light of Seven",getString(R.string.Light_of_seven),R.drawable.lightofseven);
           eventList.add(event);
           event=new Event("Automata",getString(R.string.Automata),R.drawable.automata);
           eventList.add(event);

       }
        if(str1.equals("Mechanical"))
        {
            event=new Event("Robowars",getString(R.string.Robowars),R.drawable.mech5);
            eventList.add(event);
            event=new Event("Bot Hockey",getString(R.string.bot_hockey),R.drawable.mech1);
            eventList.add(event);
            event=new Event("Perfect Machine",getString(R.string.perfect_machine),R.drawable.mech8);
            eventList.add(event);
            event=new Event("Wright Flight",getString(R.string.wright_flight),R.drawable.mech7);
            eventList.add(event);

        }
        if(str1.equals("Technites"))
        {
            event=new Event("Bot Football",getString(R.string.bot_foot),R.drawable.botfootball);
            eventList.add(event);
            event=new Event("Laser Projects",getString(R.string.laser_pro),R.drawable.laser);
            eventList.add(event);
            event=new Event("Contraption",getString(R.string.contr),R.drawable.technites1);
            eventList.add(event);
            event=new Event("Virtual Sandbox",getString(R.string.virt_sand),R.drawable.sandbox);
            eventList.add(event);

        }

        if(str1.equals("Metallurgy"))
        {
            event=new Event("MetaMagic",getString(R.string.meta_mag),R.drawable.meta);
            eventList.add(event);
            event=new Event("MindBend",getString(R.string.mindbend),R.drawable.mindbend);
            eventList.add(event);
            event=new Event("Sem Tem Expo",getString(R.string.sem_tem),R.drawable.miridae_sem_3);
            eventList.add(event);
            event=new Event("Constarch Event",getString(R.string.constarch),R.drawable.cornstarch);
            eventList.add(event);
            event=new Event("Thermite Demo",getString(R.string.thermite),R.drawable.meta);
            eventList.add(event);
            event=new Event("GA embrittlement of AL",getString(R.string.ga),R.drawable.gaal);
            eventList.add(event);
            event=new Event("Blast Furnace Model",getString(R.string.blast_furnce),R.drawable.blastfurnace);
            eventList.add(event);
            event=new Event("Foil Amalgamation",getString(R.string.foil),R.drawable.chem_lab_1);
            eventList.add(event);

        }
        if(str1.equals("Civil"))
        {
            event=new Event("Colossus",getString(R.string.col),R.drawable.colossus);
            eventList.add(event);
            event=new Event("Mind Bend",getString(R.string.mind_bend),R.drawable.mindbend);
            eventList.add(event);
            event=new Event("Architect",getString(R.string.arch),R.drawable.architect);
            eventList.add(event);

        }
        if(str1.equals("Reflux"))
        {
            event=new Event("Chemical Breakdown",getString(R.string.chem_break),R.drawable.chem_breakdown);
            eventList.add(event);
            event=new Event("IDP",getString(R.string.idp),R.drawable.chem_idp);
            eventList.add(event);
            event=new Event("Capture",getString(R.string.capture),R.drawable.chem_capture);
            eventList.add(event);
            event=new Event("Chem-E-Talks",getString(R.string.chem_e_talks),R.drawable.chem_talks);
            eventList.add(event);

        }
        if(str1.equals("BizWaves"))
        {
            event=new Event("GAME OF FINANCE","Finance Event",R.drawable.gameoffin);
            eventList.add(event);
            event=new Event("Thrive","Human Resource Event",R.drawable.thrive);
            eventList.add(event);
            event=new Event("Vipanan","Marketing Event",R.drawable.vipanan);
            eventList.add(event);
            event=new Event("BizLytics","Analytics Event",R.drawable.biz1);
            eventList.add(event);
            event=new Event("Light of Seven",getString(R.string.Light_of_seven),R.drawable.lightofseven);
            eventList.add(event);
            event=new Event("Automata",getString(R.string.Automata),R.drawable.automata);
            eventList.add(event);

        }
        if(str1.equals("Comp"))
        {
            event=new Event("Inscription",getString(R.string.inscr),R.drawable.ins);
            eventList.add(event);
            event=new Event("Kode Kombat",getString(R.string.kode_komb),R.drawable.combat);
            eventList.add(event);
            event=new Event("Marathon",getString(R.string.marath),R.drawable.mlpic);
            eventList.add(event);
            event=new Event("Rectify",getString(R.string.rect),R.drawable.rectifyimg);
            eventList.add(event);
            event=new Event("ECTF",getString(R.string.ectf),R.drawable.ctfimg);
            eventList.add(event);
        }
        if(str1.equals("Astro"))
        {
            event=new Event("Antariksh Attraction",getString(R.string.antar),R.drawable.antariksh);
            eventList.add(event);
            event=new Event("StarWars",getString(R.string.starwars),R.drawable.starwars);
            eventList.add(event);
            event=new Event("Interstellar",getString(R.string.Interst),R.drawable.interstellarfinal);
            eventList.add(event);
            event=new Event("Endeavour",getString(R.string.endev),R.drawable.endeavour);
            eventList.add(event);
            event=new Event("Starry Night",getString(R.string.starrynight),R.drawable.starrynight);
            eventList.add(event);
        }
        mAdapter.notifyDataSetChanged();
    }
}

