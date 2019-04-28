package org.saxing.muteidiom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * main
 *
 * @author saxing 2019/4/28 9:42
 */
public class Aa001568MuteIdiomApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001568MuteIdiomApplication.class);

    public static void main(String[] args) throws SQLException {
        useOfLoggedMute();

        useOfMute();
    }

    private static void useOfMute() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Mute.mute(() -> out.write("Hello".getBytes()));
    }

    private static void useOfLoggedMute() throws SQLException {
        Resource resource = null;
        try {
            resource = acquireResource();
            utilizeResource(resource);
        } finally {
            closeResource(resource);
        }
    }

    private static void closeResource(Resource resource) {
        Mute.loggedMute(() -> resource.close());
    }

    private static void utilizeResource(Resource resource) throws SQLException {
        LOGGER.info("Utilizing acquired resource: {}", resource);
    }

    private static Resource acquireResource() throws SQLException{
        return new Resource() {
            @Override
            public void close() throws IOException {
                throw new IOException("Error in closing resource: " + this);
            }
        };
    }

}
