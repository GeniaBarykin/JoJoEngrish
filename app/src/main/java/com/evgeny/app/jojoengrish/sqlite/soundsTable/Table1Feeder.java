package com.evgeny.app.jojoengrish.sqlite.soundsTable;

public abstract class Table1Feeder {
    public static final String TABLE_NAME= "sounds";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "name";
    public static final String KEY_SOUND_ADDRESS = "sound_address";
    public static final String KEY_PICTURE_ADRESS = "picture_address";
    public static final String KEY_DESCRIPTION = "description";

    public static String makeContract(){
        return  "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " VARCHAR(255) UNIQUE, "
                + KEY_SOUND_ADDRESS + " INTEGER, "
                + KEY_PICTURE_ADRESS + " INTEGER, "
                + KEY_DESCRIPTION + " VARCHAR(255) "
                +");";
    }

}
