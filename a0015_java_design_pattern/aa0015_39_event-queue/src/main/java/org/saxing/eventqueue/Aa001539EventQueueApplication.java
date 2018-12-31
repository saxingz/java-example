package org.saxing.eventqueue;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * main
 * 
 * @author saxing 2018/12/30 22:48
 */
public class Aa001539EventQueueApplication {

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, InterruptedException {
        Audio audio = Audio.getInstance();
        audio.playSound(audio.getAudioStream("D:\\Code\\study\\java-example\\a0015_java_design_pattern\\aa0015_39_event-queue\\etc\\Bass-Drum-1.wav"), -10.0f);
        audio.playSound(audio.getAudioStream("D:\\Code\\study\\java-example\\a0015_java_design_pattern\\aa0015_39_event-queue\\etc\\Closed-Hi-Hat-1.wav"), -8.0f);

        System.out.println("Press Enter key to stop the program...");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            br.read();
        }
        audio.stopService();
    }

}

