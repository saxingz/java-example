package org.saxing.a0040_sensitive.entity;

import java.io.Serializable;

public class BasicCode implements Serializable {
    private static final long serialVersionUID = -6541047214838265157L;

    private String name;
    private Integer code;

    public BasicCode() {
    }

    public BasicCode(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
