package org.saxing.reactor;

import reactor.core.publisher.Flux;

public class ReactorTest {

    public static void main(String[] args) {
        Flux.range(0, 100).subscribe(System.out::println);
    }

}
