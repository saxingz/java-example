package org.saxing.thinking.in.spring.ioc.overview.domain;

import org.saxing.thinking.in.spring.ioc.overview.enums.City;

/**
 * user
 *
 * @author saxing 2020/11/17 23:08
 */
public class User {

    private Long id;

    private String name;

    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("saxing");
        return user;
    }

}
