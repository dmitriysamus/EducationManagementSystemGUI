package educationManagementSystemGUI.cabinets;

import educationManagementSystemGUI.forms.LoginForm;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCabinet extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JLabel userLabel = new JLabel();
    JButton showButton = new JButton("LOGOUT");

    JButton groupAddButton = new JButton("Add to Group");
    JButton groupDropButton = new JButton("Leave Group");

    JButton userInfoButton = new JButton("Show User Info"); // при http GET запросе по адресу .../api/auth/users/getUserInfo
    JButton userInfoChangeButton = new JButton("Change User Info"); // при http PUT запросе по адресу .../api/auth/users/{id}

    UserCabinet(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    UserCabinet() {
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        showButton.setBounds(260, 500, 100, 30);
        userLabel.setBounds(50, 150, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(showButton);
    }

    public void addActionEvent() {
        showButton.addActionListener(this);
    }

    public static void showCabinetForm(JSONObject userInfo, JSONObject response) {
        UserCabinet frame = new UserCabinet(userInfo, response);
        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout());
        JLabel label = new JLabel();
        label.setText((String) response.get("username"));
        container.add(label);

        frame.setTitle("User Cabinet");
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
    }
}