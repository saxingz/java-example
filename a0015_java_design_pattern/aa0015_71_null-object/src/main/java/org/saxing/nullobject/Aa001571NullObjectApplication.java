package org.saxing.nullobject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/5/5 15:13
 */
public class Aa001571NullObjectApplication {

    public static void main(String[] args) {
        Node root =
                new NodeImpl("1",
                        new NodeImpl("11", new NodeImpl("111", NullNode.getInstance(),NullNode.getInstance()), NullNode.getInstance()),
                        new NodeImpl("12", NullNode.getInstance(), new NodeImpl("122", NullNode.getInstance(), NullNode.getInstance())));

        root.walk();
    }

}
