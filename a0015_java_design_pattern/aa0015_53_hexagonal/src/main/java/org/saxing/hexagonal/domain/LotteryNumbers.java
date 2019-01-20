package org.saxing.hexagonal.domain;

import com.google.common.base.Joiner;

import java.util.*;

/**
 * Value object representing lottery numbers. This lottery uses sets of 4 numbers. The numbers must be unique and
 * between 1 and 20.
 *
 * @author saxing 2019/1/20 11:02
 */
public class LotteryNumbers {

    private final Set<Integer> numbers;

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 20;
    public static final int NUM_NUMBERS = 4;

    /**
     * Constructor. Creates random lottery numbers.
     */
    private LotteryNumbers() {
        numbers = new HashSet<>();
        generateRandomNumbers();
    }

    /**
     * Constructor. Uses given numbers.
     */
    private LotteryNumbers(Set<Integer> givenNumbers) {
        numbers = new HashSet<>();
        numbers.addAll(givenNumbers);
    }

    /**
     * @return random LotteryNumbers
     *
     * @return
     */
    public static LotteryNumbers createRandom(){
        return new LotteryNumbers();
    }

    /**
     * @return given LotteryNumbers
     */
    public static LotteryNumbers create(Set<Integer> givenNumbers) {
        return new LotteryNumbers(givenNumbers);
    }

    /**
     *
     * @return lottery numbers
     */
    public Set<Integer> getNumbers(){
        return Collections.unmodifiableSet(numbers);
    }

    /**
     * @return numbers as comma separated string
     */
    public String getNumbersAsString() {
        return Joiner.on(',').join(numbers);
    }

    private void generateRandomNumbers() {
        numbers.clear();
        RandomNumberGenerator generator = new RandomNumberGenerator(MIN_NUMBER, MAX_NUMBER);
        while (numbers.size() < NUM_NUMBERS) {
            int num = generator.nextInt();
            numbers.add(num);
        }
    }

    @Override
    public String toString() {
        return "LotteryNumbers{" + "numbers=" + numbers + '}';
    }

    /**
     *
     * Helper class for generating random numbers.
     *
     */
    private static class RandomNumberGenerator {

        private PrimitiveIterator.OfInt randomIterator;

        public RandomNumberGenerator(int min, int max) {
            randomIterator = new Random().ints(min, max + 1).iterator();
        }

        /**
         * @return a random number in the range (min, max)
         *
         * @return
         */
        public int nextInt(){
            return randomIterator.nextInt();
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numbers == null) ? 0 : numbers.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LotteryNumbers other = (LotteryNumbers) obj;
        if (numbers == null) {
            if (other.numbers != null) {
                return false;
            }
        } else if (!numbers.equals(other.numbers)) {
            return false;
        }
        return true;
    }
}
