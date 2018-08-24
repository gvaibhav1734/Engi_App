package com.example.lenovo.engineer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

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
        name = rootView.findViewById(R.id.entry_dialog_tv_name);
        time = rootView.findViewById(R.id.entry_dialog_tv_time);
        location = rootView.findViewById(R.id.entry_dialog_tv_location);
        committee = rootView.findViewById(R.id.entry_dialog_tv_committee);
        content = rootView.findViewById(R.id.entry_dialog_tv_content);
        day = rootView.findViewById(R.id.entry_dialog_tv_day);
        close = rootView.findViewById(R.id.entry_dialog_iv_close);
        image = rootView.findViewById(R.id.entry_dialog_iv_image);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Entry entry = getArguments().getParcelable("entry");
        name.setText(entry.getName());
        time.setText(entry.getTime());
        day.setText(String.valueOf(entry.getDay()));
        location.setText(entry.getLocation());
        committee.setText(entry.getCommittee());
        content.setText(entry.getContent());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return rootView;
    }
}
