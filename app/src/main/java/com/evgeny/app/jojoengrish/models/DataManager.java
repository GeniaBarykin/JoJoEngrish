package com.evgeny.app.jojoengrish.models;
import com.evgeny.app.jojoengrish.R;

import java.util.ArrayList;

public class DataManager {


    private ArrayList<SoundModel> sounds;
    private static DataManager dataManager;

    public ArrayList<SoundModel> getSounds() {
        return sounds;
    }

    public static DataManager request(){
        if(dataManager==null){
            dataManager=new DataManager();
        }
        return dataManager;
    }
    private DataManager(){
        sounds=new ArrayList<>();
        ArrayList<String>tagsToAdd = new ArrayList<>();
        tagsToAdd.add("jotaro");
        tagsToAdd.add("kujo");
        tagsToAdd.add("yare");
        tagsToAdd.add("daze");
        tagsToAdd.add("good");
        tagsToAdd.add("grief");
        tagsToAdd.add("stardust");
        tagsToAdd.add("crusaders");
        SoundModel toAdd = new SoundModel("2mesto", tagsToAdd, R.raw.jotaroyareyaredaze,R.drawable.yare_yare_daze);
        sounds.add(toAdd);
        tagsToAdd = new ArrayList<>();
        tagsToAdd.add("jotaro");
        tagsToAdd.add("kujo");
        tagsToAdd.add("yare");
        tagsToAdd.add("yare");
        tagsToAdd.add("daze");
        tagsToAdd.add("good");
        tagsToAdd.add("grief");
        tagsToAdd.add("stardust");
        tagsToAdd.add("crusaders");
        tagsToAdd.add("m");
        toAdd = new SoundModel("jotaro_yare_daze", tagsToAdd, R.raw.jotaroyareyaredaze,R.drawable.yare_yare_daze);
        sounds.add(toAdd);
        toAdd = new SoundModel("0mesto", new ArrayList<String>(), R.raw.jotaroyareyaredaze,R.drawable.yare_yare_daze);
        sounds.add(toAdd);
    }



}
