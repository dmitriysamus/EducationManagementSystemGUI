package educationManagementSystemGUI.forms;

import educationManagementSystemGUI.cabinets.admin.AdminCabinet;
import educationManagementSystemGUI.cabinets.teacher.TeacherCabinet;
import educationManagementSystemGUI.cabinets.user.UserCabinet;
import educationManagementSystemGUI.utils.HttpPostUtil;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link LoginForm} отображает форму аутентификации
 * зарегистрированного пользователя.
 *
 * @author habatoo
 * @version 0.001
 */
public class LoginForm extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JButton registerButton = new JButton("Register");
    JCheckBox showPassword = new JCheckBox("Show Password");

    LoginForm() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link LoginForm#setLayoutManager}
     * устанавливает формат  layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link LoginForm#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы аутентификации.
     */
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

    /**
     * Метод {@link LoginForm#addComponentsToContainer}
     * добавляет элементовы формы аутентификации в контейнер для
     * отображения.
     */
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

    /**
     * Метод {@link LoginForm#addActionEvent}
     * добавляет элементовы формы для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    /**
     * Статический метод {@link LoginForm#showLoginForm}
     * создает форму аутентификации для отображения.
     */
    public static void showLoginForm() {
        LoginForm frame = new LoginForm();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link LoginForm#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * кнопки для перехода на форму регистрации {@link RegisterForm#registerButton}
     * кнопки сброса {@link RegisterForm#resetButton}
     * кнопки отображения пароля {@link RegisterForm#showPassword}
     * кнопки аутентификации {@link RegisterForm#loginButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
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
            try {
                token = (String) response.get("tokenType");
                if (null != response && response.get("tokenType").equals("Bearer")) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Login Successful");

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
                }

            } catch (JSONException je) {
                je.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password",
                        "Login error",
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

        //Coding Part of REGISTER button
        if (e.getSource() == registerButton) {
            dispose();
            RegisterForm.showRegisterForm();
        }
    }

}