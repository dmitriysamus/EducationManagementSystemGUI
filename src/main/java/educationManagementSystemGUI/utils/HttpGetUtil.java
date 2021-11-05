package educationManagementSystemGUI.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HttpGetUtil {

    public static JSONObject httpRequest(String url, String token) {

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10 * 1000).build();
        JSONObject result = null;
        HttpGet httpGet = new HttpGet(url);
        if (null != token) {
            httpGet.setHeader("Authorization", "Bearer " + token);
        }

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
             CloseableHttpResponse response = httpClient.execute(httpGet)
        ){
            HttpEntity entity = response.getEntity();
            String stringResponse = EntityUtils.toString(entity, "UTF-8");
            if (stringResponse.startsWith("[") && stringResponse.endsWith("]")) {
                stringResponse = stringResponse.substring(1, stringResponse.length() - 1);
            }
            System.out.println(stringResponse);
            result = new JSONObject(stringResponse);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
