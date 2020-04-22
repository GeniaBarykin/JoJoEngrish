package com.evgeny.app.jojoengrish.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SoundModel {
    private int ID;
    private ArrayList<String> tags;
    private int sound_adress;
    private int picture_adress;
    private String name;
    private String description = "Content will be available in the next update";

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public int getSound_adress() {
        return sound_adress;
    }

    public int getPicture_adress() {
        return picture_adress;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }




    public SoundModel(int ID, String name, int soundID, int pictureID, String description) {
        this.ID=ID;
        this.sound_adress = soundID;
        this.picture_adress = pictureID;
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
