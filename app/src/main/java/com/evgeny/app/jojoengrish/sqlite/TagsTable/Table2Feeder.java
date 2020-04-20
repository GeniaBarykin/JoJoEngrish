package com.evgeny.app.jojoengrish.sqlite.TagsTable;

import com.evgeny.app.jojoengrish.sqlite.soundsTable.Table1Feeder;

public abstract class Table2Feeder {
    public static final String TABLE_NAME = "tags";
    public  static final String KEY_ID = "ID";
    public  static final String KEY_TAGS = "tags";

    public static String makeContract(){
        return  "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_TAGS + " VARCHAR(255), "
                + KEY_ID + " INTEGER ,"
                + "FOREIGN KEY (" + KEY_ID + ") REFERENCES "
                + Table1Feeder.TABLE_NAME + " (" + Table1Feeder.KEY_ID + ")"
                +");";
    }

}
