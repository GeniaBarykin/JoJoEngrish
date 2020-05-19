package com.evgeny.app.jojoengrish.api;

import com.evgeny.app.jojoengrish.R;

public abstract class SoundsTableFeeder {
    public static final String TABLE_NAME= "sounds";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "name";
    public static final String KEY_SOUND_ADDRESS = "sound_address";
    public static final String KEY_PICTURE_ADDRESS = "picture_address";
    public static final String KEY_DESCRIPTION = "description";

    public static String makeContract(){
        return  "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " VARCHAR(255) UNIQUE, "
                + KEY_SOUND_ADDRESS + " INTEGER, "
                + KEY_PICTURE_ADDRESS + " INTEGER, "
                + KEY_DESCRIPTION + " VARCHAR(255) "
                +");";
    }

    public static void feed(DbHelper db){
        db.postSound(Files.YARE_YARE_DAZE, R.raw.jotaro_yare_daze, R.drawable.jotaro_yare_daze,
                "When you are too tired of someone's stupidity and want a break.");
        db.postSound(Files.NICE, R.raw.joseph_nice, R.drawable.joseph_nice,
                "Sneaky Joseph takes a peek through the keyhole.");
        db.postSound(Files.WRY_SCARED, R.raw.dio_wry_scared, R.drawable.dio_wry_scared,
                "Intense vampire noise.");
        db.postSound(Files.NO_NO, R.raw.jotaro_no_no, R.drawable.jotaro_no_no,
                "Will he punch you with his left arm? Maybe with his right?");
        db.postSound(Files.YES_YES, R.raw.jotaro_yes_yes, R.drawable.jotaro_yes_yes,
                "\" Honey, to you want to watch a movie with me?\"");
        db.postSound(Files.YES_I_AM, R.raw.abdul_yes_i_am, R.drawable.abdul_yes_i_am,
                "Muhammad Avdol?!?!?!");
        db.postSound(Files.THANK_YOU, R.raw.kakyoin_thank_you, R.drawable.kakyoin_thank_you,
                "Very polite cherry lover.");
        db.postSound(Files.BRAVO, R.raw.polnareff_bravo, R.drawable.polnareff_bravo,
                "Congratulations!");
        db.postSound(Files.BYE_JOJO, R.raw.dio_goodbye_jojo, R.drawable.dio_goodbye_jojo,
                "See you later, alligator.");
        db.postSound(Files.WRY_ANGRY, R.raw.dio_wry, R.drawable.dio_wry,
                "Battle cry of a true warrior.");
        db.postSound(Files.HELL_TO_YOU, R.raw.abdul_hell_to_you, R.drawable.abdul_hell_to_you,
                "The iconic line.");
        db.postSound(Files.WRY_QUIET, R.raw.dio_wry_quiet, R.drawable.dio_wry_quiet,
                "Menacing voice from a coffin.");
        db.postSound(Files.BITE_THE_DUST, R.raw.kira_bite_the_dust, R.drawable.kira_bite_the_dust,
                "When you are going to reset the timeline.");
        db.postSound(Files.ROADROLLER, R.raw.dio_roadroller, R.drawable.dio_roadroller,
                "The most stylish way to finish your opponent.");
        db.postSound(Files.ZAWARUDO, R.raw.dio_zawarudo, R.drawable.dio_zawarudo,
                "It is time to stop.");
        db.postSound(Files.MUDA_MUDA, R.raw.dio_muda_muda, R.drawable.dio_muda_muda,
                "Useless, useless, USELESS!");
        db.postSound(Files.STAND_POWER, R.raw.dio_stand_power, R.drawable.dio_stand_power,
                "The ultimate power of a stand that controls time.");
        db.postSound(Files.KONO_DIO_DA, R.raw.dio_kono_da, R.drawable.dio_kono_da,
                "Do you want to know, who stole ur gf's first kiss? I was me! Dio!");
        db.postSound(Files.JOTARO_UNDERSTAND, R.raw.jotaro_do_you_understand, R.drawable.jotaro_do_you_understand,
                "Reply to the defeated enemy with his own line. Flex.");
        db.postSound(Files.UNDERSTAND, R.raw.ruber_soul_understand, R.drawable.ruber_soul_understand,
                "When you think you are going to win and want to brag with your english skills.");
        db.postSound(Files.SPEEDWAGON, R.raw.speedwagon, R.drawable.speedwagon,
                "It is hard to resist when they call you like this.");
        db.postSound(Files.VERY_NICE, R.raw.joseph_very_nice, R.drawable.joseph_very_nice,
                "Good job Ceasar-chan.");
        db.postSound(Files.DIOOOO, R.raw.jonathan_dio, R.drawable.jonathan_dio,
                "Hate intensifies.");
        db.postSound(Files.OH_MY_GOD, R.raw.joseph_oh_my_god, R.drawable.joseph_oh_my_god,
                "Old Joseph got a problem!");
        db.postSound(Files.YES_OH_MY_GOD, R.raw.joseph_yes_oh_my_god, R.drawable.joseph_yes_oh_my_god,
                "Flexing back on the defeated enemy.");
        db.postSound(Files.OH_NO, R.raw.joseph_oh_no, R.drawable.joseph_oh_no,
                "Something is wrong. I can feel it.");
        db.postSound(Files.HOLY_SHIT, R.raw.joseph_holy_shit, R.drawable.joseph_holy_shit,
                "The problem just got bigger.");
        db.postSound(Files.SON_OF_A_BITCH, R.raw.joseph_son_of_a_bitch, R.drawable.joseph_son_of_a_bitch,
                "The disappointment is immeasurable and the day is ruined.");
        db.postSound(Files.HEY_BABY, R.raw.zeppeli_hey_baby, R.drawable.zeppeli_hey_baby,
                "Baron Zeppeli nails it.");
        db.postSound(Files.YOU_FOOL, R.raw.you_fool, R.drawable.you_fool,
                "German science is the best in the world!.");
        db.postSound(Files.SHIZA, R.raw.joseph_shiza, R.drawable.joseph_shiza,
                "RIP, my friend.");
        db.postSound(Files.LERO, R.raw.kakyoin_lero, R.drawable.kakyoin_lero,
                "Bro, do not eat your cherry like that!");
        db.postSound(Files.PEROLO, R.raw.alessi_perolo, R.drawable.alessi_perolo,
                "Here is Johny!");
        db.postSound(Files.RUN, R.raw.joseph_run, R.drawable.joseph_run,
                "Joseph's trump card.");
        db.postSound(Files.ORA_ORA, R.raw.jotaro_ora_ora, R.drawable.jotaro_ora_ora,
                "Ora ora the shit out of you.");
        db.postSound(Files.MUDA_MUDA_FIGHT, R.raw.dio_muda_fight, R.drawable.dio_muda_fight,
                "Muda muda your face.");
        db.postSound(Files.KAKOYIN, R.raw.joseph_kakoyin, R.drawable.joseph_kakoyin,
                "He is freaking dead!");
        db.postSound(Files.PRETTY, R.raw.okuyasu_pretty, R.drawable.okuyasu_pretty,
                "When you friends mom looks good.");
        db.postSound(Files.KILL_DA_HOE, R.raw.koichi_kill_da_hoe, R.drawable.koichi_kill_da_hoe,
                "Beetch!");
        db.postSound(Files.GO_AHEAD, R.raw.darby_go_ahead, R.drawable.darby_go_ahead,
                "When you have all the cards to crush the opponent.");
        db.postSound(Files.ARRIVIDERCI, R.raw.bruno_arrivederci, R.drawable.bruno_arrivederci,
                "Have a good day in your afterlife!");
        db.postSound(Files.GIORNO_MUDA, R.raw.giorno_muda, R.drawable.giorno_muda,
                "Those kids grow so fast nowadays.");
        db.postSound(Files.GIORNO_DREAM, R.raw.giorno_dream, R.drawable.giorno_dream,
                "\"Giornos theme intensifies.\"");
        db.postSound(Files.STICKY_FINGERS, R.raw.bruno_sticky_fingers, R.drawable.bruno_sticky_fingers,
                "When you are in a toilet and want to unzip ur pants.");
        db.postSound(Files.FAKE_PHONE, R.raw.diavolo_telephone, R.drawable.diavolo_telephone,
                "Ringtone new meta.");
        db.postSound(Files.SONOCHIDO, R.raw.sonochido, R.drawable.sonochido,
                "They are bounded by fate. Google translate (c)");
        db.postSound(Files.IREFUSE, R.raw.irefuse, R.drawable.irefuse,
                "When your mom asks you for a dinner while you have a serious game.");
        db.postSound(Files.HAYATO, R.raw.hayato, R.drawable.hayato,
                "Who is your daddy?");
        db.postSound(Files.CONTINUED, R.raw.to_be_continued, R.drawable.to_be_continued,
                "At the most interesting moment!");




    }

}
