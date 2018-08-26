package com.example.lenovo.engineer;

import android.os.Parcel;
import android.os.Parcelable;

public class Entry implements Parcelable {
    private int ID;
    private String Name;
    private String Committee;
    private String Time;
    private String Location;
    private String Image;
    private String Content;
    private int Day;
    private boolean Liked;

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
            return entry;
        }

        @Override
        public Entry[] newArray(int i) {
            return new Entry[0];
        }
    };
}
