package org.saxing.featuretoggle.pattern.tieredversion;

import org.saxing.featuretoggle.pattern.Service;
import org.saxing.featuretoggle.user.User;
import org.saxing.featuretoggle.user.UserGroup;

/**
 * This example of the Feature Toogle pattern shows how it could be implemented based on a {@link User}. Therefore
 * showing its use within a tiered application where the paying users get access to different content or
 * better versions of features. So in this instance a {@link User} is passed in and if they are found to be
 * on the {@link UserGroup#isPaid(User)} they are welcomed with a personalised message. While the other is more
 * generic. However this pattern is limited to simple examples such as the one below.
 *
 * @author saxing 2019/1/10 9:33
 */
public class TieredFeatureToggleVersion implements Service {


    @Override
    public String getWelcomeMessage(User user) {
        if (UserGroup.isPaid(user)) {
            return "You're amazing " + user + ". Thanks for paying for this awesome software.";
        }

        return "I suppose you can use this software.";
    }

    @Override
    public boolean isEnhanced() {
        return true;
    }
}
