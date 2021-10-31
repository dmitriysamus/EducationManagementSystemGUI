package educationManagementSystemGUI.utils;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.swing.*;
import java.security.Principal;

public class HttpClient {

    public static HttpResponse httpRequest(String url, StringEntity params) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        // request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + finalToken);

        try {
            HttpPost request = new HttpPost(url);
            request.setHeader(HttpHeaders.CONTENT_TYPE,"application/json");
            request.setEntity(params);
            response = httpClient.execute(request);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return response;
    }


}
