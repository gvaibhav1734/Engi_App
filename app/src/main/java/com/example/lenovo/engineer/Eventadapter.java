package com.example.lenovo.engineer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Eventadapter  extends RecyclerView.Adapter<Eventadapter.ViewHolder> {
    public Context mContext;
    public List<Event>event_list;
    public Eventadapter(Context context, List<Event>events)
    {
        this.mContext=context;
        this.event_list=events;
    }

    @NonNull
    @Override
    public Eventadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_row, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        final Event event=event_list.get(pos);
        viewHolder.title.setText(event.getTitle());
        viewHolder.descr.setText(event.getDescription());
        //Loading Committee Cover
        Glide.with(mContext).load(event.getThumbnail()).into(viewHolder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return event_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,descr;
        public ImageView thumbnail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail=(ImageView)itemView.findViewById(R.id.event_view_thumbnail);
            title=itemView.findViewById(R.id.event_view_title);
            descr=itemView.findViewById(R.id.event_view_desc);


        }
    }


}
