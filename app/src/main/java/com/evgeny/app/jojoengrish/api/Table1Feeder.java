package com.evgeny.app.jojoengrish.api;

public abstract class Table1Feeder {
    public static final String TABLE_NAME= "sounds";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "name";
    public static final String COLUMN_3 = "sound_address";
    public static final String COLUMN_4 = "picture_address";
    public static final String COLUMN_5 = "description";

    public static String makeContract(){
        return  "CREATE TABLE " + TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "  + COLUMN_2 + " TEXT "
                + COLUMN_3 + " INTEGER " + COLUMN_4 + " INTEGER " + COLUMN_5 + " TEXT"
                +")";
    }

}
