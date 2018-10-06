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

public class CommitteeHeadsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_committee_heads, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.committee_heads_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<CommitteeHead> committeeHeads = new ArrayList<>();
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_gaurab_raut,
                "Gaurab Raut",
                "Convenor",
                "gaurab.raut@gmail.com",
                "8123849209")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_kshitij_bhandari,
                "Kshitij Bhandari",
                "Chief Coordinator",
                "kbhandari98@gmail.com",
                "9930411090")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_kavish,
                "Kavish Ramchandani",
                "Joint Convenor",
                "krciiti@gmail.com",
                "8949696724")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_shreyansh,
                "Shreyansh",
                "Chief Coordinator",
                "shreyanshsajal17@gmail.com",
                "7903344939")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_nihar,
                "Nihar Raichada",
                "Corporate Relations and Hospitality",
                "raichada.nihar3@gmail.com",
                "8105329305")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_atul,
                "Atul Singh",
                "Technical Head",
                "atulksingh1507@gmail.com",
                "8976045880")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_abhilekh,
                "Abhilekh Shrivastava",
                "Cheif Coordinator",
                "-",
                "9071987791")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_raveesh,
                "Raveesh Sinha",
                "Workshop ",
                "sinharaveesh16@gmail.com",
                "9983563302 ")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_ved,
                "Ved Choupane",
                "Engi Press",
                "vchoop3@gmail.com",
                "8830157634")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_pradeep,
                "Pradeep Choudhary",
                "Student Hospitality",
                "pradeepchoudharyj99@gmail.com",
                "9413640133")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_yash_gangwal,
                "Yash Gangwal",
                "Associate Publicity",
                "-",
                "9130272865")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_nihar_chitnis,
                "Nihar Chitnis",
                "Associate Technical Head",
                "niharchitnis@gmail.com",
                "8951791633 ")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_ritvik_arya,
                "Ritvik Arya",
                "Associate Technical Head",
                "rtvkiz@gmail.com",
                "8989973062")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_dipanshu,
                "Dipanshu Prasad",
                "Tronix Committee",
                "tronix@nitk.edu.in",
                "8861897884")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_darshan,
                "Darshan S",
                "Mechanical Committee",
                "darshans9999@gmail.com",
                "8861832038")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_chetan,
                "Chetan Munegowda",
                "Marketing Manager",
                "chetanm66@gmail.com",
                "8892140482")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_rakesh_naga_sai,
                "Rakesh Naga Sai",
                "Civil Committee",
                "rakeshnagasai123@gmail.com",
                "9505208878")
        );
        committeeHeads.add(new CommitteeHead(
                R.drawable.image_gokul,
                "Gokul Subramanian",
                "Metamorph",
                "subramanian.gokul@gmail.com",
                "9742348710")
        );

        committeeHeads.add(new CommitteeHead(
                R.drawable.image_akhilu,
                "Akhil Udathu",
                "Comps Committee",
                "akhilu077@gmail.com",
                "9740287502")
        );
        recyclerView.setAdapter(new RecyclerViewAdapter(committeeHeads));
        return rootView;
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        List<CommitteeHead> committeeHeads;

        RecyclerViewAdapter(List<CommitteeHead> committeeHeads) {
            this.committeeHeads = committeeHeads;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView image;
            public TextView name, committee, email, phone;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.committee_heads_item_tv_name);
                committee = itemView.findViewById(R.id.committee_heads_item_tv_committee);
                email = itemView.findViewById(R.id.committee_heads_item_tv_email);
                phone = itemView.findViewById(R.id.committee_heads_item_tv_phone);
                image = itemView.findViewById(R.id.committee_heads_item_iv_image);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 1 ? 1 : 0;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = null;
            switch (viewType) {
                case 0:
                    v = layoutInflater.inflate(R.layout.committee_heads_item_0, viewGroup, false);
                    break;
                case 1:
                    v = layoutInflater.inflate(R.layout.committee_heads_item_1, viewGroup, false);
            }
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            viewHolder.name.setText(committeeHeads.get(position).getName());
            viewHolder.committee.setText(committeeHeads.get(position).getCommittee());
            viewHolder.email.setText(committeeHeads.get(position).getEmail());
            viewHolder.phone.setText(committeeHeads.get(position).getPhone());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);
            GlideApp.with(getActivity())
                    .load(committeeHeads.get(position).getImage())
                    .apply(options)
                    .into(viewHolder.image);
        }

        @Override
        public int getItemCount() {
            return committeeHeads.size();
        }

    }

    class CommitteeHead {
        private String name;
        private String committee;
        private String phone;
        private String email;
        private int image;

        CommitteeHead(int image, String name, String committee, String email, String phone) {
            this.image = image;
            this.name = name;
            this.committee = committee;
            this.phone = phone;
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

        public String getCommittee() {
            return committee;
        }

        public void setCommittee(String committee) {
            this.committee = committee;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }
}
