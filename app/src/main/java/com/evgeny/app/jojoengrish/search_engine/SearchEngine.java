package com.evgeny.app.jojoengrish.search_engine;

import com.evgeny.app.jojoengrish.models.DataManager;
import com.evgeny.app.jojoengrish.models.SoundModel;

import java.util.ArrayList;

public class SearchEngine {

    public static ArrayList<SoundModel> findSoundFiles(String searchBarText) {
        ArrayList<SoundModel> sounds = DataManager.request().getSounds();
        ArrayList<SoundModel> sortedSounds = new ArrayList<>();

        searchBarText = searchBarText.toLowerCase();
        String[] tryTags = searchBarText.split(" ");
        int[] matches = new int[sounds.size()];
        int index = 0;
        int value = 0;
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = Integer.MIN_VALUE;
        for (SoundModel sound :
                sounds) {
            for (String tryTag :
                    tryTags) {
                if (sound.getName().contains(tryTag)) {
                    value++;
                }
                for (String tag :
                        sound.getTags()) {
                    if (tag.contains(tryTag)) {
                        value++;
                    }
                }
            }
            if (maxValue < value) {
                maxValue = value;
                maxIndex = sounds.indexOf(sound);
            }
            matches[index] = value;
            value = 0;
            index++;
        }
        sortedSounds.add(sounds.get(maxIndex));
        maxValue = Integer.MIN_VALUE;
        maxIndex = Integer.MIN_VALUE;

        for (int i = 0; i < sounds.size(); i++) {
            for (SoundModel sound :
                    sounds) {
                if (!sortedSounds.contains(sound)) {
                    int soundValue = matches[sounds.indexOf(sound)];
                    if (soundValue != 0 && soundValue > maxValue) {
                        maxValue = soundValue;
                        maxIndex = sounds.indexOf(sound);
                    }

                }
            }
            if (maxValue <= 0) {
                break;
            }
            sortedSounds.add(sounds.get(maxIndex));
            maxValue = Integer.MIN_VALUE;
            maxIndex = Integer.MIN_VALUE;
        }
        return sortedSounds;
    }

}
