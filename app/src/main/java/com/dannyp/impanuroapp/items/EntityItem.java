package com.dannyp.impanuroapp.items;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class EntityItem {


    int id;
    String title;
    String description;
    String date;

    public EntityItem(int id, String title, String description, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
