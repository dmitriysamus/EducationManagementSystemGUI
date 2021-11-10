package educationManagementSystemGUI.forms;

import educationManagementSystemGUI.cabinets.AdminCabinet;
import educationManagementSystemGUI.cabinets.TeacherCabinet;
import educationManagementSystemGUI.cabinets.UserCabinet;
import educationManagementSystemGUI.utils.HttpPostUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JButton registerButton = new JButton("REGISTER");
    JCheckBox showPassword = new JCheckBox("Show Password");

    LoginForm() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(150, 300, 100, 30);
        registerButton.setBounds(250, 300, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(registerButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    public static void showLoginForm() {
        LoginForm frame = new LoginForm();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            String token = null;

            String url = "http://localhost:8080/api/auth/login";
            String JSON_STRING = "{\"username\":\"" + userText + "\",\"password\":\"" + pwdText + "\"} ";
            JSONObject response = HttpPostUtil.httpRequest(url, JSON_STRING, token);
            System.out.println(response);

            if (null != response && response.get("tokenType").equals("Bearer")) {
                JOptionPane.showMessageDialog(this, "Login Successful");

                String role = (String) response.getJSONArray("roles").get(0);
                if (role.equals("ROLE_ADMIN")) {
                    dispose();
                    AdminCabinet.showCabinetForm(response, response);
                } else if (role.equals("ROLE_TEACHER")) {
                    dispose();
                    TeacherCabinet.showCabinetForm(
                            response, response);
                } else if (role.equals("ROLE_USER")) {
                    dispose();
                    UserCabinet.showCabinetForm(
                            response, response);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }

        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }

        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }

        //Coding Part of REGISTER button
        if (e.getSource() == registerButton) {
            dispose();
            RegisterForm.showRegisterForm();
        }
    }

}