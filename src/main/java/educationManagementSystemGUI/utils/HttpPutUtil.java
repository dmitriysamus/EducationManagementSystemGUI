package educationManagementSystemGUI.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Класс {@link HttpPutUtil} утилита для обработки запросов
 * метода PUT.
 *
 * @author habatoo
 * @version 0.001
 */
public class HttpPutUtil {

    /**
     * Метод {@link HttpPutUtil#httpRequest(String, String, String)}
     * добавляет токен в заголовок запроса и принимает ответ.
     * @param url адрес запроса
     * @param JSON_STRING json параметры запроса в строковом виде
     * @param token токен запроса в строковом виде
     * @return возвращает JSONObject с параметрами в виде файла json
     */
    public static JSONObject httpRequest(String url, String JSON_STRING, String token) {

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10 * 1000).build();
        JSONObject result = null;
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(stringEntity);
        if (null != token) {
            httpPut.setHeader("Authorization", "Bearer " + token);
        }

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
             CloseableHttpResponse response = httpClient.execute(httpPut)
        ){
            HttpEntity entity = response.getEntity();
            String stringResponse = EntityUtils.toString(entity, "UTF-8");
            result = new JSONObject(stringResponse);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
