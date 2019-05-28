package org.saxing.a.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFile {

    public static void copyFileByChannel(File source, File dest)
        throws IOException{
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(dest).getChannel();
        ) {
            for (long count = sourceChannel.size(); count > 0; ){
                long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
                count -= transferred;
            }
        }
    }

}
