package com.thaliees.overlaydemo.Models;

public class InformationModel {
    public String title;
    public String date;
    public String image;
    public String description;
    public String tag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public InformationModel(String title, String date, String image, String description, String tag) {
        this.title = title;
        this.date = date;
        this.image = image;
        this.description = description;
        this.tag = tag;
    }
}
