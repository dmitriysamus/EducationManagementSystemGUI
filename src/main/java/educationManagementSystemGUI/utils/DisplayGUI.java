package educationManagementSystemGUI.utils;

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


}
