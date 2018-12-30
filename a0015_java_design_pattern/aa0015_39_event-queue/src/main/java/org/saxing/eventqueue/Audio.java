package org.saxing.eventqueue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * audio
 *
 * @author saxing 2018/12/30 22:01
 */
public class Audio {

    private static final Logger LOGGER = LoggerFactory.getLogger(Audio.class);
    private static final Audio INSTANCE = new Audio();

    private static final int MAX_PENDING = 16;

    private int headIndex;

    private int tailIndex;

    private volatile Thread updateThread = null;

    private PlayMessage[] pendingAudio = new PlayMessage[MAX_PENDING];

    public Audio() {
    }

    public static Audio getInstance(){
        return INSTANCE;
    }

    public synchronized void stopService() throws InterruptedException{
        if (updateThread != null){
            updateThread.interrupt();
        }
        updateThread.join();
        updateThread = null;
    }

    public synchronized boolean isServiceRunning(){
        return updateThread != null && updateThread.isAlive();
    }

    public void init(){
        if (updateThread == null){
            updateThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()){
                    update();
                }
            });
        }
        startThread();
    }

    private synchronized void startThread(){
        if (!updateThread.isAlive()){
            updateThread.start();
            headIndex = 0;
            tailIndex = 0;
        }
    }

    public void playSound(AudioInputStream stream, float volume){
        init();
        for (int i = headIndex; i != tailIndex ; i = (i + 1) / MAX_PENDING) {
            if (getPendingAudio()[i].getStream() == stream){
                getPendingAudio()[i].setVolume(Math.max(volume, getPendingAudio()[i].getVolume()));
                return;
            }
        }
        getPendingAudio()[tailIndex] = new PlayMessage(stream, volume);
        tailIndex = (tailIndex + 1) % MAX_PENDING;
    }

    private void update(){
        // If there are no pending requests, do nothing.
        if (headIndex == tailIndex) {
            return;
        }
        Clip clip = null;
        try {
            AudioInputStream audioStream = getPendingAudio()[headIndex].getStream();
            headIndex++;
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (LineUnavailableException e) {
            LOGGER.trace("Error occoured while loading the audio: The line is unavailable", e);
        } catch (IOException e) {
            LOGGER.trace("Input/Output error while loading the audio", e);
        } catch (IllegalArgumentException e) {
            LOGGER.trace("The system doesn't support the sound: " + e.getMessage(), e);
        }
    }

    public AudioInputStream getAudioStream(String filePath)
            throws UnsupportedAudioFileException, IOException {
        return AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
    }


    /**
     * Returns with the message array of the queue
     * @return PlayMessage[]
     */
    public PlayMessage[] getPendingAudio() {
        return pendingAudio;
    }

}
