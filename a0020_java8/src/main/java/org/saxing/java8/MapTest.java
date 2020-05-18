package org.saxing.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * @author saxing 2020/5/18 20:23
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a1");
        map.put("b", "b1");
        map.put("c", "c1");

        Map<String, String> mapAll = new HashMap<>();
        map.putAll(map);

        map = new HashMap<>();
        map.put("a2", "a1");
        map.put("b3", "b1");
        map.put("c4", "c1");

        map.putAll(map);

        System.out.println(mapAll);
    }

}
