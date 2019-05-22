package org.saxing.java8;

import org.saxing.systeminfo.RuntimeTest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class A0020Java8Application
        implements CommandLineRunner
{

    public static void main(String[] args)  {
        SpringApplication.run(A0020Java8Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        RuntimeTest.systemInfo();
    }
}
