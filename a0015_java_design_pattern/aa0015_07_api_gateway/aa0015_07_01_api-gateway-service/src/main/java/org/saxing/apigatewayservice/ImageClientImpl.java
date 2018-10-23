package org.saxing.apigatewayservice;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * image client implement
 *
 * @author saxing  2018/10/23 21:48
 */
@Component
public class ImageClientImpl implements ImageClient {

    /**
     * Makes a simple HTTP Get request to the Image microservice
     *
     * @return the path of image
     */
    @Override
    public String getImagePath() {
        String response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:50005/image-path");
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                response = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
