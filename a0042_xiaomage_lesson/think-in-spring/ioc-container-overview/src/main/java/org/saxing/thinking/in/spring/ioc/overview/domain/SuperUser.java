package org.saxing.thinking.in.spring.ioc.overview.domain;

import org.saxing.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * super user
 *
 * @author saxing 2020/11/17 23:23
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
