package com.evgeny.app.jojoengrish.models;

import java.util.ArrayList;

public class GroupModel {
    private String name;
    private ArrayList<SoundModel> sounds;

    public int getPicture_adress() {
        return picture_adress;
    }

    private int picture_adress;

    public GroupModel(String name, int picture_adress) {
        this.name = name;
        this.picture_adress = picture_adress;
    }

    public void addSounds(ArrayList<SoundModel> sounds){
        this.sounds = new ArrayList<>();
        this.sounds.addAll(sounds);
    }

    public String getName() {
        return name;
    }

    public ArrayList<SoundModel> getSounds() {
        return sounds;
    }

    public void addSound(SoundModel soundModel){
        this.sounds.add(soundModel);
    }
}
