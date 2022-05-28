package com.github.hrozhek.noizalyzerserver.util;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SoundUtil {

    public static byte[] convertPcmToWav(AudioFormat format, byte[] bytes) {
        try (ByteArrayOutputStream wavByteBuffer = new ByteArrayOutputStream()) {
            AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(bytes), format, bytes.length),
                    AudioFileFormat.Type.WAVE, wavByteBuffer);
            return wavByteBuffer.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);//todo
        }
    }
}
