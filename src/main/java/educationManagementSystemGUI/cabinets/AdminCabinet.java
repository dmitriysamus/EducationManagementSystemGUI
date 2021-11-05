package educationManagementSystemGUI.cabinets;

import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpDeleteUtil;
import educationManagementSystemGUI.utils.HttpGetUtil;
import educationManagementSystemGUI.utils.HttpPostUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCabinet extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JLabel userLabel = new JLabel();
    JButton showButton = new JButton("Logout");

//    JButton groupAddButton = new JButton("Add Teacher to Group");
//    JButton groupCreateButton = new JButton("Create Group");
//    JButton groupDropButton = new JButton("Delete Group");

    JButton showAllUsersButton = new JButton("Show All Users"); // при http GET запросе по адресу .../api/auth/users
    JButton userInfoButton = new JButton("Show User Info"); // при http GET запросе по адресу .../api/auth/users/getUserInfo
//    JButton userInfoChangeButton = new JButton("Change User Info"); // при http PUT запросе по адресу .../api/auth/users/{id}

    JLabel userIdDeleteLabel = new JLabel("User ID for delete");
    JTextField userIdDeleteTextField = new JTextField();
    JButton userDeleteButton = new JButton("Delete User"); // при http DELETE запросе по адресу .../api/auth/users/{id}
//    JButton tokenDeleteButton = new JButton("Delete old tokens"); // при http DELETE запросе по адресу .../api/auth/users/tokens - очищает базу от токенов с истекшим сроком

    AdminCabinet(JSONObject userInfo, JSONObject response) {
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

//        groupAddButton.setBounds(90, 150, 100, 30);
//        groupCreateButton.setBounds(130, 150, 100, 30);
//        groupDropButton.setBounds(170, 150, 100, 30);
        showAllUsersButton.setBounds(210, 150, 100, 30);
        userInfoButton.setBounds(250, 150, 100, 30);
//        userInfoChangeButton.setBounds(290, 150, 100, 30);
        userDeleteButton.setBounds(330, 150, 100, 30);
        userIdDeleteLabel.setBounds(350, 150, 100, 30);
        userIdDeleteTextField.setBounds(380, 350, 100, 30);
//        tokenDeleteButton.setBounds(370, 150, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(showButton);

//        container.add(groupAddButton);
//        container.add(groupCreateButton);
//        container.add(groupDropButton);
        container.add(showAllUsersButton);
        container.add(userInfoButton);
//        container.add(userInfoChangeButton);
        container.add(userDeleteButton);
        container.add(userIdDeleteLabel);
        container.add(userIdDeleteTextField);
//        container.add(tokenDeleteButton);
    }

    public void addActionEvent() {
        showButton.addActionListener(this);
        showAllUsersButton.addActionListener(this);
        userInfoButton.addActionListener(this);
        userDeleteButton.addActionListener(this);
    }

    public static void showCabinetForm(JSONObject userInfo, JSONObject response) {
        AdminCabinet frame = new AdminCabinet(userInfo, response);
        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout());
        JLabel label = new JLabel();
        label.setText(userInfo.toString());
        container.add(label);

        frame.setTitle("Admin Cabinet");
        frame.setVisible(true);
        frame.getContentPane().add(label);
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
        //Coding Part of SHOW button
        if (e.getSource() == showButton) {
            dispose();
            LoginForm.showLoginForm();
        }

        //Coding Part of Show All Users button /api/auth/users
        if (e.getSource() == showAllUsersButton) {
            String url = "http://localhost:8080/api/auth/users";
            JSONObject response = HttpGetUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            dispose();
            AdminCabinet.showCabinetForm(userInfo, response);
        }

        //Coding Part of Show Users Info button /api/auth/users/getUserInfo
        if (e.getSource() == userInfoButton) {
            String url = "http://localhost:8080/api/auth/users/getUserInfo";
            JSONObject response = HttpGetUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            dispose();
            AdminCabinet.showCabinetForm(userInfo, response);
        }

        //Coding Part of Delete Users Info button /api/auth/users/{id}
        if (e.getSource() == userDeleteButton) {
            String userIdText;
            userIdText = userIdDeleteTextField.getText();

            String url = "http://localhost:8080/api/auth/users/" + userIdText;
            JSONObject response = HttpDeleteUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            dispose();
            AdminCabinet.showCabinetForm(userInfo, response);
        }

    }
}
