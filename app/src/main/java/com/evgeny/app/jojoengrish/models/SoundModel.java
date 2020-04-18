package com.evgeny.app.jojoengrish.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SoundModel {
    private ArrayList<String> tags;
    private int ID;
    private int pictureID;
    private String name;

    public ArrayList<String> getTags() {
        return tags;
    }

    public int getID() {
        return ID;
    }

    public int getPictureID() {
        return pictureID;
    }

    public String getName() {
        return name;
    }



    public SoundModel( String name, ArrayList<String> tags, int ID, int pictureID) {
        this.tags = tags;
        this.ID = ID;
        this.pictureID = pictureID;
        this.name = name;

    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
