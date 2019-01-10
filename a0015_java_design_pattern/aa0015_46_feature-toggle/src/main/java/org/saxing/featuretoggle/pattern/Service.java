package org.saxing.featuretoggle.pattern;

import org.saxing.featuretoggle.user.User;

/**
 * Simple interfaces to allow the calling of the method to generate the welcome message for a given user. While there is
 * a helper method to gather the the status of the feature toggle. In some cases there is no need for the
 * {@link Service#isEnhanced()} in {@link TieredFeatureToggleVersion}
 * where the toggle is determined by the actual {@link User}.
 *
 *
 * @author saxing 2019/1/10 9:25
 */
public interface Service {

    String getWelcomeMessage(User user);

    boolean isEnhanced();

}
