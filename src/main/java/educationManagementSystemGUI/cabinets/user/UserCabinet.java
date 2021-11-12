package educationManagementSystemGUI.cabinets.user;

import educationManagementSystemGUI.forms.LoginForm;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link UserCabinet} отображает форму рабочего кабинета пользователя с
 * ролью USER. Отображаются доступные для роли USER методы.
 *
 * @author habatoo
 * @version 0.001
 */
public class UserCabinet extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JLabel userLabel = new JLabel();
    JButton logoutButton = new JButton("Logout");

    JButton userInfoButton = new JButton("Show User Info");
    // при http GET запросе по адресу .../api/auth/users/getUserInfo
    JButton userInfoChangeButton = new JButton("Change User Info");
    // при http PUT запросе по адресу .../api/auth/users/{id}

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

    /**
     * Метод {@link UserCabinet#setLayoutManager}
     * устанавливает формат  layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link UserCabinet#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя USER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        userLabel.setBounds(50, 150, 100, 30);
    }

    /**
     * Метод {@link UserCabinet#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя USER
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(logoutButton);
    }

    /**
     * Метод {@link UserCabinet#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
    }

    /**
     * Статический метод {@link UserCabinet#showCabinetForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя USER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
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
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link UserCabinet#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки выхода {@link UserCabinet#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGOUT button
        if (e.getSource() == logoutButton) {
            dispose();
            LoginForm.showLoginForm();
        }
    }
}