package com.example.lenovo.engineer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {
    private List<Entry> entryList = new ArrayList<>();
    private Context context;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.engineer";

    ScheduleListAdapter(Context context) {
        this.context = context;
        mPreferences = context.getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //        Typeface font = Typeface.createFromAsset( context.getAssets(), "fontawesome-webfont.ttf" );
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fa-regular-400.ttf");
        TextView name, time, content, location;
        TextView like;


        ViewHolder(View viewHolder) {
            super(viewHolder);
            name = viewHolder.findViewById(R.id.list_item_tv_name);
            time = viewHolder.findViewById(R.id.list_item_tv_time);
            location = viewHolder.findViewById(R.id.list_item_tv_location);
            content = viewHolder.findViewById(R.id.list_item_tv_content);
            like = viewHolder.findViewById(R.id.list_item_iv_like);
            like.setTypeface(font);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.list_view_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        Typeface font1 = Typeface.createFromAsset(context.getAssets(), "fa-regular-400.ttf");
        Typeface font2 = Typeface.createFromAsset(context.getAssets(), "fa-solid-900.ttf");
        final SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        viewHolder.name.setText(entryList.get(position).getName());
        viewHolder.time.setText(entryList.get(position).getTime());
        viewHolder.location.setText(entryList.get(position).getLocation());
        viewHolder.content.setText(entryList.get(position).getContent());
        if (entryList.get(position).isLiked()) {
            viewHolder.like.setTypeface(font2);
        } else
            viewHolder.like.setTypeface(font1);
        //viewHolder.like.setText(R.string.fa_icon_heart);

//        Set liked or not liked when SharedPreferences implemented.
//        viewHolder.like.setImageResource(R.drawable.ic_not_liked);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryDialog dialogFragment =
                        EntryDialog.getInstance(entryList.get(viewHolder.getAdapterPosition()));
                dialogFragment.setCancelable(true);
                dialogFragment.show(((main_menu) context).getSupportFragmentManager(),
                        "entry_dialog");
            }
        });
        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Typeface font1 = Typeface.createFromAsset(context.getAssets(), "fa-regular-400.ttf");
                Typeface font2 = Typeface.createFromAsset(context.getAssets(), "fa-solid-900.ttf");

                if (!entryList.get(viewHolder.getAdapterPosition()).isLiked()) {
                    viewHolder.like.setTypeface(font2);
                    entryList.get(viewHolder.getAdapterPosition()).setLiked(true);
                    String json = GsonHelper.getInstance().getGson()
                            .toJson(entryList.get(viewHolder.getAdapterPosition()));
                    preferencesEditor.putString(
                            (entryList.get(viewHolder.getAdapterPosition()).getName()),
                            json
                    );
                    preferencesEditor.apply();

                } else {
                    viewHolder.like.setTypeface(font1);
                    entryList.get(viewHolder.getAdapterPosition()).setLiked(false);
                    preferencesEditor.remove(entryList.get(viewHolder.getAdapterPosition()).getName());
                    preferencesEditor.apply();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public void addEntry(Entry entry) {
        entryList.add(entry);
        notifyItemInserted(entryList.size());
    }
}
