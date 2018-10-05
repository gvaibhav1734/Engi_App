package com.example.lenovo.engineer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class DevelopersFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_developers, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.developers_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Developer> developers = new ArrayList<>();
        developers.add(new Developer(
                R.drawable.developer_vaibhav,
                "Vaibhav G",
                "Android",
                "gvaibhav1734@gmail.com")
        );
        developers.add(new Developer(
                R.drawable.developer_avakash,
                "Avakash Bhat",
                "Android",
                "avakash261@gmail.com")
        );
        developers.add(new Developer(
                R.drawable.developer_tanmai,
                "Tanmai V Harish",
                "Android",
                "vvijaytanmai@gmail.com")
        );
        developers.add(new Developer(
                R.drawable.developer_animesh,
                "Animesh Kumar",
                "Android",
                "animuz111@gmail.com")
        );
        recyclerView.setAdapter(new RecyclerViewAdapter(developers));
        return rootView;
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        List<Developer> developers;

        RecyclerViewAdapter(List<Developer> developers) {
            this.developers = developers;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView image;
            public TextView name, committee, email, phone;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.developer_item_tv_name);
                committee = itemView.findViewById(R.id.developer_item_tv_committee);
                email = itemView.findViewById(R.id.developer_item_tv_email);
                //phone = itemView.findViewById(R.id.committee_heads_item_tv_phone);
                image = itemView.findViewById(R.id.developer_item_iv_image);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 1 ? 1 : 0;
        }

        @NonNull
        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = null;
            switch (viewType) {
                case 0:
                    v = layoutInflater.inflate(R.layout.developer_item_0, viewGroup, false);
                    break;
                case 1:
                    v = layoutInflater.inflate(R.layout.developer_item_1, viewGroup, false);
            }
            RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
            viewHolder.name.setText(developers.get(position).getName());
            viewHolder.committee.setText(developers.get(position).getWorkedOn());
            viewHolder.email.setText(developers.get(position).getEmail());
            //viewHolder.phone.setText(developers.get(position).getPhone());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);
            GlideApp.with(getActivity())
                    .load(developers.get(position).getImage())
                    .apply(options)
                    .into(viewHolder.image);
        }

        @Override
        public int getItemCount() {
            return developers.size();
        }

    }

    class Developer {
        private String name;
        private String workedOn;
        private String phone;
        private String email;
        private int image;

        Developer(int image, String name, String workedOn, String email) { //, String phone
            this.image = image;
            this.name = name;
            this.workedOn = workedOn;
            //this.phone = phone;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWorkedOn() {
            return workedOn;
        }

        public void setWorkedOn(String workedOn) {
            this.workedOn = workedOn;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }
}
