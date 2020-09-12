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
        db.postGroupSound(Files.JOTARO, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.JOTARO, id++);
        db.postGroupSound(Files.JOTARO, id++);
        db.postGroupSound(Files.AVDUL, id++);
        db.postGroupSound(Files.KAKYOIN, id++);
        db.postGroupSound(Files.POLNAREF, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.AVDUL, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.KIRA, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.JOTARO, id++);
        db.postGroupSound(Files.OTHER, id++);
        db.postGroupSound(Files.SPEEDWAGON, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.JONATHAN, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.ZEPELI, id++);
        db.postGroupSound(Files.OTHER, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.KAKYOIN, id++);
        db.postGroupSound(Files.POLNAREF, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.JOTARO, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.OKUYASU, id++);
        db.postGroupSound(Files.OTHER, id++);
        db.postGroupSound(Files.DARBY, id++);
        db.postGroupSound(Files.BRUNO, id++);
        db.postGroupSound(Files.GIORNO, id++);
        db.postGroupSound(Files.GIORNO, id++);
        db.postGroupSound(Files.BRUNO, id++);
        db.postGroupSound(Files.DIAVOLO, id++);
        db.postGroupSound(Files.MUSIC, id++);
        db.postGroupSound(Files.ROHAN, id++);
        db.postGroupSound(Files.KIRA, id++);
        db.postGroupSound(Files.MUSIC, id++);
        db.postGroupSound(Files.DIO, id++);
        db.postGroupSound(Files.NARANCHA, id++);
        db.postGroupSound(Files.OKUYASU, id++);
        db.postGroupSound(Files.MUSIC, id);
        db.postGroupSound(Files.PILLARMEN, id);
        db.postGroupSound(Files.ROHAN, id++);
        db.postGroupSound(Files.GIORNO, id++);
        db.postGroupSound(Files.OTHER, id++);
        db.postGroupSound(Files.PILLARMEN, id++);
        db.postGroupSound(Files.GIORNO, id++);
        db.postGroupSound(Files.BRUNO, id++);
        db.postGroupSound(Files.JOSEPH, id++);
        db.postGroupSound(Files.JOTARO, id++);
        db.postGroupSound(Files.PILLARMEN, id++);
        db.postGroupSound(Files.MISTA, id++);
        db.postGroupSound(Files.MISTA, id++);
        db.postGroupSound(Files.JOTARO, id++);
        db.postGroupSound(Files.KAKYOIN, id++);
        db.postGroupSound(Files.MUSIC, id);
        db.postGroupSound(Files.GIORNO, id++);
        //11:9:2020


    }
}
