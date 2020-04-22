package com.evgeny.app.jojoengrish.audio;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Background music player
 *
 * @author genia
 */
public class Player {
    private static MediaPlayer mp;
    private static Player music;

    private static final int MAX_VOLUME = 100;
    private static float volume = 0.6f;
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
    }

    /**
     * Off the music player
     */
    public void stop() {
        mp.stop();
        playing=false;
    }

    public boolean isPlaying(){
        if(playing) {
            return mp.isPlaying();
        } else {
            return false;
        }
    }

    /**
     * Set the volume
     *
     * @param vol int
     */
    public static void setVolume(int vol) {
        volume = (float) vol / 100f;
        mp.setVolume(volume, volume);
    }
}
