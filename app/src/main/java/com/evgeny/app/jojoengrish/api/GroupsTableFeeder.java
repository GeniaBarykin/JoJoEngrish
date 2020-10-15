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
        db.postGroup(Files.DIO, R.drawable.dio_kono_da);
        db.postGroup(Files.JOSEPH, R.drawable.joseph_nice);
        db.postGroup(Files.GIORNO, R.drawable.buen_giorno);
        db.postGroup(Files.KIRA, R.drawable.hayato);

        db.postGroup(Files.AVDUL, R.drawable.abdul_yes_i_am);
        db.postGroup(Files.KAKYOIN, R.drawable.kakyoin_thank_you);
        db.postGroup(Files.ROHAN, R.drawable.rohan_hey_hey);
        db.postGroup(Files.BRUNO, R.drawable.bruno_arrivederci);
        db.postGroup(Files.MUSIC, R.drawable.giorno_theme);

        db.postGroup(Files.OTHER, R.drawable.shoku);
        db.postGroup(Files.OKUYASU, R.drawable.okuyasu_presenting);
        db.postGroup(Files.MISTA, R.drawable.mista_face);
        db.postGroup(Files.POLNAREFF, R.drawable.polnareff_holhorse_laughting);
        db.postGroup(Files.PHANTOM, R.drawable.dio_kono_da);

        db.postGroup(Files.BATTLE, R.drawable.joseph_shiza);
        db.postGroup(Files.STARDUST, R.drawable.dio_roadroller);
        db.postGroup(Files.DIAMOND, R.drawable.josuke_okuyasu);
        db.postGroup(Files.GOLD, R.drawable.giorno_this_is_requiem);
        db.postGroup(Files.HOLHORSE, R.drawable.polnareff_holhorse_run);

        db.postGroup(Files.TRISH, R.drawable.trish_no_hurt);
        db.postGroup(Files.JOSUKE, R.drawable.josuke_bastard);
        db.postGroup(Files.NARANCIA, R.drawable.narancia_volare_via);
        db.postGroup(Files.DIAVOLO, R.drawable.diavolo_king_crimson_calm);
        db.postGroup(Files.ENGRISH, R.drawable.f_mega);

        db.postGroup(Files.DARBY, R.drawable.darby_go_ahead);
        db.postGroup(Files.SPEEDWAGON, R.drawable.speedwagon_look_time);
        db.postGroup(Files.JONATHAN, R.drawable.jonathan_dio);
        db.postGroup(Files.ZEPELI, R.drawable.zeppeli_hey_baby);
        db.postGroup(Files.PILLARMEN, R.drawable.pillar_men);

        db.postGroup(Files.KOICHI, R.drawable.koichi_act_three);







    }
}
