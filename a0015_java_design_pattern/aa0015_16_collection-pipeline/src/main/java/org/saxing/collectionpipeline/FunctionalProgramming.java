package org.saxing.collectionpipeline;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  * Iterating and sorting with a collection pipeline
 *  *
 *  * <p>In functional programming, it's common to sequence complex operations through
 *  * a series of smaller modular functions or operations. The series is called a
 *  * composition of functions, or a function composition. When a collection of
 *  * data flows through a function composition, it becomes a collection pipeline.
 *  * Function Composition and Collection Pipeline are two design patterns
 *  * frequently used in functional-style programming.
 *  *
 *  * <p>Instead of passing a lambda expression to the map method, we passed the
 *  * method reference Car::getModel. Likewise, instead of passing the lambda
 *  * expression car -> car.getYear() to the comparing method, we passed the method
 *  * reference Car::getYear. Method references are short, concise, and expressive.
 *  * It is best to use them wherever possible.
 *
 * @author saxing  2018/11/22 21:59
 */
public class FunctionalProgramming {

    private FunctionalProgramming() {
    }

    /**
     * Method to get models using for collection pipeline.
     *
     * @param cars {@link List} of {@link Car} to be used for filtering
     * @return {@link List} of {@link String} representing models built after year 2000
     */
    public static List<String> getModelsAfter2000(List<Car> cars){
        return cars.stream().filter(car -> car.getYear() > 2000)
                .sorted(Comparator.comparing(Car::getYear))
                .map(Car::getModel).collect(Collectors.toList());
    }





}
