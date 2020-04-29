package com.evgeny.app.jojoengrish.api;

import com.evgeny.app.jojoengrish.R;

public abstract class SoundsTableFeeder {
    public static final String TABLE_NAME= "sounds";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "name";
    public static final String KEY_SOUND_ADDRESS = "sound_address";
    public static final String KEY_PICTURE_ADDRESS = "picture_address";
    public static final String KEY_DESCRIPTION = "description";

    public static String makeContract(){
        return  "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " VARCHAR(255) UNIQUE, "
                + KEY_SOUND_ADDRESS + " INTEGER, "
                + KEY_PICTURE_ADDRESS + " INTEGER, "
                + KEY_DESCRIPTION + " VARCHAR(255) "
                +");";
    }

    public static void feed(DbHelper db){
        db.postSound(Files.YARE_YARE_DAZE, R.raw.jotaro_yare_daze, R.drawable.jotaro_yare_daze,
                "When you are too tired of someone's stupidity and want a break.");
        db.postSound(Files.WRY_SCARED, R.raw.dio_wry_scared, R.drawable.dio_wry_scared,
                "Intense vampire noise.");
        db.postSound(Files.WRY_ANGRY, R.raw.dio_wry, R.drawable.dio_wry,
                "Battle cry of a true warrior.");
        db.postSound(Files.WRY_QUIET, R.raw.dio_wry_quiet, R.drawable.dio_wry_quiet,
                "Menacing voice from a coffin.");
        db.postSound(Files.BITE_THE_DUST, R.raw.kira_bite_the_dust, R.drawable.kira_bite_the_dust,
                "When you are going to reset the timeline.");
        db.postSound(Files.ROADROLLER, R.raw.dio_roadroller, R.drawable.dio_roadroller,
                "The most stylish way to finish your opponent.");
        db.postSound(Files.ZAWARUDO, R.raw.dio_zawarudo, R.drawable.dio_zawarudo,
                "It is time to stop.");



    }

}
