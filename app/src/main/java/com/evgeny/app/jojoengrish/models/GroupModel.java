package com.evgeny.app.jojoengrish.models;

import java.util.ArrayList;

public class GroupModel {
    private String name;
    private ArrayList<SoundModel> sounds;

    public GroupModel(String name, ArrayList<SoundModel> sounds) {
        this.name = name;
        this.sounds = sounds;
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
