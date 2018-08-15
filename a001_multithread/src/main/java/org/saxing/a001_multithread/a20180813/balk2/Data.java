package org.saxing.a001_multithread.a20180813.balk2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Data {

    private final String filename;
    private String content;
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent){
        content = newContent;
        changed = true;
    }

    public void save() throws IOException{
        if (!changed){
            return;
        }
        doSave();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
}
