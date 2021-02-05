package org.saxing.age;

import java.net.URLEncoder;

public class UrlTest {

    public static void main(String[] args) {
        String url = "";
        String encode = URLEncoder.encode(url);
        System.out.println(encode);
    }

}
