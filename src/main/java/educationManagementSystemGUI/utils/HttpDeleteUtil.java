package educationManagementSystemGUI.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Класс {@link HttpDeleteUtil} утилита для обработки запросов
 * метода DELETE.
 *
 * @author habatoo
 * @version 0.001
 */
public class HttpDeleteUtil {

    /**
     * Метод {@link HttpDeleteUtil#httpRequest(String, String)}
     * добавляет токен в заголовок запроса и принимает ответ.
     * @param url адрес запроса
     * @param token токен запроса в строковом виде
     * @return возвращает JSONObject с параметрами в виде файла json
     */
    public static JSONObject httpRequest(String url, String token) {

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10 * 1000).build();
        JSONObject result = null;
        HttpDelete httpDelete = new HttpDelete(url);
        if (null != token) {
            httpDelete.setHeader("Authorization", "Bearer " + token);
        }

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
             CloseableHttpResponse response = httpClient.execute(httpDelete)
        ){
            HttpEntity entity = response.getEntity();
            String stringResponse = EntityUtils.toString(entity, "UTF-8");
            if (stringResponse.startsWith("[") && stringResponse.endsWith("]")) {
                stringResponse = stringResponse.substring(1, stringResponse.length() - 1);
            }
            result = new JSONObject(stringResponse);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
