package com.evgeny.app.jojoengrish.sqlite;

import com.evgeny.app.jojoengrish.R;
import com.evgeny.app.jojoengrish.sqlite.DbHelper;

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

    public static boolean feed(DbHelper db){
        return db.postSound("jotaro_yare_daze", R.raw.jotaroyareyaredaze, R.drawable.yare_yare_daze,"Content will be available in the next update");
    }

}
