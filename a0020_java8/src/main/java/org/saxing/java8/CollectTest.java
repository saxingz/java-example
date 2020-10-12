package org.saxing.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author saxing 2020/10/12 15:04
 */
public class CollectTest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Book {
        private String name;
        private int releaseYear;
        private String isbn;
    }


    public static void main(String[] args) {

        new CollectTest().testAdd();
    }

    private void testAdd(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1958, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));

        Map<Integer, Book> collect = bookList.stream().collect(Collectors.toMap(Book::getReleaseYear, t -> t));
        System.out.println(collect);
    }

}
