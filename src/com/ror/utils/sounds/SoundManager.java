package com.ror.utils.sounds;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundManager {
    public static void play(String path) {
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
                return; 
            }

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            
            //debug thing, the original clip is 15mins long.
            if (path.toLowerCase().contains("title")) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            System.err.println("AUDIO SYSTEM CRASH!");
            e.printStackTrace();
        }
    }
}