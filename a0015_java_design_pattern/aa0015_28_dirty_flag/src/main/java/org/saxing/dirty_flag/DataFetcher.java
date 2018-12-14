package org.saxing.dirty_flag;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A mock database manager -- Fetches data from a raw file.
 *
 * @author saxing 2018/12/14 14:31
 */
public class DataFetcher {

    private final String filename = "world.txt";
    private long lastFetched;

    public DataFetcher() {
        this.lastFetched = -1;
    }

    private boolean isDirty(long fileLastModified){
        if (lastFetched != fileLastModified){
            lastFetched = fileLastModified;
            return true;
        }
        return false;
    }

    public List<String> fetch(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());

        if (isDirty(file.lastModified())){
            System.out.println(filename + " is dirty! Re-fetching file content...");

            List<String> data = new ArrayList<>();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    data.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        return new ArrayList<>();
    }

}
