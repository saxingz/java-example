package org.saxing.a.java8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapTest2 {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);

        Set<String> keys = new HashSet<>(map.keySet());

        for (String key : keys){
            if (map.get(key) == 4){
                map.remove(key);
            }
        }

        System.out.println(map);

    }

}
