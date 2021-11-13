package educationManagementSystemGUI.cabinets.user.user;

import educationManagementSystemGUI.cabinets.user.UserCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link UserCabinetShowUserInfo} отображает форму метода
 * {@link UserCabinet#userInfoButton}  пользователя с
 * ролью USER.
 *
 * @author habatoo
 * @version 0.001
 */
public class UserCabinetShowUserInfo extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel userInfoLabel = new JLabel("User Info");
    JTextArea textArea = new JTextArea();
    JScrollPane areaScrollPane = new JScrollPane(textArea);
    // при http GET запросе по адресу .../api/auth/users/getUserInfo

    public UserCabinetShowUserInfo(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link UserCabinetShowUserInfo#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link UserCabinetShowUserInfo#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя USER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        userInfoLabel.setBounds(50, 100, 100, 30);
        textArea.setBounds(150, 100, 150, 30);
        areaScrollPane.setBounds(150, 100, 150, 30);
    }

    /**
     * Метод {@link UserCabinetShowUserInfo#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя USER
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(userInfoLabel);
        container.add(textArea);
        container.add(areaScrollPane);
    }

    /**
     * Метод {@link UserCabinetShowUserInfo#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    /**
     * Статический метод {@link UserCabinetShowUserInfo#showUserInfoForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя USER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showUserInfoForm(JSONObject userInfo, JSONObject response) {
        UserCabinetShowUserInfo frame = new UserCabinetShowUserInfo(userInfo, response);
        frame.setTitle("User Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link UserCabinetShowUserInfo#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя USER {@link UserCabinetShowUserInfo#backButton}
     * кнопки выхода {@link UserCabinetShowUserInfo#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of BACK button
        if (e.getSource() == backButton) {
            dispose();
            UserCabinet.showCabinetForm(userInfo, response);
        }

        //Coding Part of LOGOUT button
        if (e.getSource() == logoutButton) {
            dispose();
            LoginForm.showLoginForm();
        }

    }

}
