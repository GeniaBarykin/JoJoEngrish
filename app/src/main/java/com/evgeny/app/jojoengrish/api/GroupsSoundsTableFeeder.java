package com.evgeny.app.jojoengrish.api;


public class GroupsSoundsTableFeeder {
    public static final String TABLE_NAME= "groups_and_sounds";
    public static final String KEY_NAME = "group_name";
    public static final String KEY_SOUND_ID = "sound_id";

    public static String makeContract() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_NAME + " VARCHAR(255), "
                + KEY_SOUND_ID + " INTEGER, "
                + "FOREIGN KEY (" + KEY_NAME+ ") REFERENCES "
                + GroupsTableFeeder.TABLE_NAME + " (" + GroupsTableFeeder.KEY_NAME + "), "
                + "FOREIGN KEY (" + KEY_SOUND_ID + ") REFERENCES "
                + SoundsTableFeeder.TABLE_NAME + " (" + SoundsTableFeeder.KEY_ID + ")"
                + ");";
    }

    public static void feed(DbHelper db) {
        int id = 1;
        db.postGroupSound("Jotaro", id++);
        db.postGroupSound("Joseph", id++);
        db.postGroupSound("Dio", id++);
        db.postGroupSound("Jotaro", id++);
        db.postGroupSound("Jotaro", id++);
    }
}
