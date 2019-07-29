package org.saxing.ctodown;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestDown {

    static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.DAYS).build();


    public static void main(String[] args) throws IOException, InterruptedException {
        String baseUrl = "";

        for (int i = 322857; i < 487333; i++) {
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("current id : " + i);
            String url = baseUrl.replace("${id}", i + "");
            System.out.println("url: " + url);
            String result = run(url);
            System.out.println(result);

            if (!"nobuy".equals(result)){
                System.out.println("find success id: " + i);
                break;
            }
        }


    }

    static String run(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
