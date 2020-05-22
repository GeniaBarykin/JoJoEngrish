package com.evgeny.app.jojoengrish.audio;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

/**
 * Background music player
 *
 * @author genia
 */
public class Player {
    private static MediaPlayer mp;
    private static Player music;

    private static final int MAX_VOLUME = 100;
    private static float volume;
    private boolean playing;
    /**
     * Get volume without the instance
     *
     * @return int value of volume
     */
    public static int getVolumeInt() {

        return (int) (volume * 100);
    }

    /**
     * Get instance
     *
     * @return MediaPlayer instance
     */
    public static Player getInstance() {
        if (music == null) {
            music = new Player();
        }
        return music;
    }

    public void loadVolume(Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = settings.getString("volume", "50");
        Integer vol = Integer.parseInt(jsonString);
        volume = (float) vol / 100f;
    }

    /**
     * Play music
     *
     * @param context of activity to play in
     * @param id      Id of the track to play
     * @return int value of volume
     */
    public void play(Context context, int id) throws Exception {
            if (mp != null) {
                mp = null;
            }
            mp = MediaPlayer.create(context, id);
            playing=true;
            mp.setLooping(false);
            mp.setVolume(volume, volume);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();

                };
            });
    }

    /**
     * Off the music player
     */
    public void stop() {
        mp.stop();
        mp.release();
        playing=false;
    }

    public boolean isPlaying(){
        try {
            if (playing) {
                return mp.isPlaying();
            } else {
                return false;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Set the volume
     *
     * @param vol int
     */
    public static void setVolume(int vol, Context context) {
        volume = (float) vol / 100f;
        if(mp!=null) {
            mp.setVolume(volume, volume);
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("volume", vol + "");
            editor.apply();
        }
    }
}
