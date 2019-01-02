package org.saxing.event_sourcing.processor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the implementation of event journal.
 *  * This implementation serialize/deserialize the events with JSON
 *  * and writes/reads them on a Journal.json file at the working directory.
 *
 * @author saxing 2019/1/2 23:30
 */
public class JsonFileJournal {

    private final File aFile;
    private final List<String> events = new ArrayList<>();
    private int index = 0;

    public JsonFileJournal() {
        aFile = new File("Journal.json");
        if (aFile.exists()){
            try (BufferedReader input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(aFile), "UTF-8")
            )) {
                String line;
                while ((line = input.readLine()) != null){
                    events.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            reset();
        }
    }

    /**
     * reset
     */
    public void reset(){
        aFile.delete();
    }
}
