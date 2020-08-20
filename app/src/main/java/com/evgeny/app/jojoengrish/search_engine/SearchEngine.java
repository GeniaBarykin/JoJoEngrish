package com.evgeny.app.jojoengrish.search_engine;

import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.api.Files;
import com.evgeny.app.jojoengrish.models.SoundModel;

import java.util.ArrayList;

public abstract class SearchEngine {

    public static ArrayList<SoundModel> findSoundFiles(String searchBarText, DbHelper db) {
        if(searchBarText==null){
            ArrayList<SoundModel> soundModels = new ArrayList<>();
            soundModels.add(Files.PEWDIE);
            return soundModels;
        }
        ArrayList<String> tagsWeNeed = new ArrayList<>();
        searchBarText = searchBarText.toLowerCase();
        String[] tryTags = searchBarText.split(" ");
        for (String tryTag :
                tryTags) {
            tagsWeNeed.addAll(filterTags(tryTag,db));
        }
        ArrayList<SoundModel> foundSounds = new ArrayList<>();
        for (String tag :
                tryTags) {
            ArrayList<SoundModel> soundsByTag = db.getSoundsByTagASC(tag);
            for (SoundModel soundByTag :
                    soundsByTag) {
                if(foundSounds.isEmpty()){
                    foundSounds.add(soundByTag);
                }
                if(!hasElement(foundSounds,soundByTag)){
                    if(foundSounds.get(foundSounds.size()-1).getName().compareTo(soundByTag.getName())<=0){
                        foundSounds.add(soundByTag);
                    } else {
                        int end = foundSounds.size()-2;
                        for (int i = 0; i < end; i++) {
                            if (foundSounds.get(i).getName().compareTo(soundByTag.getName()) >= 0) {
                                foundSounds.add(i,soundByTag);
                            }
                        }
                    }
                }
            }
        }
        return foundSounds;
    }

    private static boolean hasElement(ArrayList<SoundModel> array,SoundModel element){
        for (SoundModel sound :
                array) {
            if(sound.getName().equals(element.getName())){
                return true;
            }
        }
        return false;
    }

    private static  ArrayList<String> filterTags(String tagToLook, DbHelper db){
        ArrayList<String> allTags = db.getAllTags();
        ArrayList<String> someTags = new ArrayList<>();
        for (String tag :
                allTags) {
            if(tag.contains(tagToLook)){
                someTags.add(tag);
            }
        }
        return someTags;
    }

}
