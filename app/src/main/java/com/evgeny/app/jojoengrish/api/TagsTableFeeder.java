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
        int id = db.getSoundId(Files.YARE_YARE_DAZE);
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "yare");
        db.postTag(id, "daze");
        db.postTag(id, "good");
        db.postTag(id, "grief");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        id = db.getSoundId(Files.WRY_SCARED);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "wryyyyyyy");
        db.postTag(id, "phantom blood");
        db.postTag(id, "vampire");
        db.postTag(id, "noise");
        db.postTag(id, "loudly");
        db.postTag(id, "fight");
        db.postTag(id, "scared");
        db.postTag(id, "confused");
        id = db.getSoundId(Files.WRY_ANGRY);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "wryyyyyyy");
        db.postTag(id, "phantom blood");
        db.postTag(id, "vampire");
        db.postTag(id, "noise");
        db.postTag(id, "loudly");
        db.postTag(id, "fight");
        db.postTag(id, "angry");
        id = db.getSoundId(Files.WRY_SCARED);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "wryyyyyyy");
        db.postTag(id, "phantom blood");
        db.postTag(id, "vampire");
        db.postTag(id, "noise");
        db.postTag(id, "quietly");
        db.postTag(id, "coffin");
        db.postTag(id, "menacing");
        id = db.getSoundId(Files.BITE_THE_DUST);
        db.postTag(id, "kira");
        db.postTag(id, "yoshikage");
        db.postTag(id, "killer");
        db.postTag(id, "queen");
        db.postTag(id, "bite");
        db.postTag(id, "dust");
        db.postTag(id, "reset");
        db.postTag(id, "time");
        db.postTag(id, "dusto");
        id = db.getSoundId(Files.ROADROLLER);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "time");
        db.postTag(id, "stop");
        db.postTag(id, "road");
        db.postTag(id, "roller");
        db.postTag(id, "da");
        id = db.getSoundId(Files.ZAWARUDO);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "time");
        db.postTag(id, "stop");
        db.postTag(id, "the");
        db.postTag(id, "world");
        db.postTag(id, "za");
        db.postTag(id, "warudo");




    }

}
