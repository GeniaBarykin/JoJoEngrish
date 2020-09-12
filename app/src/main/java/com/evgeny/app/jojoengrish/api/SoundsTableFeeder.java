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
        db.postSound(Files.SPEEDWAGON_NAZI, R.raw.speedwagon, R.drawable.speedwagon,
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
        db.postSound(Files.KAKOYIN_DEATH, R.raw.joseph_kakoyin, R.drawable.joseph_kakoyin,
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
        db.postSound(Files.TIMESTOP, R.raw.dio_tokiwo_tomare, R.drawable.dio_tokiwo_tomare,
                "TOKI WO TOMARE!");
        db.postSound(Files.GOFLYING, R.raw.narancia_volare_via, R.drawable.narancia_volare_via,
                "VOLARE VIA! <Plane sounds on the background>");
        db.postSound(Files.THEHANDSOUND, R.raw.okuyasu_hand, R.drawable.okuyasu_hand,
                "<Sound of Erasing>");
        db.postSound(Files.PILLAR, R.raw.pillar_men, R.drawable.pillar_men,
                "The music of gods");
        db.postSound(Files.HEYHEY, R.raw.rohan_hey_hey, R.drawable.rohan_hey_hey,
                "Rohan's suspense intensifies.");
        db.postSound(Files.SEVEN, R.raw.seven_page_muda, R.drawable.seven_page_muda,
                "As you requested, my dear users <3");
        //17.7.2020
        db.postSound(Files.BOINGO_LAUGH, R.raw.boingo_laughting,R.drawable.boingo_laughting,
                "Are you choking?");
        db.postSound(Files.KARS_LAUGH, R.raw.kars_laughting,R.drawable.kars_laughting,
                "As a true villain.");
        db.postSound(Files.BUEN_GIORNO, R.raw.buen_giorno,R.drawable.buen_giorno,
                "Oh, you are an Italian, I see.");
        db.postSound(Files.STICKY_FIGHT, R.raw.bruno_sticky_fingaaas,R.drawable.bruno_sticky_fingaaas,
                "Intense combat.");
        db.postSound(Files.HELP_ME_OMG, R.raw.help_me_oh_my_god,R.drawable.help_me_oh_my_god,
                "No homo.");
        db.postSound(Files.KONO_POWAH, R.raw.jotaro_kono_powa,R.drawable.jotaro_kono_powa,
                "Jotaro has a hard time.");
        db.postSound(Files.KARS_GUITAR, R.raw.kars_guitar,R.drawable.kars_guitar,
                "What a talent!");
        db.postSound(Files.PAS_PAS, R.raw.sex_pas_pas,R.drawable.sex_pas_pas,
                "The football team in action.");
        db.postSound(Files.YEHA, R.raw.sex_yeehaa,R.drawable.sex_yeehaa,
                "A cowboy saddles a bullet.");
        db.postSound(Files.STAR_WARUDO, R.raw.star_zawarudo,R.drawable.star_zawarudo,
                "Plagiarism from Star Platinum.");
        db.postSound(Files.EMERALDO_SPLASH, R.raw.emeraldo_splash,R.drawable.emeraldo_splash,
                "And... he died.");
        db.postSound(Files.GIORNO_THEME, R.raw.giorno_theme,R.drawable.giorno_theme,
                "Epic comeback intensifies");
        //12.9.2020
        db.postSound(Files.AVDOL_TSK, R.raw.avdol_tsk,R.drawable.avdol_tsk,
                "Not so fast");
        db.postSound(Files.BAND_ENEMY, R.raw.band_enemy,R.drawable.band_enemy,
                "Wanna join?");
        db.postSound(Files.BASEBALL, R.raw.baseball,R.drawable.baseball,
                "Cybersport intensifies");
        db.postSound(Files.BRUNO_LIAR, R.raw.bruno_liar,R.drawable.bruno_liar,
                "Natural polygraph");
        db.postSound(Files.CROSS_SPLIT, R.raw.cross_split_attack,R.drawable.cross_split_attack,
                "You fell for it, fool!");
        db.postSound(Files.DARBY_CANT, R.raw.darby_cant,R.drawable.darby_cant,
                "Wanna call the lawyer, but cant");
        db.postSound(Files.DIA_ALREADY, R.raw.diavolo_already,R.drawable.diavolo_already,
                "That stand is so op!");
        db.postSound(Files.DIA_DARE, R.raw.diavolo_dare,R.drawable.diavolo_dare,
                "You dare talk to me with that tone of voice, boy?!");
        db.postSound(Files.DIA_KC, R.raw.diavolo_king_crimson,R.drawable.diavolo_king_crimson,
                "When you hear it, you are going to die");
        db.postSound(Files.DIA_KC_CALM, R.raw.diavolo_king_crimson_calm,R.drawable.diavolo_king_crimson_calm,
                "He is very proud of his stand");
        db.postSound(Files.DIA_RESUME, R.raw.diavolo_resume,R.drawable.diavolo_resume,
                "I feel like I know this line from someone else...");
        db.postSound(Files.DIA_AWAY, R.raw.diavolo_stay_away,R.drawable.diavolo_stay_away,
                "Now he is scared like a chicken");
        db.postSound(Files.DIO_OHO, R.raw.dio_oh_ho,R.drawable.dio_oh_ho,
                "When someone is approaching you");
        db.postSound(Files.PIL_CRY, R.raw.eisidisi_cry,R.drawable.eisidisi_cry,
                "Like a baby");
        db.postSound(Files.MEGA, R.raw.f_mega,R.drawable.f_mega,
                "Epic game. 10 out of 10");
        db.postSound(Files.GIO_GOLD_EXP, R.raw.giorno_gold_expirience,R.drawable.giorno_gold_expirience,
                "It is like rain, but experience");
        db.postSound(Files.GIO_REQUIEM, R.raw.giorno_this_is_requiem,R.drawable.giorno_gold_expirience,
                "The end of the villain.");
        db.postSound(Files.JOSEPH_TEQUILA, R.raw.joseph_tequila,R.drawable.joseph_tequila,
                "The master of disguise");
        db.postSound(Files.JOSUKE_BASTARD, R.raw.josuke_bastard,R.drawable.josuke_bastard,
                "Oh no. You should not talk about his hair in this way");
        db.postSound(Files.JOSUKE_GREAT, R.raw.josuke_great,R.drawable.josuke_great,
                "Very nice");
        db.postSound(Files.JOSUKE_OKUYASU, R.raw.josuke_okuyasu,R.drawable.josuke_okuyasu,
                "Worrying about the bro");
        db.postSound(Files.JOTARO_BURNING, R.raw.jotaro_burning,R.drawable.jotaro_burning,
                "Baby on fire!");
        db.postSound(Files.JOTARO_GROAN, R.raw.jotaro_groaning,R.drawable.jotaro_groaning,
                "The life is hard");
        db.postSound(Files.JOTARO_SCUM, R.raw.jotaro_scum,R.drawable.jotaro_scum,
                "And it is ok to ora ora you");
        db.postSound(Files.JOTARO_SHUTUP, R.raw.jotaro_shut_up,R.drawable.jotaro_shut_up,
                "The badass way to talk with your mother");
        db.postSound(Files.JOTARO_FINGER, R.raw.jotaro_star_finger,R.drawable.jotaro_star_finger,
                "Makes your wife happy");
        db.postSound(Files.KAKYOIN_DEFLECT, R.raw.kakyoin_deflect,R.drawable.kakyoin_deflect,
                "Impossibru!");
        db.postSound(Files.KIRA_ERECTION, R.raw.kira_erection,R.drawable.kira_erection,
                "The art is horny");
        db.postSound(Files.KIRA_JOIN, R.raw.kira_join,R.drawable.kira_join,
                "Johny Johny? Yes papa? Can I join? WTF?");
        db.postSound(Files.MISTA_FACE, R.raw.mista_face,R.drawable.mista_face,
                "When the gitl is inside you");
        db.postSound(Files.OKAY, R.raw.mom_okey,R.drawable.mom_okey,
                "She is so sweet");
        db.postSound(Files.NARANCIA_KILL, R.raw.narancia_kill,R.drawable.narancia_kill,
                "Dam he is angry");
        db.postSound(Files.OKU_STANDUSER, R.raw.okuyasu_standuser,R.drawable.okuyasu_standuser,
                "Can you hear me? S-t-a-n-d u-s-e-r!");
        db.postSound(Files.PERSI_BAIT, R.raw.persi_bait,R.drawable.persi_bait,
                "He did it! Or not?");
        db.postSound(Files.POLNAREFF_AVDOL, R.raw.polnareff_avdol,R.drawable.polnareff_avdol,
                "YES I AM!");
        db.postSound(Files.POLNAREFF_CHARIOT, R.raw.polnareff_chariot,R.drawable.polnareff_chariot,
                "Ready for a fight");
        db.postSound(Files.POLNAREFF_HOL_KILL, R.raw.polnareff_holhorse_kill_you,R.drawable.polnareff_holhorse_kill_you,
                "When you meet ur gf ex");
        db.postSound(Files.POLNAREFF_HOL_LAUGH, R.raw.polnareff_holhorse_laughting,R.drawable.polnareff_holhorse_laughting,
                "What a joke!");
        db.postSound(Files.POLNAREFF_HOL_RUN, R.raw.polnareff_holhorse_run,R.drawable.polnareff_holhorse_run,
                "Strategic retreat!");
        db.postSound(Files.POLNAREFF_NON, R.raw.polnareff_non,R.drawable.polnareff_non,
                "Not gonna happen");
        db.postSound(Files.POLNAREFF_TRES_BIEN, R.raw.polnareff_tres_bien,R.drawable.polnareff_tres_bien,
                "Oh, so you are french?");
        db.postSound(Files.POLNAREFF_YEAH, R.raw.polnareff_yeah,R.drawable.polnareff_yeah,
                "Bruh fist!");
        db.postSound(Files.ROHAN_HD, R.raw.rohan_heavens_door,R.drawable.rohan_heavens_door,
                "Reading is power!");
        db.postSound(Files.ROHAN_IWON, R.raw.rohan_i_won,R.drawable.rohan_i_won,
                "Actually no");
        db.postSound(Files.SEC_CIO_GOOD, R.raw.secco_cioccolata_good,R.drawable.secco_cioccolata_good,
                "You watch the anime and you parents come into the room on exactly this moment");
        db.postSound(Files.SHOKU, R.raw.shoku,R.drawable.shoku,
                "True man doesnt care");
        db.postSound(Files.SPEEDWAGON_TIME, R.raw.speedwagon_look_time,R.drawable.speedwagon_look_time,
                "Nice watch");
        db.postSound(Files.TAMAMI_OW, R.raw.tamami_ow,R.drawable.tamami_ow,
                "Not even overreaction");
        db.postSound(Files.BAND_TORTURE_DANCE, R.raw.torture_dance,R.drawable.torture_dance,
                "This is celebration");
        db.postSound(Files.TRISH_NOHURT, R.raw.trish_no_hurt,R.drawable.trish_no_hurt,
                "Finally has a stand!");
        db.postSound(Files.ZEPPELI_LUCK, R.raw.zeppeli_luck,R.drawable.zeppeli_luck,
                "Who needs luck?");
        db.postSound(Files.ZEPPELI_PLUCK, R.raw.zeppeli_pluck,R.drawable.zeppeli_pluck,
                "When you can have a pluck?");
    }

}
