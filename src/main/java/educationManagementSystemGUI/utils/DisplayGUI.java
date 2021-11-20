package educationManagementSystemGUI.utils;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class DisplayGUI {
    public static JPanel displayUserGUI(JSONObject userInfo) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 0));
        JLabel nameLabel = new JLabel("username: " + userInfo.get("username"));
        JLabel emailLabel = new JLabel("email: " + userInfo.get("email"));
        panel.add(nameLabel);
        panel.add(emailLabel);

        return panel;
    }

    public static JPanel displayAllUsersGUI(JSONArray userArray) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 0));
        for (Object object : userArray) {

            JSONObject jsonObject = new JSONObject(object.toString());
            JSONObject role = new JSONObject(jsonObject.getJSONArray("roles").get(0).toString());

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("username: " + jsonObject.get("username"));
            stringBuilder.append(", ");
            stringBuilder.append("email: " + jsonObject.get("email"));
            stringBuilder.append(", ");
            stringBuilder.append(role.get("name"));

            JLabel label = new JLabel(stringBuilder.toString());
            panel.add(label);
        }

        return panel;
    }


}
