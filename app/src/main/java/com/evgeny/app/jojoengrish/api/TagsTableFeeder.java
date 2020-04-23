package com.evgeny.app.jojoengrish.api;

import com.evgeny.app.jojoengrish.api.exceptions.NotFoundException;

public abstract class TagsTableFeeder {
    public static final String TABLE_NAME = "tags";
    public static final String KEY_ID = "ID";
    public static final String KEY_TAG = "tag";

    public static String makeContract() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_TAG + " VARCHAR(255), "
                + KEY_ID + " INTEGER ,"
                + "FOREIGN KEY (" + KEY_ID + ") REFERENCES "
                + SoundsTableFeeder.TABLE_NAME + " (" + SoundsTableFeeder.KEY_ID + ")"
                + ");";
    }

    public static void feed(DbHelper db) throws NotFoundException {
        int id = db.getSoundId("Jotaro yare daze");
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "yare");
        db.postTag(id, "daze");
        db.postTag(id, "good");
        db.postTag(id, "grief");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        id++;
        id = db.getSoundId("Dio WRYYY");
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "wryyyyyyy");
        db.postTag(id, "phantom blood");
        db.postTag(id, "vampire");
        db.postTag(id, "noise");
        db.postTag(id, "loudly");
        db.postTag(id, "fight");
        id++;
        id = db.getSoundId("Dio wryyy (quiet)");
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "wryyyyyyy");
        db.postTag(id, "phantom blood");
        db.postTag(id, "vampire");
        db.postTag(id, "noise");
        db.postTag(id, "quietly");
        db.postTag(id, "coffin");
        db.postTag(id, "menacing");
        id = db.getSoundId("Bite the dust");
        db.postTag(id, "kira");
        db.postTag(id, "yoshikage");
        db.postTag(id, "killer");
        db.postTag(id, "queen");
        db.postTag(id, "bite");
        db.postTag(id, "dust");
        db.postTag(id, "reset");
        db.postTag(id, "time");
        db.postTag(id, "dusto");



    }

}
