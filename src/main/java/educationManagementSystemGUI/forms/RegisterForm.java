package educationManagementSystemGUI.forms;

import educationManagementSystemGUI.utils.HttpPostUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link RegisterForm} отображает форму регистрации нового пользователя.
 * Пользователи с ролями ADMIN и TEACHER через форму регистрации не создаются.
 *
 * @version 0.001
 * @author habatoo
 */
public class RegisterForm extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("Username");
    JLabel emailLabel = new JLabel("Email");
    JLabel passwordLabel = new JLabel("Password");

    JTextField userTextField = new JTextField();
    JTextField emailTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton registerButton = new JButton("Register");
    JButton resetButton = new JButton("Reset");
    JButton loginButton = new JButton("Login");
    JCheckBox showPassword = new JCheckBox("Show Password");

    RegisterForm() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link RegisterForm#setLayoutManager}
     * устанавливает формат  layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link RegisterForm#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы регистрации.
     */
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

    /**
     * Метод {@link RegisterForm#addComponentsToContainer}
     * добавляет элементовы формы регистрации в контейнер для
     * отображения.
     */
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

    /**
     * Метод {@link RegisterForm#addActionEvent}
     * добавляет элементовы формы для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
        loginButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    /**
     * Статический метод {@link RegisterForm#showRegisterForm}
     * создает форму регистрации для отображения.
     */
    public static void showRegisterForm() {
        RegisterForm frame = new RegisterForm();
        frame.setTitle("Register Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link RegisterForm#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * кнопки регистрации {@link RegisterForm#registerButton}
     * кнопки сброса {@link RegisterForm#resetButton}
     * кнопки отображения пароля {@link RegisterForm#showPassword}
     * кнопки для перехода на форму аутентификации {@link RegisterForm#loginButton}
     * кнопки перехода на форму аутентификации {@link RegisterForm#loginButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
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
            String token = null;

            String url = "http://localhost:8080/api/auth/register";
            String JSON_STRING = "{\"username\":\"" + userText +
                    "\",\"email\":\"" + emailText +
                    "\",\"password\":\"" + pwdText + "\"} ";
            JSONObject response = HttpPostUtil.httpRequest(url, JSON_STRING, token);

            if (null != response && response.get("message").equals("User registered successfully!")) {
                JOptionPane.showMessageDialog(this, "Register Successful");
                dispose();
                LoginForm.showLoginForm();
            } else if (userText.equals(null) || emailText.equals(null) || pwdText.equals(null)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Register data can not be blank!",
                        "Register error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (response.get("message").equals("Error: Username is already taken!")){
                    JOptionPane.showMessageDialog(
                            this,
                            "Error: Username is already taken!",
                            "Register error",
                            JOptionPane.ERROR_MESSAGE);
            } else if (response.get("message").equals("Error: Email is already in use!")){
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Email is already in use!",
                        "Register error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Incorrect register data!",
                        "Register error",
                        JOptionPane.ERROR_MESSAGE);
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