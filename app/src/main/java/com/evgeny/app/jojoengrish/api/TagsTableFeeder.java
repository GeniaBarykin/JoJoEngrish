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
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "yare");
        db.postTag(id, "daze");
        db.postTag(id, "good");
        db.postTag(id, "grief");

        id = db.getSoundId(Files.WRY_SCARED);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "wryyyyyyy");
        db.postTag(id, "nazi");
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "vampire");
        db.postTag(id, "noise");
        db.postTag(id, "loudly");
        db.postTag(id, "fight");
        db.postTag(id, "scared");
        db.postTag(id, "confused");

        id = db.getSoundId(Files.WRY_ANGRY);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "nazi");
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "vampire");
        db.postTag(id, "noise");
        db.postTag(id, "loudly");
        db.postTag(id, "fight");
        db.postTag(id, "angry");
        db.postTag(id, "wryyyyyyy");

        id = db.getSoundId(Files.WRY_QUIET);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "nazi");
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "vampire");
        db.postTag(id, "noise");
        db.postTag(id, "quietly");
        db.postTag(id, "coffin");
        db.postTag(id, "menacing");
        db.postTag(id, "wryyyyyyy");

        id = db.getSoundId(Files.BITE_THE_DUST);
        db.postTag(id, "kira");
        db.postTag(id, "yoshikage");
        db.postTag(id, "killer");
        db.postTag(id, "queen");
        db.postTag(id, "bite");
        db.postTag(id, "dust");
        db.postTag(id, "the");
        db.postTag(id, "reset");
        db.postTag(id, "time");
        db.postTag(id, "dusto");
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");

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
        db.postTag(id, "fight");

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
        db.postTag(id, "fight");
        db.postTag(id, "warudo");
        db.postTag(id, "stand");

        id = db.getSoundId(Files.MUDA_MUDA);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "muda");
        db.postTag(id, "knife");
        db.postTag(id, "knives");
        db.postTag(id, "fight");

        id = db.getSoundId(Files.STAND_POWER);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "the");
        db.postTag(id, "world");
        db.postTag(id, "za");
        db.postTag(id, "fight");
        db.postTag(id, "warudo");
        db.postTag(id, "stand");
        db.postTag(id, "power");
        db.postTag(id, "powah");

        id = db.getSoundId(Files.KONO_DIO_DA);
        db.postTag(id, "dio");
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "brando");
        db.postTag(id, "it");
        db.postTag(id, "was");
        db.postTag(id, "me");
        db.postTag(id, "kono");
        db.postTag(id, "da");

        id = db.getSoundId(Files.JOTARO_UNDERSTAND);
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "do");
        db.postTag(id, "you");
        db.postTag(id, "understand");

        id = db.getSoundId(Files.UNDERSTAND);
        db.postTag(id, "rubber");
        db.postTag(id, "soul");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "do");
        db.postTag(id, "you");
        db.postTag(id, "understand");

        id = db.getSoundId(Files.SPEEDWAGON_NAZI);
        db.postTag(id, "speedwagon");
        db.postTag(id, "nazi");
        db.postTag(id, "soldier");
        db.postTag(id, "battle");
        db.postTag(id, "tendency");

        id = db.getSoundId(Files.VERY_NICE);
        db.postTag(id, "battle");
        db.postTag(id, "tendency");
        db.postTag(id, "ceasar");
        db.postTag(id, "zeppeli");
        db.postTag(id, "chan");
        db.postTag(id, "very");
        db.postTag(id, "nice");
        db.postTag(id, "nicu");

        id = db.getSoundId(Files.DIOOOO);
        db.postTag(id, "jonathan");
        db.postTag(id, "joestar");
        db.postTag(id, "emotional");
        db.postTag(id, "angry");
        db.postTag(id, "dio");
        db.postTag(id, "phantom");
        db.postTag(id, "blood");

        id = db.getSoundId(Files.OH_MY_GOD);
        db.postTag(id, "joseph");
        db.postTag(id, "joestar");
        db.postTag(id, "emotional");
        db.postTag(id, "oh");
        db.postTag(id, "my");
        db.postTag(id, "god");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");

        id = db.getSoundId(Files.YES_OH_MY_GOD);
        db.postTag(id, "joseph");
        db.postTag(id, "joestar");
        db.postTag(id, "oh");
        db.postTag(id, "yes");
        db.postTag(id, "my");
        db.postTag(id, "god");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");

        id = db.getSoundId(Files.OH_NO);
        db.postTag(id, "emotional");
        db.postTag(id, "joseph");
        db.postTag(id, "no");
        db.postTag(id, "joestar");
        db.postTag(id, "oh");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");

        id = db.getSoundId(Files.HOLY_SHIT);
        db.postTag(id, "emotional");
        db.postTag(id, "joseph");
        db.postTag(id, "holy");
        db.postTag(id, "hor");
        db.postTag(id, "joestar");
        db.postTag(id, "shit");
        db.postTag(id, "she");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");

        id = db.getSoundId(Files.SON_OF_A_BITCH);
        db.postTag(id, "emotional");
        db.postTag(id, "joseph");
        db.postTag(id, "son");
        db.postTag(id, "son");
        db.postTag(id, "of");
        db.postTag(id, "joestar");
        db.postTag(id, "bitch");
        db.postTag(id, "be");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");

        id = db.getSoundId(Files.HEY_BABY);
        db.postTag(id, "zeppeli");
        db.postTag(id, "will");
        db.postTag(id, "anthonio");
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "hey");
        db.postTag(id, "baby");

        id = db.getSoundId(Files.YOU_FOOL);
        db.postTag(id, "battle");
        db.postTag(id, "tendency");
        db.postTag(id, "Braka");
        db.postTag(id, "Monoga");
        db.postTag(id, "you");
        db.postTag(id, "fool");
        db.postTag(id, "german");
        db.postTag(id, "rudol");
        db.postTag(id, "von");
        db.postTag(id, "stroheim");

        id = db.getSoundId(Files.SHIZA);
        db.postTag(id, "battle");
        db.postTag(id, "tendency");
        db.postTag(id, "joseph");
        db.postTag(id, "joestar");
        db.postTag(id, "ceasar");
        db.postTag(id, "shiza");
        db.postTag(id, "zeppeli");
        db.postTag(id, "dead");
        db.postTag(id, "stone");
        db.postTag(id, "cross");
        db.postTag(id, "crucifixion");
        db.postTag(id, "logo");
        db.postTag(id, "rip");

        id = db.getSoundId(Files.HELL_TO_YOU);
        db.postTag(id, "muhammad");
        db.postTag(id, "avdol");
        db.postTag(id, "abdul");
        db.postTag(id, "hell");
        db.postTag(id, "to");
        db.postTag(id, "2");
        db.postTag(id, "you");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");

        id = db.getSoundId(Files.YES_I_AM);
        db.postTag(id, "muhammad");
        db.postTag(id, "avdol");
        db.postTag(id, "abdol");
        db.postTag(id, "abdul");
        db.postTag(id, "yes");
        db.postTag(id, "yes");
        db.postTag(id, "I");
        db.postTag(id, "am");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");

        id = db.getSoundId(Files.NICE);
        db.postTag(id, "phantom");
        db.postTag(id, "nice");
        db.postTag(id, "blood");
        db.postTag(id, "joseph");
        db.postTag(id, "joestar");
        db.postTag(id, "peek");
        db.postTag(id, "keyhole");
        db.postTag(id, "door");

        id = db.getSoundId(Files.YES_YES);
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "yes");

        id = db.getSoundId(Files.NO_NO);
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "no");

        id = db.getSoundId(Files.LERO);
        db.postTag(id, "kakyoin");
        db.postTag(id, "noriaki");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "lero");
        db.postTag(id, "cherry");

        id = db.getSoundId(Files.THANK_YOU);
        db.postTag(id, "kakyoin");
        db.postTag(id, "noriaki");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "cherry");
        db.postTag(id, "thank");
        db.postTag(id, "you");

        id = db.getSoundId(Files.PEROLO);
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "here");
        db.postTag(id, "i");
        db.postTag(id, "come");
        db.postTag(id, "here");
        db.postTag(id, "jonhy");
        db.postTag(id, "axe");
        db.postTag(id, "door");

        id = db.getSoundId(Files.RUN);
        db.postTag(id, "battle");
        db.postTag(id, "tendency");
        db.postTag(id, "joseph");
        db.postTag(id, "joestar");
        db.postTag(id, "run");
        db.postTag(id, "hotfoot");
        db.postTag(id, "nigerundayo");
        db.postTag(id, "smokey");

        id = db.getSoundId(Files.ORA_ORA);
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "star");
        db.postTag(id, "platinum");
        db.postTag(id, "ora");
        db.postTag(id, "battle");

        id = db.getSoundId(Files.BRAVO);
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "battle");
        db.postTag(id, "bravo");

        id = db.getSoundId(Files.KAKOYIN_DEATH);
        db.postTag(id, "kakyoin");
        db.postTag(id, "noriaki");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "death");
        db.postTag(id, "joseph");
        db.postTag(id, "joestar");

        id = db.getSoundId(Files.PRETTY);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "wow");
        db.postTag(id, "pretty");
        db.postTag(id, "okuyasu");
        db.postTag(id, "nijimura");
        db.postTag(id, "tomoko");
        db.postTag(id, "higashikata");

        id = db.getSoundId(Files.KILL_DA_HOE);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "koichi");
        db.postTag(id, "hirose");
        db.postTag(id, "echoes");
        db.postTag(id, "bitch");
        db.postTag(id, "beatch");
        db.postTag(id, "beetch");
        db.postTag(id, "kill");
        db.postTag(id, "da");
        db.postTag(id, "hoe");

        id = db.getSoundId(Files.GO_AHEAD);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "joestar");
        db.postTag(id, "darby");
        db.postTag(id, "j");
        db.postTag(id, ".");
        db.postTag(id, "go");
        db.postTag(id, "ahead");
        db.postTag(id, "daniel");

        id = db.getSoundId(Files.ARRIVIDERCI);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "bruno");
        db.postTag(id, "bucciarati");
        db.postTag(id, "ari");
        db.postTag(id, "arri");
        db.postTag(id, "der");
        db.postTag(id, "ci");
        db.postTag(id, "chi");
        db.postTag(id, "arrividerci");

        id = db.getSoundId(Files.GIORNO_MUDA);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "expiriece");
        db.postTag(id, "requiem");
        db.postTag(id, "wind");
        db.postTag(id, "muda");
        db.postTag(id, "giorno");
        db.postTag(id, "giovanna");

        id = db.getSoundId(Files.MUDA_MUDA_FIGHT);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "muda");
        db.postTag(id, "fight");

        id = db.getSoundId(Files.STICKY_FINGERS);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "bruno");
        db.postTag(id, "bucciarati");
        db.postTag(id, "sticky");
        db.postTag(id, "stand");
        db.postTag(id, "fingers");
        db.postTag(id, "zipper");

        id = db.getSoundId(Files.FAKE_PHONE);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "phone");
        db.postTag(id, "tele");
        db.postTag(id, "ring");
        db.postTag(id, "diavolo");
        db.postTag(id, "alo");

        id = db.getSoundId(Files.SONOCHIDO);
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "joseph");
        db.postTag(id, "joestar");
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "oppening");
        db.postTag(id, "intro");
        db.postTag(id, "sono");
        db.postTag(id, "chi");
        db.postTag(id, "sadame");
        db.postTag(id, "jojo");

        id = db.getSoundId(Files.IREFUSE);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "rohan");
        db.postTag(id, "kishibe");
        db.postTag(id, "daga");
        db.postTag(id, "daga");
        db.postTag(id, "kotowaru");
        db.postTag(id, "refuse");

        id = db.getSoundId(Files.HAYATO);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "hayato");
        db.postTag(id, "kira");
        db.postTag(id, "yoshikage");
        db.postTag(id, "killer");
        db.postTag(id, "queen");

        id = db.getSoundId(Files.TIMESTOP);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "time");
        db.postTag(id, "stop");
        db.postTag(id, "fight");
        db.postTag(id, "toki");
        db.postTag(id, "wo");
        db.postTag(id, "tomare");

        id = db.getSoundId(Files.GOFLYING);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "narancia");
        db.postTag(id, "ghirga");
        db.postTag(id, "go");
        db.postTag(id, "flying");
        db.postTag(id, "volare");
        db.postTag(id, "via");

        id = db.getSoundId(Files.THEHANDSOUND);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "the");
        db.postTag(id, "hand");
        db.postTag(id, "sound");
        db.postTag(id, "erase");
        db.postTag(id, "okuyasu");
        db.postTag(id, "nijimura");

        id = db.getSoundId(Files.HEYHEY);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "rohan");
        db.postTag(id, "kishibe");
        db.postTag(id, "hey");
        db.postTag(id, "oi");

        id = db.getSoundId(Files.PILLAR);
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "pillar");
        db.postTag(id, "men");
        db.postTag(id, "music");
        db.postTag(id, "ost");

        id = db.getSoundId(Files.SEVEN);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "expiriece");
        db.postTag(id, "requiem");
        db.postTag(id, "wind");
        db.postTag(id, "muda");
        db.postTag(id, "giorno");
        db.postTag(id, "giovanna");
        db.postTag(id, "seven");
        db.postTag(id, "page");
        db.postTag(id, "7");

        //17.7.2020
        id = db.getSoundId(Files.BOINGO_LAUGH);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "laugh");
        db.postTag(id, "boingo");

        id = db.getSoundId(Files.KARS_LAUGH);
        db.postTag(id, "battle");
        db.postTag(id, "tendency");
        db.postTag(id, "kars");
        db.postTag(id, "laugh");

        id = db.getSoundId(Files.BUEN_GIORNO);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "giorno");
        db.postTag(id, "giovanna");
        db.postTag(id, "buen");

        id = db.getSoundId(Files.STICKY_FIGHT);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "bruno");
        db.postTag(id, "bucciarati");
        db.postTag(id, "sticky");
        db.postTag(id, "stand");
        db.postTag(id, "fingers");
        db.postTag(id, "zipper");

        id = db.getSoundId(Files.HELP_ME_OMG);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "joseph");
        db.postTag(id, "joestar");
        db.postTag(id, "help");
        db.postTag(id, "me");
        db.postTag(id, "omg");
        db.postTag(id, "oh");
        db.postTag(id, "my");
        db.postTag(id, "god");

        id = db.getSoundId(Files.KONO_POWAH);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "kono");
        db.postTag(id, "power");
        db.postTag(id, "powah");

        id = db.getSoundId(Files.KARS_GUITAR);
        db.postTag(id, "battle");
        db.postTag(id, "tendency");
        db.postTag(id, "kars");
        db.postTag(id, "guitar");

        id = db.getSoundId(Files.PAS_PAS);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "sex");
        db.postTag(id, "pistols");
        db.postTag(id, "mista");
        db.postTag(id, "pas");


        id = db.getSoundId(Files.YEHA);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "sex");
        db.postTag(id, "pistols");
        db.postTag(id, "mista");
        db.postTag(id, "ye");
        db.postTag(id, "yeha");

        id = db.getSoundId(Files.STAR_WARUDO);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "star");
        db.postTag(id, "star");
        db.postTag(id, "platinum");
        db.postTag(id, "za");
        db.postTag(id, "warudo");
        db.postTag(id, "world");
        db.postTag(id, "time");
        db.postTag(id, "stop");

        id = db.getSoundId(Files.EMERALDO_SPLASH);
        db.postTag(id, "kakyoin");
        db.postTag(id, "noriaki");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "stand");
        db.postTag(id, "hierophant");
        db.postTag(id, "green");

        id = db.getSoundId(Files.BUEN_GIORNO);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "giorno");
        db.postTag(id, "giovanna");
        db.postTag(id, "music");
        db.postTag(id, "theme");
        db.postTag(id, "comeback");

        id = db.getSoundId(Files.AVDOL_TSK);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "muhammad");
        db.postTag(id, "avdol");
        db.postTag(id, "tsk");
        id = db.getSoundId(Files.BAND_ENEMY);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "enemy");
        db.postTag(id, "take");
        db.postTag(id, "this");
        db.postTag(id, "wine");
        id = db.getSoundId(Files.BASEBALL);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "game");
        db.postTag(id, "baseball");
        db.postTag(id, "it");
        id = db.getSoundId(Files.BRUNO_LIAR);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "taste");
        db.postTag(id, "liar");
        db.postTag(id, "bruno");
        db.postTag(id, "bucciarati");
        id = db.getSoundId(Files.CROSS_SPLIT);
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "cross");
        db.postTag(id, "split");
        db.postTag(id, "attack");
        id = db.getSoundId(Files.DARBY_CANT);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "darby");
        db.postTag(id, "call");
        db.postTag(id, "can");
        id = db.getSoundId(Files.DIA_ALREADY);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "diavolo");
        db.postTag(id, "already");
        db.postTag(id, "see");
        id = db.getSoundId(Files.DIA_DARE);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "diavolo");
        db.postTag(id, "dare");
        db.postTag(id, "alive");
        db.postTag(id, "stand");
        id = db.getSoundId(Files.DIA_KC);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "diavolo");
        db.postTag(id, "king");
        db.postTag(id, "crimson");
        id = db.getSoundId(Files.DIA_KC_CALM);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "diavolo");
        db.postTag(id, "king");
        db.postTag(id, "crimson");
        id = db.getSoundId(Files.DIA_RESUME);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "diavolo");
        db.postTag(id, "resume");
        db.postTag(id, "time");
        id = db.getSoundId(Files.DIA_AWAY);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "diavolo");
        db.postTag(id, "stay");
        db.postTag(id, "away");
        id = db.getSoundId(Files.DIO_OHO);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "oh");
        db.postTag(id, "ho");
        db.postTag(id, "approach");
        id = db.getSoundId(Files.PIL_CRY);
        db.postTag(id, "battle");
        db.postTag(id, "tendency");
        db.postTag(id, "pillar");
        db.postTag(id, "men");
        db.postTag(id, "eisi");
        db.postTag(id, "disi");
        db.postTag(id, "cry");
        id = db.getSoundId(Files.MEGA);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "f-mega");
        db.postTag(id, "game");
        id = db.getSoundId(Files.GIO_GOLD_EXP);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "experience");
        id = db.getSoundId(Files.GIO_REQUIEM);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "it");
        db.postTag(id, "requiem");
        db.postTag(id, "stand");
        db.postTag(id, "experience");
        id = db.getSoundId(Files.JOSEPH_TEQUILA);
        db.postTag(id, "battle");
        db.postTag(id, "tendency");
        id = db.getSoundId(Files.JOSUKE_BASTARD);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        id = db.getSoundId(Files.JOSUKE_GREAT);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "josuke");
        db.postTag(id, "higashikata");
        db.postTag(id, "great");
        id = db.getSoundId(Files.JOSUKE_OKUYASU);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "josuke");
        db.postTag(id, "higashikata");
        db.postTag(id, "okuyasu");
        id = db.getSoundId(Files.JOTARO_BURNING);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "fire");
        db.postTag(id, "burn");
        id = db.getSoundId(Files.JOTARO_GROAN);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "groan");
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "press");
        db.postTag(id, "hard");
        db.postTag(id, "road");
        db.postTag(id, "roller");
        id = db.getSoundId(Files.JOTARO_SCUM);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "scum");
        db.postTag(id, "money");
        id = db.getSoundId(Files.JOTARO_SHUTUP);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "shut");
        db.postTag(id, "up");
        db.postTag(id, "annoying");
        id = db.getSoundId(Files.JOTARO_FINGER);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jotaro");
        db.postTag(id, "kujo");
        db.postTag(id, "finger");
        db.postTag(id, "star");
        db.postTag(id, "platinum");
        id = db.getSoundId(Files.KAKYOIN_DEFLECT);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "kakyoin");
        db.postTag(id, "noriaki");
        db.postTag(id, "deflect");
        db.postTag(id, "emerald");
        db.postTag(id, "splash");
        id = db.getSoundId(Files.KIRA_ERECTION);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "kira");
        db.postTag(id, "yoshikage");
        db.postTag(id, "hand");
        db.postTag(id, "monaliza");
        db.postTag(id, "erection");
        id = db.getSoundId(Files.KIRA_JOIN);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "kira");
        db.postTag(id, "yoshikage");
        db.postTag(id, "bath");
        db.postTag(id, "join");
        id = db.getSoundId(Files.MISTA_FACE);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "trish");
        db.postTag(id, "guido");
        db.postTag(id, "face");
        db.postTag(id, "body");
        db.postTag(id, "swap");
        db.postTag(id, "smell");
        id = db.getSoundId(Files.OKAY);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "mom");
        db.postTag(id, "mother");
        db.postTag(id, "okay");
        db.postTag(id, "okey");
        id = db.getSoundId(Files.NARANCIA_KILL);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "narancia");
        db.postTag(id, "ghirga");
        db.postTag(id, "kill");
        db.postTag(id, "angry");
        id = db.getSoundId(Files.OKU_STANDUSER);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "okuyasu");
        db.postTag(id, "nijimura");
        db.postTag(id, "joseph");
        db.postTag(id, "old");
        db.postTag(id, "deaf");
        db.postTag(id, "standuser");
        id = db.getSoundId(Files.PERSI_BAIT);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "persi");
        db.postTag(id, "fishingrod");
        db.postTag(id, "bait");
        id = db.getSoundId(Files.POLNAREFF_AVDOL);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        id = db.getSoundId(Files.POLNAREFF_CHARIOT);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        id = db.getSoundId(Files.POLNAREFF_HOL_LAUGH);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        id = db.getSoundId(Files.POLNAREFF_HOL_RUN);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        id = db.getSoundId(Files.POLNAREFF_NON);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        id = db.getSoundId(Files.POLNAREFF_TRES_BIEN);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        db.postTag(id, "tres");
        db.postTag(id, "bien");
        db.postTag(id, "shave");
        id = db.getSoundId(Files.POLNAREFF_YEAH);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "jean");
        db.postTag(id, "pierre");
        db.postTag(id, "polnaref");
        db.postTag(id, "yeah");
        db.postTag(id, "pant");
        db.postTag(id, "bro");
        id = db.getSoundId(Files.ROHAN_HD);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "rohan");
        db.postTag(id, "kishibe");
        db.postTag(id, "heavens");
        db.postTag(id, "door");
        id = db.getSoundId(Files.ROHAN_IWON);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "rohan");
        db.postTag(id, "kishibe");
        db.postTag(id, "i");
        db.postTag(id, "won");
        id = db.getSoundId(Files.SEC_CIO_GOOD);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "good");
        db.postTag(id, "gay");
        db.postTag(id, "men");
        db.postTag(id, "secco");
        db.postTag(id, "cioccolata");

        id = db.getSoundId(Files.SHOKU);
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "shock");
        db.postTag(id, "girls");
        id = db.getSoundId(Files.SPEEDWAGON_TIME);
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "speedwagon");
        db.postTag(id, "look");
        db.postTag(id, "time");
        id = db.getSoundId(Files.TAMAMI_OW);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "ow");
        db.postTag(id, "tamami");
        db.postTag(id, "lock");
        id = db.getSoundId(Files.BAND_TORTURE_DANCE);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "torture");
        db.postTag(id, "dance");
        db.postTag(id, "music");
        id = db.getSoundId(Files.TRISH_NOHURT);
        db.postTag(id, "vento");
        db.postTag(id, "aureo");
        db.postTag(id, "golden");
        db.postTag(id, "wind");
        db.postTag(id, "trish");
        db.postTag(id, "no");
        db.postTag(id, "hurt");
        db.postTag(id, "more");
        db.postTag(id, "una");
        id = db.getSoundId(Files.ZEPPELI_LUCK);
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "will");
        db.postTag(id, "zeppeli");
        db.postTag(id, "anthonio");
        db.postTag(id, "pluck");
        id = db.getSoundId(Files.ZEPPELI_PLUCK);
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "will");
        db.postTag(id, "zeppeli");
        db.postTag(id, "anthonio");
        db.postTag(id, "pluck");

        id = db.getSoundId(Files.DIO_COUNT);
        db.postTag(id, "dio");
        db.postTag(id, "brando");
        db.postTag(id, "stardust");
        db.postTag(id, "crusaders");
        db.postTag(id, "counting");
        db.postTag(id, "seconds");
        db.postTag(id, "one");
        db.postTag(id, "two");
        db.postTag(id, "eight");
        db.postTag(id, "time");
        db.postTag(id, "stop");
        id = db.getSoundId(Files.JONATHAN_OVERDRIVE);
        db.postTag(id, "phantom");
        db.postTag(id, "blood");
        db.postTag(id, "jonathan");
        db.postTag(id, "joestar");
        db.postTag(id, "punch");
        db.postTag(id, "hamon");
        db.postTag(id, "overdrive");
        db.postTag(id, "sunlight");
        db.postTag(id, "fight");
        id = db.getSoundId(Files.JOSUKE_DORA);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "josuke");
        db.postTag(id, "joestar");
        db.postTag(id, "dora");
        db.postTag(id, "stand");
        id = db.getSoundId(Files.KIRA_DOORKNOB);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "kira");
        db.postTag(id, "killer");
        db.postTag(id, "queen");
        db.postTag(id, "doorknob");
        db.postTag(id, "touched");
        db.postTag(id, "yoshikage");
        id = db.getSoundId(Files.KOICHI_ACT_T);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "koichi");
        db.postTag(id, "hirose");
        db.postTag(id, "echoes");
        db.postTag(id, "act");
        db.postTag(id, "3");
        db.postTag(id, "three");
        db.postTag(id, "stand");
        id = db.getSoundId(Files.KOICHI_ACT_TF);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "koichi");
        db.postTag(id, "hirose");
        db.postTag(id, "echoes");
        db.postTag(id, "act");
        db.postTag(id, "3");
        db.postTag(id, "three");
        db.postTag(id, "stand");
        db.postTag(id, "freeze");
        id = db.getSoundId(Files.HOLO);
        db.postTag(id, "unbreakable");
        db.postTag(id, "diamond");
        db.postTag(id, "koichi");
        db.postTag(id, "hirose");
        db.postTag(id, "echoes");
        db.postTag(id, "bitch");
        db.postTag(id, "beatch");
        db.postTag(id, "beetch");
        db.postTag(id, "kill");
        db.postTag(id, "da");
        db.postTag(id, "hoe");
        db.postTag(id, "holovile");
        db.postTag(id, "amano hikamee");
        db.postTag(id, "tomoshika hikasa");
    }

}
