package com.ror.utils.sounds;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundManager {
    private SoundManager() {} // instantiation prevention yay

    private static Clip bgmClip;
    private static Clip sfxClip;

    public static void playMusic(String path) { //music loops
        bgmClip = loadClip(path);
        if (bgmClip != null) {
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgmClip.start();
        }
    }

    public static void playSfx(String path) {
        sfxClip = loadClip(path);
        if (sfxClip != null) {
            sfxClip.start();
        }
    }

    private static Clip loadClip(String path) {
        try {
            AudioInputStream ais = null;
            
            URL url = SoundManager.class.getResource(path);
            if (url != null) {
                ais = AudioSystem.getAudioInputStream(url);
            } else {
                String fsPath = path.startsWith("/") ? path.substring(1) : path;
                File file = new File(fsPath);
                if (file.exists()) {
                    ais = AudioSystem.getAudioInputStream(file);
                }
            }

            if (ais == null) {
                System.err.println("AUDIO ERROR: Could not find file at " + path);
                return null;
            }

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            
            //debug thing, the original clip is 15mins long.
            // if (path.toLowerCase().contains("title")) {
            //     clip.loop(Clip.LOOP_CONTINUOUSLY);
            // }
            return clip;
        } catch (Exception e) {
            System.err.println("AUDIO SYSTEM CRASH!");
            e.printStackTrace();
            return null;
        }

        
    }

    public static void stopMusic() {
        if (bgmClip != null) {
            bgmClip.stop();
            bgmClip.close();
        }
    }

    public static void stopSfx() {
        if (sfxClip != null) {
            sfxClip.stop();
            sfxClip.close();
        }
    }

    public static void shutdownSound() {
        stopMusic();
        stopSfx();
    }
}