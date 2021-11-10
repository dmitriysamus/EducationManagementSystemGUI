package educationManagementSystemGUI.cabinets.user;

import educationManagementSystemGUI.cabinets.AdminCabinet;
import educationManagementSystemGUI.utils.HttpDeleteUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCabinetUserDelete extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JLabel userLabel = new JLabel();
    JButton showButton = new JButton("LOGOUT");

    JLabel userDeleteIdIdLabel = new JLabel("USER ID FOR DELETE");
    JTextField userDeleteTextIdField = new JTextField();
    JButton userDeleteButton = new JButton("Delete User"); // при http DELETE запросе по адресу .../api/auth/users/{id}

    UserCabinetUserDelete(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        showButton.setBounds(500, 500, 100, 30);
        userLabel.setBounds(50, 150, 100, 30);

        userDeleteIdIdLabel.setBounds(50, 100, 100, 30);
        userDeleteTextIdField.setBounds(150, 100, 150, 30);
        userDeleteButton.setBounds(350, 150, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(showButton);
        container.add(userDeleteIdIdLabel);
        container.add(userDeleteTextIdField);
        container.add(userDeleteButton);
    }

    public void addActionEvent() {
        showButton.addActionListener(this);
        userDeleteButton.addActionListener(this);
    }

    public static void showDeleteUserForm(JSONObject userInfo, JSONObject response) {
        UserCabinetUserDelete frame = new UserCabinetUserDelete(userInfo, response);
        frame.setTitle("User Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of Delete Users Info button /api/auth/users/{id}
        if (e.getSource() == userDeleteButton) {
            String userIdText;
            userIdText = userDeleteTextIdField.getText();

            String url = "http://localhost:8080/api/auth/users/" + userIdText;
            JSONObject response = HttpDeleteUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            dispose();
            AdminCabinet.showCabinetForm(userInfo, response);
        }

    }

}
