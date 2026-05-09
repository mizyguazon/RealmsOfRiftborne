package com.ror.utils.sounds;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public final class SoundManager {
    private static Clip currentMusicClip;

    private SoundManager() {
    }

    public static synchronized void play(String path) {
        Clip clip = createClip(path);
        if (clip == null) {
            return;
        }

        clip.start();
    }

    public static synchronized void playMusic(String path) {
        shutdownSound();

        Clip clip = createClip(path);
        if (clip == null) {
            return;
        }

        currentMusicClip = clip;
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public static synchronized void shutdownSound() {
        if (currentMusicClip == null) {
            return;
        }

        currentMusicClip.stop();
        currentMusicClip.close();
        currentMusicClip = null;
    }

    private static Clip createClip(String path) {
        try {
            AudioInputStream audioInputStream = loadAudio(path);
            if (audioInputStream == null) {
                System.err.println("AUDIO ERROR: Could not find file at " + path);
                return null;
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (Exception exception) {
            System.err.println("AUDIO SYSTEM CRASH!");
            exception.printStackTrace();
            return null;
        }
    }

    private static AudioInputStream loadAudio(String path) {
        try {
            URL resource = SoundManager.class.getResource(path);
            if (resource != null) {
                return AudioSystem.getAudioInputStream(resource);
            }

            String fileSystemPath = path.startsWith("/") ? path.substring(1) : path;
            File file = new File(fileSystemPath);
            if (file.exists()) {
                return AudioSystem.getAudioInputStream(file);
            }

            return null;
        } catch (Exception exception) {
            System.err.println("AUDIO ERROR: Failed to load " + path);
            exception.printStackTrace();
            return null;
        }
    }
}
