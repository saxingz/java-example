package org.saxing.eventqueue;

import javax.sound.sampled.AudioInputStream;

public class PlayMessage {

    private AudioInputStream stream;

    private float volume;

    public PlayMessage(AudioInputStream stream, float volume) {
        setStream(stream);
        setVolume(volume);
    }

    public AudioInputStream getStream() {
        return stream;
    }

    public void setStream(AudioInputStream stream) {
        this.stream = stream;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
