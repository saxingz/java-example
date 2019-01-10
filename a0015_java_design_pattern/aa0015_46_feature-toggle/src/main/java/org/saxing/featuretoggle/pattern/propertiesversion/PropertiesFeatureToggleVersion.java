package org.saxing.featuretoggle.pattern.propertiesversion;

import org.saxing.featuretoggle.pattern.Service;
import org.saxing.featuretoggle.user.User;

import java.util.Properties;

/**
 * This example of the Feature Toogle pattern is less dynamic version
 *
 * @author saxing 2019/1/10 9:30
 */
public class PropertiesFeatureToggleVersion implements Service {

    private boolean isEnhanced;

    public PropertiesFeatureToggleVersion(final Properties properties) {
        if (properties == null){
            throw new IllegalArgumentException("No Properties Provided.");
        }else {
            try {
                isEnhanced = (boolean) properties.get("enhancedWelcome");
            }catch (Exception e){
                throw new IllegalArgumentException("Invalid Enhancement Settings Provided.");
            }
        }
    }

    @Override
    public String getWelcomeMessage(final User user) {
        if (isEnhanced()) {
            return "Welcome " + user + ". You're using the enhanced welcome message.";
        }

        return "Welcome to the application.";
    }

    @Override
    public boolean isEnhanced() {
        return isEnhanced;
    }
}
