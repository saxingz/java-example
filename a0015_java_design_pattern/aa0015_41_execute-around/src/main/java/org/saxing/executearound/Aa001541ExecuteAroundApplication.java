package org.saxing.executearound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * main
 *
 * @author saxing 2019/1/5 14:21
 */
public class Aa001541ExecuteAroundApplication {

    public static void main(String[] args) throws IOException {
        FileWriterAction writeHello = writer -> {
            writer.write("Hello");
            writer.append(" ");
            writer.append("there!");
        };
        new SimpleFileWriter("hello.txt", writeHello);
    }

}

