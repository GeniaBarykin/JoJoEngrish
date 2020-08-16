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
        db.postGroup("Jotaro", R.drawable.star_zawarudo);
        db.postGroup("Joseph", R.drawable.joseph_nice);
        db.postGroup("Dio", R.drawable.dio_kono_da);
    }
}
