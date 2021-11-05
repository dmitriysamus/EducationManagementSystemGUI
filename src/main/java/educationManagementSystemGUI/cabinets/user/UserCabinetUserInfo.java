package educationManagementSystemGUI.cabinets.user;

import educationManagementSystemGUI.forms.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class UserCabinetUserInfo extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel();
    JButton showButton = new JButton("LOGOUT");

    UserCabinetUserInfo() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
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

    public static void showUserCabinetForm(Map userMap) {
        UserCabinetUserInfo frame = new UserCabinetUserInfo();
        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout());
        JLabel label = new JLabel();
        label.setText((String) userMap.get("username"));
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