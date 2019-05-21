package org.saxing.partialresponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * main
 *
 * @author saxing 2019/5/21 14:57
 */
public class Aa001574PartialResponseApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001574PartialResponseApplication.class);

    public static void main(String[] args) throws Exception {
        Map<Integer, Video> videos = new HashMap<>();
        videos.put(1, new Video(1, "Avatar", 178, "epic science fiction film", "James Cameron", "English"));
        videos.put(2, new Video(2, "Godzilla Resurgence", 120, "Action & drama movie|", "Hideaki Anno", "Japanese"));
        videos.put(3, new Video(3, "Interstellar", 169, "Adventure & Sci-Fi", "Christopher Nolan", "English"));
        VideoResource videoResource = new VideoResource(new FieldJsonMapper(), videos);


        LOGGER.info("Retrieving full response from server:-");
        LOGGER.info("Get all video information:");
        String videoDetails = videoResource.getDetails(1);
        LOGGER.info(videoDetails);

        LOGGER.info("----------------------------------------------------------");

        LOGGER.info("Retrieving partial response from server:-");
        LOGGER.info("Get video @id, @title, @director:");
        String specificFieldsDetails = videoResource.getDetails(3, "id", "title", "director");
        LOGGER.info(specificFieldsDetails);

        LOGGER.info("Get video @id, @length:");
        String videoLength = videoResource.getDetails(3, "id", "length");
        LOGGER.info(videoLength);
    }

}
