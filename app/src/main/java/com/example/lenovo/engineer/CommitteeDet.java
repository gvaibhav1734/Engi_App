package com.example.lenovo.engineer;

public class CommitteeDet {
    private String name;
    private String detailed_name;
    private int thumbnail;
    public CommitteeDet(String name, String detailed_name, int thumbnail)
    {
        this.name=name;
        this.detailed_name=detailed_name;
        this.thumbnail=thumbnail;
    }
    public CommitteeDet(String name, int thumbnail)
    {
        this.name=name;
        this.detailed_name="";
        this.thumbnail=thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetailed_name() {
        return detailed_name;
    }

    public void setDetailed_name(String detailed_name) {
        this.detailed_name = detailed_name;
    }
}
