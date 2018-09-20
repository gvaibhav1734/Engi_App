package com.example.lenovo.engineer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

public class CommAdapter extends RecyclerView.Adapter<CommAdapter.MyViewHolder> {
    private Context mContext;
    private List<CommitteeDet> committeeDetList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public View view;

        public MyViewHolder(View view) {
            super(view);
            this.view=view;
            title = (TextView) view.findViewById(R.id.home_view_title);
            thumbnail = (ImageView) view.findViewById(R.id.home_view_thumbnail);
        }
    }
    public CommAdapter(Context context, List<CommitteeDet>committeeDetList)
    {
        this.mContext=context;
        this.committeeDetList=committeeDetList;
    }
    @NonNull
    @Override
    public CommAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.committee, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommAdapter.MyViewHolder myViewHolder, int pos) {
        final CommitteeDet committeeDet=committeeDetList.get(pos);
        myViewHolder.title.setText(committeeDet.getName());
        //Loading Committee Cover
        Glide.with(mContext).load(committeeDet.getThumbnail()).into(myViewHolder.thumbnail);
        myViewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,EventsActivity.class);
                String s=committeeDet.getName();
                intent.putExtra("title", s);
                mContext.startActivity(intent);
            }
        });
        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,EventsActivity.class);
                String s=committeeDet.getName();
                intent.putExtra("title", s);
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return committeeDetList.size();
    }
}
