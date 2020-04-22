package com.evgeny.app.jojoengrish.api;

import com.evgeny.app.jojoengrish.api.exceptions.NotFoundException;

public abstract class TagsTableFeeder {
    public static final String TABLE_NAME = "tags";
    public  static final String KEY_ID = "ID";
    public  static final String KEY_TAG = "tag";

    public static String makeContract(){
        return  "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_TAG + " VARCHAR(255), "
                + KEY_ID + " INTEGER ,"
                + "FOREIGN KEY (" + KEY_ID + ") REFERENCES "
                + SoundsTableFeeder.TABLE_NAME + " (" + SoundsTableFeeder.KEY_ID + ")"
                +");";
    }

    public static void feed(DbHelper db){
            int id = 1;
            db.postTag(id,"jotaro");
            db.postTag(id,"kujo");
            db.postTag(id,"yare");
            db.postTag(id,"daze");
            db.postTag(id,"good");
            db.postTag(id,"grief");
            db.postTag(id,"stardust");
            db.postTag(id,"crusaders");
    }

}
