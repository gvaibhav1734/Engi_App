package com.example.lenovo.engineer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class EntryDialog extends DialogFragment {
    private static final String TAG = "EntryDialog";

    public EntryDialog() {

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public static EntryDialog getInstance(Entry entry) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("entry", entry);
        EntryDialog entryDialog = new EntryDialog();
        entryDialog.setArguments(bundle);
        return entryDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_entry, container, false);
        ImageView image, close;
        TextView name, time, location, committee, content, day;
        Button register;
        name = rootView.findViewById(R.id.entry_dialog_tv_name);
        time = rootView.findViewById(R.id.entry_dialog_tv_time);
        location = rootView.findViewById(R.id.entry_dialog_tv_location);
        committee = rootView.findViewById(R.id.entry_dialog_tv_committee);
        content = rootView.findViewById(R.id.entry_dialog_tv_content);
        day = rootView.findViewById(R.id.entry_dialog_tv_day);
        close = rootView.findViewById(R.id.entry_dialog_iv_close);
        image = rootView.findViewById(R.id.entry_dialog_iv_image);
        register = rootView.findViewById(R.id.entry_dialog_btn_register);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        final Entry entry = getArguments().getParcelable("entry");
        name.setText(entry.getName());
        time.setText(entry.getTime());
        day.setText(String.valueOf(entry.getDay()));
        location.setText(entry.getLocation());
        Log.d(TAG,"Committee " + entry.getCommittee());
        committee.setText(entry.getCommittee());
        content.setText(entry.getContent());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.engineer_logo)
                .error(R.drawable.engineer_logo);
        Glide.with(this)
                .load(entry.getImage())
                .apply(options)
                .into(image);
        if (entry.getRegister_event() == 1)
            register.setVisibility(View.VISIBLE);
        else
            register.setVisibility(View.GONE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getContext(), WebViewActivity.class);
                a.putExtra("link",entry.getRegister_link());
                startActivity(a);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapsFragment mapsFragment = new MapsFragment();
                Bundle bundle = new Bundle();
                String location = entry.getLocation().toLowerCase();
                /*
                 Add checks here to call the correct location

                 * name - indicates the name displayed when user clicks on marker
                 * location - is and int which is used to get desired LatLng object.
                 */
                if(location.contains("NTB")) {
                    bundle.putString("name","NTB");
                    bundle.putInt("location",MapsFragment.NTB);
                } else if(location.contains("ATB")){
                    bundle.putString("name","ATB");
                    bundle.putInt("location",MapsFragment.ATB);
                } else if(location.contains("ISTE Seminar Hall")
                        || location.contains("MB")
                        || location.contains("Main Building")
                        || location.contains("Main Seminar Hall")){
                    bundle.putString("name","Main Building");
                    bundle.putInt("location",MapsFragment.MAIN_BUILDING);
                } else if(location.contains("Pavilion")){
                    bundle.putString("name","Pavilion");
                    bundle.putInt("location",MapsFragment.PAVILION);
                } else if(location.contains("CCC")){
                    bundle.putString("name","CCC");
                    bundle.putInt("location",MapsFragment.CCC);
                } else if(location.contains("SJA")){
                    bundle.putString("name","SJA");
                    bundle.putInt("location",MapsFragment.SJA);
                } else if(location.contains("SAC")){
                    bundle.putString("name","SAC");
                    bundle.putInt("location",MapsFragment.SAC);
                } else if(location.contains("Sports Complex")){
                    bundle.putString("name","New Sports Block");
                    bundle.putInt("location",MapsFragment.NEW_SPORTS_BLOCK);
                }
                mapsFragment.setArguments(bundle);
                ((AppCompatActivity) getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_menu_fl_container, mapsFragment)
                        .addToBackStack("MapsFragment")
                        .commit();
                getDialog().dismiss();
            }
        });
        return rootView;
    }
}
