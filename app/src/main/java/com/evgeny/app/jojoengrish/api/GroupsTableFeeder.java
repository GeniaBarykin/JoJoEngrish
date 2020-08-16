package com.evgeny.app.jojoengrish.api;


import com.evgeny.app.jojoengrish.R;

public class GroupsTableFeeder {
    public static final String TABLE_NAME= "groups_pictures";
    public static final String KEY_NAME = "name";
    public static final String KEY_PICTURE_ADDRESS = "picture_address";

    public static String makeContract() {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_NAME + " VARCHAR(255), "
                + KEY_PICTURE_ADDRESS + " INTEGER);";
    }

    public static void feed(DbHelper db) {
        db.postGroup(Files.JOTARO, R.drawable.jotaro_yare_daze);
        db.postGroup(Files.JOSEPH, R.drawable.joseph_nice);
        db.postGroup(Files.DIO, R.drawable.dio_kono_da);
        db.postGroup(Files.GIORNO, R.drawable.buen_giorno);
        db.postGroup(Files.ABDUL, R.drawable.abdul_yes_i_am);
        db.postGroup(Files.KIRA, R.drawable.hayato);
        db.postGroup(Files.KAKYOIN, R.drawable.kakyoin_thank_you);
        db.postGroup(Files.ROHAN, R.drawable.rohan_hey_hey);
        db.postGroup(Files.BRUNO, R.drawable.bruno_arrivederci);
        db.postGroup(Files.MUSIC, R.drawable.pillar_men);
        db.postGroup(Files.OTHER, R.drawable.speedwagon);
        db.postGroup(Files.OKUYASU, R.drawable.okuyasu_hand);
        db.postGroup(Files.MISTA, R.drawable.sex_yeehaa);



    }
}
