package org.saxing.a.java8;

public final class InitTest {

    public static InitTest createInitTest(String param){
        return new InitTest(param);
    }

    final String name;

    InitTest(String param){
        this.name = param;
    }

    public String getName() {
        return name;
    }

}
