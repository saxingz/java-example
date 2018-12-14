package org.saxing.dirty_flag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//@SpringBootApplication

/**
 * main
 *
 * @author saxing 2018/12/14 16:37
 */
public class Aa001528DirtyFlagApplication {

    public void run(){
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                World world = new World();
                List<String> countries = world.fetch();
                System.out.println("Our world currently has the following countries:-");
                for (String country :countries){
                    System.out.println("\t" + country);
                }
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
//        SpringApplication.run(Aa001528DirtyFlagApplication.class, args);
        Aa001528DirtyFlagApplication app = new Aa001528DirtyFlagApplication();
        app.run();
    }

}

