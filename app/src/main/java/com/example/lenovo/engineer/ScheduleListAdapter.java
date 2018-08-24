package com.example.lenovo.engineer;

import android.app.Activity;
import android.content.Context;
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

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {
    private List<Entry> entryList = new ArrayList<>();
    private Context context;

    ScheduleListAdapter(Context context) {
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, time, content, location;
        ImageView like;

        ViewHolder(View viewHolder) {
            super(viewHolder);
            name = viewHolder.findViewById(R.id.list_item_tv_name);
            time = viewHolder.findViewById(R.id.list_item_tv_time);
            location = viewHolder.findViewById(R.id.list_item_tv_location);
            content = viewHolder.findViewById(R.id.list_item_tv_content);
            like = viewHolder.findViewById(R.id.list_item_iv_like);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.list_view_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.name.setText(entryList.get(position).getName());
        viewHolder.time.setText(entryList.get(position).getTime());
        viewHolder.location.setText(entryList.get(position).getLocation());
        viewHolder.content.setText(entryList.get(position).getContent());
//        Set liked or not liked when SharedPreferences implemented.
//        viewHolder.like.setImageResource(R.drawable.ic_liked);
//        viewHolder.like.setImageResource(R.drawable.ic_not_liked);
//        viewHolder.like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO: Get data from SharedPreferences and set icon appropriately.
//            }
//        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryDialog dialogFragment =
                        EntryDialog.getInstance(entryList.get(viewHolder.getAdapterPosition()));
                dialogFragment.setCancelable(true);
                dialogFragment.show(((main_menu)context).getSupportFragmentManager(),
                        "entry_dialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public void addEntry(Entry entry){
        entryList.add(entry);
        notifyItemInserted(entryList.size());
    }
}
