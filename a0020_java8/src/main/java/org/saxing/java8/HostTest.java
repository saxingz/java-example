package org.saxing.java8;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * host test
 *
 * @author saxing 2020/5/28 14:52
 */
public class HostTest {

    public static String getHost(String link) {
        URL url;
        String host = "";
        try {
            url = new URL(link);
            host = url.getHost();
        } catch (MalformedURLException e) {
        }
        return host;
    }
    public static void main(String[] args){
//        System.out.println(getHost("https://jingyan.baidu.com/article/ca41422fc76c4a1eae99ed9f.html"));
        String url = "http:/resource-zh.7kid.com.cn/images/82/teachplan/2019/10/12/fd3d2187-2178-48c5-b0cb-94030f429dc3_0.jpg";
        url = url.replaceAll(":/", "://");
        System.out.println(url);
    }

}
