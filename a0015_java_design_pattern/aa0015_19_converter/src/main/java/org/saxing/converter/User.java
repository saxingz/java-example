package org.saxing.converter;

import java.util.Objects;

/**
 * user class
 *
 * @author saxing  2018/11/26 23:28
 */
public class User {

    private String firstName;
    private String lastName;
    private String isActive;
    private String userId;

    public User(String firstName, String lastName, String isActive, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return isActive == user.isActive && Objects.equals(firstName, user.firstName) && Objects
                .equals(lastName, user.lastName) && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, isActive, userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive='" + isActive + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
