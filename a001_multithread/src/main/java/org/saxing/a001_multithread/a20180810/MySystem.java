package org.saxing.a001_multithread.a20180810;

import java.util.Date;

public class MySystem {

    private static MySystem instance = null;
    private Date date = new Date();

    public MySystem() {
    }

    public Date getDate(){
        return date;
    }

    public static synchronized MySystem getInstance(){
        if (instance == null){
            instance = new MySystem();
        }
        return instance;
    }
}
