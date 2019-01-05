package org.saxing.executearound;


import java.io.FileWriter;
import java.io.IOException;

/**
 * file writer action
 *
 * @author saxing 2019/1/5 14:18
 */
@FunctionalInterface
public interface FileWriterAction {

    void writeFile(FileWriter writer) throws IOException;

}
