package org.saxing.executearound;

import java.io.FileWriter;
import java.io.IOException;

/**
 * simple file write
 *
 * @author saxing 2019/1/5 14:19
 */
public class SimpleFileWriter {

    public SimpleFileWriter(String filename, FileWriterAction fileWriterAction) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriterAction.writeFile(fileWriter);
        }
    }
}
