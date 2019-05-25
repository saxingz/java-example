package org.saxing.a.java8;

import java.util.Objects;
import java.util.UUID;

public class CreateTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            String random = UUID.randomUUID().toString();
            new Thread(){
                @Override
                public void run() {
                    InitTest initTest = InitTest.createInitTest(random);
                    if (!Objects.equals(initTest.getName(), random)){
                        System.out.println("error. name = " + initTest.getName() + ", random = " + random);
                    }
                }
            }.start();
        }

        System.out.println("end ...");
    }

}
