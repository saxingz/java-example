package org.saxing.collectionpipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * main
 *
 * @author saxing  2018/11/22 22:19
 */
//@SpringBootApplication
public class Aa001516CollectionPipelineApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(Aa001516CollectionPipelineApplication.class, args);
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001516CollectionPipelineApplication.class);

    public static void main(String[] args) {
        List<Car> cars = CarFactory.createCars();

        List<String> modelsImperative = ImperativeProgramming.getModelsAfter2000(cars);
        LOGGER.info(modelsImperative.toString());

        List<String> modelsFunctional = FunctionalProgramming.getModelsAfter2000(cars);
        LOGGER.info(modelsFunctional.toString());

        Map<Category, List<Car>> groupingByCategoryImperative = ImperativeProgramming.getGroupingOfCarsByCategory(cars);
        LOGGER.info(groupingByCategoryImperative.toString());

        Map<Category, List<Car>> groupingByCategoryFunctional = FunctionalProgramming.getGroupingOfCarsByCategory(cars);
        LOGGER.info(groupingByCategoryFunctional.toString());

        Person john = new Person(cars);

        List<Car> sedansOwnedImperative = ImperativeProgramming.getSedanCarsOwnedSortedByDate(Arrays.asList(john));
        LOGGER.info(sedansOwnedImperative.toString());

        List<Car> sedansOwnedFunctional = FunctionalProgramming.getSedanCarsOwnedSortedByDate(Arrays.asList(john));
        LOGGER.info(sedansOwnedFunctional.toString());

    }

}
