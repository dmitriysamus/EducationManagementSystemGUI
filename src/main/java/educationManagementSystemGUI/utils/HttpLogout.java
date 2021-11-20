package educationManagementSystemGUI.utils;

import org.json.JSONObject;


public class HttpLogout {

    public static JSONObject httpLogout(JSONObject userInfo) {
        String url = "http://localhost:8080/api/auth/logout";
        String jwt = (String) userInfo.get("accessToken");
        JSONObject response = HttpGetUtil.httpRequest(
                url,
                jwt).getJSONObject(0);

        return response;
    }
}
