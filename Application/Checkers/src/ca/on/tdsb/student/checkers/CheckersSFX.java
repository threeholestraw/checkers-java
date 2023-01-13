/**
 * Gameplay sound effects
 * author@   azheng
 */
package ca.on.tdsb.student.checkers;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

// src: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
public class CheckersSFX {

    static Clip clip;

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(CheckersResource.getRes(url)));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
        
        try {
            AudioSystem.getClip().addLineListener(new LineListener() { // src: https://stackoverflow.com/questions/5808560/error-playing-sound-java-no-line-matching-interface-clip-supporting-format
                @Override
                public void update(LineEvent event) {
                    if (LineEvent.Type.STOP.equals(event.getType())) {
                        clip.close();
                    }
                }
            });
        } catch (LineUnavailableException ex) {
            Logger.getLogger(CheckersSFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void playCapture() {
        playSound("Capture.wav");
    }

    public static void playEnd() {
        playSound("GenericNotify.wav");
    }

    public static void playMove() {
        playSound("Move.wav");
    }
}