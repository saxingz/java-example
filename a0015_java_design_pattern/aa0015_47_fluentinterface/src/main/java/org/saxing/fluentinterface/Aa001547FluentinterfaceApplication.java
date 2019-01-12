package org.saxing.fluentinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

/**
 * main
 *
 * @author saxing 2019/1/12 11:00
 */
public class Aa001547FluentinterfaceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001547FluentinterfaceApplication.class);

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.addAll(Arrays.asList(1, -61, 14, -22, 18, -87, 6, 64, -82, 26, -98, 97, 45, 23, 2,
                -68, 45));

        prettyPrint("The initial list contains: ", integerList);

    }


    private static <E> void prettyPrint(String prefix, Iterable<E> iterable){
        prettyPrint(", ", prefix, iterable);
    }

    private static <E> void prettyPrint(String delimiter, String prefix,
                                        Iterable<E> iterable){
        StringJoiner stringJoiner = new StringJoiner(delimiter, prefix, ".");
        Iterator<E> iterator = iterable.iterator();
        while (iterator.hasNext()){
            stringJoiner.add(iterator.next().toString());
        }

        LOGGER.info(stringJoiner.toString());
    }
}

