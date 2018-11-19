package org.saxing.collectionpipeline;

import java.util.*;

/**
 *
 * Imperative-style programming to iterate over the list and get the names of
 *  * cars made later than the year 2000. We then sort the models in ascending
 *  * order by year.
 *  *
 *  * <p>As you can see, there's a lot of looping in this code. First, the
 *  * getModelsAfter2000UsingFor method takes a list of cars as its parameter. It
 *  * extracts or filters out cars made after the year 2000, putting them into a
 *  * new list named carsSortedByYear. Next, it sorts that list in ascending order
 *  * by year-of-make. Finally, it loops through the list carsSortedByYear to get
 *  * the model names and returns them in a list.
 *  *
 *  * <p>This short example demonstrates what I call the effect of statements. While
 *  * functions and methods in general can be used as expressions, the {@link Collections}
 *  * sort method doesn't return a result. Because it is used as a statement, it
 *  * mutates the list given as argument. Both of the for loops also mutate lists
 *  * as they iterate. Being statements, that's just how these elements work. As a
 *  * result, the code contains unnecessary garbage variables
 *
 * @author saxing  2018/11/19 9:17
 */
public class ImperativeProgramming {

    private ImperativeProgramming() {
    }

    /**
     * Method to return the car models built after year 2000 using for loops.
     *    * @param cars {@link List} of {@link Car} to iterate over
     *    * @return {@link List} of {@link String} of car models built after year 2000
     * @param cars
     * @return
     *
     * @author saxing  2018/11/19 9:22
     */
    public static List<String> getModelsAfter2000(List<Car> cars){
        List<Car> carsSortedByYear = new ArrayList<>();

        for (Car car : cars){
            if (car.getYear() > 2000){
                carsSortedByYear.add(car);
            }
        }

        Collections.sort(carsSortedByYear, new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                return new Integer(car1.getYear()).compareTo(new Integer(car2.getYear()));
            }
        });

        List<String> models = new ArrayList<>();

        for (Car car : carsSortedByYear){
            models.add(car.getModel());
        }

        return models;
    }

    /**
     * Method to group cars by category using for loops
     *
     * @param cars {@link List} of {@link Car} to be used for grouping
     * @return {@link Map} with category as key and cars belonging to that category as value
     *
     * @author saxing  2018/11/19 9:22
     */
    public static Map<Category, List<Car>> getGroupingOfCarsByCategory(List<Car> cars){
        Map<Category, List<Car>> groupingByCategory = new HashMap<>();
        for (Car car : cars){
            if (groupingByCategory.containsKey(car.getCategory())){
                groupingByCategory.get(car.getCategory()).add(car);
            } else {
                List<Car> categoryCars = new ArrayList<>();
                categoryCars.add(car);
                groupingByCategory.put(car.getCategory(), categoryCars);
            }
        }
        return groupingByCategory;
    }

    /**
     * Method to get all Sedan cars belonging to a group of persons sorted by year of manufacture using for loops
     *
     * @param persons {@link List} of {@link Person} to be used
     * @return {@link List} of {@link Car} to belonging to the group
     */
    public static List<Car> getSedanCarsOwnedSortedByDate(List<Person> persons){
        List<Car> cars = new ArrayList<>();
        for (Person person: persons) {
            cars.addAll(person.getCars());
        }

        List<Car> sedanCars = new ArrayList<>();
        for (Car car: cars) {
            if (Category.SEDAN.equals(car.getCategory())) {
                sedanCars.add(car);
            }
        }

        sedanCars.sort(new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getYear() - o2.getYear();
            }
        });

        return sedanCars;
    }

}
