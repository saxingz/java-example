package org.saxing.featuretoggle;

import org.saxing.featuretoggle.pattern.Service;
import org.saxing.featuretoggle.pattern.propertiesversion.PropertiesFeatureToggleVersion;
import org.saxing.featuretoggle.pattern.tieredversion.TieredFeatureToggleVersion;
import org.saxing.featuretoggle.user.User;
import org.saxing.featuretoggle.user.UserGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * main
 *
 * @author saxing 2019/1/10 9:34
 */
public class Aa001546FeatureToggleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001546FeatureToggleApplication.class);

    public static void main(String[] args) {
        final Properties properties = new Properties();
        properties.put("enhancedWelcome", true);
        Service service = new PropertiesFeatureToggleVersion(properties);
        final String welcomeMessage = service.getWelcomeMessage(new User("Jamie No Code"));
        LOGGER.info(welcomeMessage);

        // ---------------------------------------------

        final Properties turnedOff = new Properties();
        turnedOff.put("enhancedWelcome", false);
        Service turnedOffService = new PropertiesFeatureToggleVersion(turnedOff);
        final String welcomeMessageturnedOff = turnedOffService.getWelcomeMessage(new User("Jamie No Code"));
        LOGGER.info(welcomeMessageturnedOff);

        // --------------------------------------------

        Service service2 = new TieredFeatureToggleVersion();

        final User paidUser = new User("Jamie Coder");
        final User freeUser = new User("Alan Defect");

        UserGroup.addUserToPaidGroup(paidUser);
        UserGroup.addUserToFreeGroup(freeUser);

        final String welcomeMessagePaidUser = service2.getWelcomeMessage(paidUser);
        final String welcomeMessageFreeUser = service2.getWelcomeMessage(freeUser);
        LOGGER.info(welcomeMessageFreeUser);
        LOGGER.info(welcomeMessagePaidUser);
    }

}

