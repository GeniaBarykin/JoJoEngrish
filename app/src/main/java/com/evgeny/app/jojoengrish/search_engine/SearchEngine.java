package com.evgeny.app.jojoengrish.search_engine;

import com.evgeny.app.jojoengrish.api.DbHelper;
import com.evgeny.app.jojoengrish.api.exceptions.NotFoundException;
import com.evgeny.app.jojoengrish.models.SoundModel;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class SearchEngine {

    public static ArrayList<SoundModel> findSoundFiles(String searchBarText, DbHelper db) {

        ArrayList<String> tagsWeNeed = new ArrayList<>();
        searchBarText = searchBarText.toLowerCase();
        String[] tryTags = searchBarText.split(" ");
        for (String tryTag :
                tryTags) {
            tagsWeNeed.addAll(filterTags(tryTag,db));
        }
        HashMap<Integer, Integer> foundIdsValues = new HashMap<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (String tag :
                tagsWeNeed) {
            ArrayList<Integer> newFoundIds = db.getIdByTag(tag);
            for (int id :
                    newFoundIds) {
                if(foundIdsValues.get(id)==null){
                    foundIdsValues.put(id, 1);
                    ids.add(id);
                } else {
                    foundIdsValues.put(id, foundIdsValues.get(id)+1);
                }
            }
        }
        ArrayList<SoundModel> foundSounds = new ArrayList<>();
        int amountOfValues = foundIdsValues.size();
        for (int i = 0; i < amountOfValues; i++) {
            int maxValue = Integer.MIN_VALUE;
            int maxIndex = Integer.MIN_VALUE;
            for (int j = 0; j < ids.size(); j++) {
                int value = foundIdsValues.get(ids.get(j));
                if(value>maxValue){
                    maxValue=value;
                    maxIndex = j;
                }
            }
            try {
                foundSounds.add(db.getSound(ids.get(maxIndex)));
                ids.remove(maxIndex);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return foundSounds;
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
