package com.evgeny.app.jojoengrish.search_engine;

import com.evgeny.app.jojoengrish.models.DataManager;
import com.evgeny.app.jojoengrish.models.SoundModel;

import java.util.ArrayList;

public class SearchEngine {

    public static ArrayList<SoundModel> findSoundFiles(String searchBarText){
        ArrayList<SoundModel> sounds = DataManager.request().getSounds();
        ArrayList<SoundModel> sortedSounds = new ArrayList<>();

        searchBarText=searchBarText.toLowerCase();
        String[] tryTags = searchBarText.split(" ");
        int[]matches =new int[sounds.size()];
        int index = 0;
        int value =0;
        for (SoundModel sound :
                sounds) {
            for (String tryTag :
                    tryTags) {
                if(tryTag.equals(sound.getName())){
                    value++;
                }
                for (String tag :
                        sound.getTags()) {
                    if(tryTag.equals(tag)){
                        value++;
                    }
                }
            }
            matches[index]=value;
            value=0;
            index++;
        }
        int []sortedIndexes =sortIndexes(matches,sounds.size());
        for (int i = 0; i < tryTags.length; i++) {
            int currentValue = matches[sortedIndexes[i]];
            if(currentValue!=0) {
                sortedSounds.add(sounds.get(sortedIndexes[i]));
            }
        }
        return sortedSounds;
    }

    private static int[]sortIndexes (int[] matches, int size){
        int [] sortedIndexes = new int [size];
        int max = 0;
        int maxID = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(matches[j]>max && !contatins(sortedIndexes,matches[j])){
                    max=matches[j];
                    maxID=j;
                }
            }
            sortedIndexes[i]= maxID;
            maxID=0;
        }
        return sortedIndexes;

    }

    private static boolean contatins(int [] array, int value){
        for (int i = 0; i < array.length; i++) {
            if(array[i]==value){
                return true;
            }
        }
        return false;

    }
}
