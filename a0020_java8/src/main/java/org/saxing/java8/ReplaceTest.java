package org.saxing.java8;

/**
 * replace test
 *
 * @author saxing 2020/6/1 14:33
 */
public class ReplaceTest {

    public static void main(String[] args) {
        String oldImageUrl = "abcdf?";
        oldImageUrl = oldImageUrl.replaceAll("\\?", "");

        System.out.println(oldImageUrl);

    }

}
