package educationManagementSystemGUI.utils;

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


    public static Object displayAllGroupsGUI(JSONArray groupArray) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 0));
        for (Object object : groupArray) {

            JSONObject jsonObject = new JSONObject(object.toString());

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("group_num: " + jsonObject.get("group_num"));
            stringBuilder.append(", ");
            stringBuilder.append("teacher_id: " + jsonObject.get("teacher_id"));
            stringBuilder.append(", ");
            stringBuilder.append("users_id: " + jsonObject.get("users_id"));
            stringBuilder.append(", ");
            stringBuilder.append("lessons: " + jsonObject.get("lessons"));

            JLabel label = new JLabel(stringBuilder.toString());
            panel.add(label);
        }

        return panel;
    }

    public static Object displayAllRatesTeacherGUI(JSONArray ratesArray) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 0));
        for (Object object : ratesArray) {

            JSONObject jsonObject = new JSONObject(object.toString());

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("lesson: " + jsonObject.get("lesson"));
            stringBuilder.append(", ");
            stringBuilder.append("user_id: " + jsonObject.get("user_id"));
            stringBuilder.append(", ");
            stringBuilder.append("grade: " + jsonObject.get("grade"));

            JLabel label = new JLabel(stringBuilder.toString());
            panel.add(label);
        }

        return panel;
    }

}
