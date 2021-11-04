package educationManagementSystemGUI.forms;

import educationManagementSystemGUI.cabinets.UserCabinet;
import educationManagementSystemGUI.utils.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisterForm extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel emailLabel = new JLabel("EMAIL");
    JLabel passwordLabel = new JLabel("PASSWORD");

    JTextField userTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton registerButton = new JButton("REGISTER");
    JButton resetButton = new JButton("RESET");
    JButton loginButton = new JButton("LOGIN");
    JCheckBox showPassword = new JCheckBox("Show Password");

    RegisterForm() {
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
        emailLabel.setBounds(50, 185, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        emailTextField.setBounds(150, 185, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        registerButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(150, 300, 100, 30);
        loginButton.setBounds(250, 300, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(emailLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(emailTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(registerButton);
        container.add(resetButton);
        container.add(loginButton);
    }

    public void addActionEvent() {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
        loginButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    public static void showRegisterForm() {
        RegisterForm frame = new RegisterForm();
        frame.setTitle("Register Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of REGISTER button
        if (e.getSource() == registerButton) {
            String userText;
            String emailText;
            String pwdText;
            userText = userTextField.getText();
            emailText = emailTextField.getText();
            pwdText = passwordField.getText();

            String url = "http://localhost:8080/api/auth/register";
            String JSON_STRING = "{\"username\":\"" + userText +
                    "\",\"email\":\"" + emailText +
                    "\",\"password\":\"" + pwdText + "\"} ";
            String response = HttpClient.httpRequest(url, JSON_STRING);
            System.out.println(response);
            if (null != response && response.contains("User registered successfully!")) {
                JOptionPane.showMessageDialog(this, "Register Successful");
                dispose();
                LoginForm.showLoginForm();
            } else {
                JOptionPane.showMessageDialog(this, response.split("\"")[3]);
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

        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            dispose();
            LoginForm frame = new LoginForm();
            frame.setTitle("Login Form");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);
        }
    }

}