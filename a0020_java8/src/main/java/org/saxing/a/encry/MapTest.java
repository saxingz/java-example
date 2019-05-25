package org.saxing.a.encry;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        Map<String, Collection<String>> headers = new HashMap<>();
        headers.put("common_cs1", Collections.singleton("aaa"));
        headers.put("common_cs2", Collections.singleton("bbb"));
        headers.put("common_cs3", Collections.singleton("ccc"));

        System.out.println(headers);
    }

}
