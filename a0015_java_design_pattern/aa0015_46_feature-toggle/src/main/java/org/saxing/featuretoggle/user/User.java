package org.saxing.featuretoggle.user;

/**
 * user
 *
 * @author saxing 2019/1/10 9:26
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
