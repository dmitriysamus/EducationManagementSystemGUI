package educationManagementSystemGUI.cabinets;

import educationManagementSystemGUI.forms.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCabinet extends JFrame implements ActionListener {

    Container container = getContentPane();

    JLabel userLabel = new JLabel();
    JButton showButton = new JButton("LOGOUT");

    UserCabinet() {
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

    public static void showUserCabinetForm() {
        UserCabinet frame = new UserCabinet();
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
        //Coding Part of SHOW button
        if (e.getSource() == showButton) {
            dispose();
            LoginForm.showLoginForm();

        }
    }
}