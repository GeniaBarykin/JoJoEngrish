package com.evgeny.app.jojoengrish.api;


public class GroupsTableFeeder {
    public static final String TABLE_NAME= "groups";
    public static final String KEY_NAME = "name";
    public static final String KEY_SOUND_ID = "sound_id";

    public static String makeContract() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_NAME + " VARCHAR(255), "
                + KEY_SOUND_ID + " INTEGER ,"
                + "FOREIGN KEY (" + KEY_SOUND_ID + ") REFERENCES "
                + SoundsTableFeeder.TABLE_NAME + " (" + SoundsTableFeeder.KEY_ID + ")"
                + ");";
    }

    public static void feed(DbHelper db) {
        int id = 1;
        db.postGroup("Jotaro", id++);
        db.postGroup("Joseph", id++);
        db.postGroup("Dio", id++);
        db.postGroup("Jotaro", id++);
        db.postGroup("Jotaro", id++);
    }
}
