package org.saxing.reactor;

import reactor.core.publisher.Flux;

/**
 * reactor test
 *
 * @author saxing 2020/7/12 23:47
 */
public class ReactorTest {

    public static void main(String[] args) {
        Flux.range(0, 100).subscribe(System.out::println);
        Flux.range(1000, 100).subscribe(System.out::println);
    }

}
