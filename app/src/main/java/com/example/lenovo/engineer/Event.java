package com.example.lenovo.engineer;

public class Event {
    private String title,description;
    private int thumbnail;

    public Event(String titl,String des,int thumb)
    {
        title=titl;
        description=des;
        thumbnail=thumb;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
