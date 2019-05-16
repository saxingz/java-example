package org.saxing.sampleapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * main
 *
 * @author saxing 2019/5/16 16:51
 */
public final class Aa00157601SampleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa00157601SampleApplication.class);
    private Aa00157601SampleApplication() {
    }

    public static void main(String[] args) {
        try {
            File applicationFile = new File(Aa00157601SampleApplication.class.getClassLoader().getResource("sample-ui/login.html").getPath());

            // should work for unix like OS (mac, unix etc...)
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(applicationFile);

            } else {
                // java Desktop not supported - above unlikely to work for Windows so try following instead...
                Runtime.getRuntime().exec("cmd.exe start " + applicationFile);
            }

        } catch (IOException ex) {
            LOGGER.error("An error occured.", ex);
        }
    }

}
