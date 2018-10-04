package com.example.lenovo.engineer;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Entry implements Parcelable {
    private int ID;
    private String Name;
    private String Committee;
    private String Time;
    private String Location;
    private String Image;
    private String Content;
    private String register_link;
    private int Day;
    private boolean Liked;
    private int register_event;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isLiked() {
        return Liked;
    }
    public void setLiked(boolean like)
    {
        Liked=like;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCommittee() {
        return Committee;
    }

    public void setCommittee(String committee) {
        Committee = committee;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getID());
        parcel.writeString(getName());
        parcel.writeString(getCommittee());
        parcel.writeString(getTime());
        parcel.writeString(getLocation());
        //parcel.writeString(getImage());
        parcel.writeString(getContent());
        parcel.writeInt(getDay());
        parcel.writeInt(getRegister_event());
    }

    public final static Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() {

        @Override
        public Entry createFromParcel(Parcel parcel) {
            Entry entry = new Entry();
            entry.setID(parcel.readInt());
            entry.setName(parcel.readString());
            entry.setCommittee(parcel.readString());
            entry.setTime(parcel.readString());
            entry.setLocation(parcel.readString());
            //entry.setImage(parcel.readString());
            entry.setContent(parcel.readString());
            entry.setDay(parcel.readInt());
            entry.setRegister_event(parcel.readInt());
            return entry;
        }

        @Override
        public Entry[] newArray(int i) {
            return new Entry[0];
        }
    };

    public int getRegister_event() {
        return register_event;
    }

    public void setRegister_event(int register_event) {
        this.register_event = register_event;
    }

    public String getRegister_link() {
        return register_link;
    }

    public void setRegister_link(String register_link) {
        this.register_link = register_link;
    }

    @NonNull
    @Override
    public String toString(){
        return Name+" "+register_link;
    }
}
