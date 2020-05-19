package org.saxing.java8;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author saxing 2020/5/18 20:23
 */
public class MapTest {

    public static void main(String[] args) {
        test2();
    }

    public static void test2(){
        String uuid = UUID.randomUUID().toString();

        System.out.println(uuid);
        boolean isAlphanumeric = StringUtils.isAlphanumeric(uuid);
        System.out.println(isAlphanumeric);

        uuid = uuid.replaceAll("-", "");
        System.out.println(uuid);
        isAlphanumeric = StringUtils.isAlphanumeric(uuid);
        System.out.println(isAlphanumeric);

        uuid += "F";
        System.out.println(uuid);
        isAlphanumeric = StringUtils.isAlphanumeric(uuid);
        System.out.println(isAlphanumeric);

        uuid += " ";
        System.out.println(uuid);
        isAlphanumeric = StringUtils.isAlphanumeric(uuid);
        System.out.println(isAlphanumeric);
    }

    public static void main2(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a1");
        map.put("b", "b1");
        map.put("c", "c1");

        Map<String, String> mapAll = new HashMap<>();
        mapAll.putAll(map);

        map = new HashMap<>();
        map.put("a2", "a1");
        map.put("b3", "b1");
        map.put("c4", "c1");

        mapAll.putAll(map);

        System.out.println(mapAll);
    }

}
