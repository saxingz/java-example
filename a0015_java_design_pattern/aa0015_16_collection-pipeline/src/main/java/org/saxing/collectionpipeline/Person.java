package org.saxing.collectionpipeline;

import java.util.List;

/**
 * A Person class that has the list of cars that the person owns and use.
 * 
 * @author saxing  2018/11/19 9:16
 */
public class Person {

    private List<Car> cars;

    public Person(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
