package org.saxing.audio_video.mp3;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

import java.io.File;

public class TestMp3 {

    public static void main(String[] args) {
        covertToMp3("C:\\Users\\saxing\\Desktop\\媒体服务器\\trunk-out.PCMA",
                "C:\\Users\\saxing\\Desktop\\媒体服务器\\out\\mp3out.mp3");
    }

    public static void covertToMp3(String inFilePath, String outFilePath){
        try {
            File source = new File(inFilePath);
            File target = new File(outFilePath);

            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp3");
            attrs.setAudioAttributes(audio);


            //Encode
            Encoder encoder = new Encoder();
            MultimediaObject multimediaObject = new MultimediaObject(source);
            encoder.encode(multimediaObject, target, attrs);
        } catch (Exception ex) {
            ex.printStackTrace();
//            succeeded = false;
        }
    }

}
