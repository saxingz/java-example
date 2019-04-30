package org.saxing.mutex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/4/30 14:36
 */
public class Aa001569MutexApplication {

    public static void main(String[] args) {
        Mutex mutex = new Mutex();
        Jar jar = new Jar(1000, mutex);
        Thief peter = new Thief("Peter", jar);
        Thief john = new Thief("John", jar);
        peter.start();
        john.start();
    }

}
