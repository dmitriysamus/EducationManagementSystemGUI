package educationManagementSystemGUI.cabinets.user.group;

import educationManagementSystemGUI.cabinets.user.UserCabinet;
import educationManagementSystemGUI.cabinets.user.user.UserCabinetShowUserInfo;
import educationManagementSystemGUI.forms.LoginForm;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link UserCabinetShowMyRateInGroups} отображает форму метода
 * {@link UserCabinet#groupRateButton}  пользователя с
 * ролью USER.
 *
 * @author habatoo
 * @version 0.001
 */
public class UserCabinetShowMyRateInGroups extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel userInfoLabel = new JLabel("Add User to Group");
    JTextArea textArea = new JTextArea();
    JScrollPane areaScrollPane = new JScrollPane(textArea);
    // TODO

    public UserCabinetShowMyRateInGroups(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link UserCabinetShowMyRateInGroups#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link UserCabinetShowMyRateInGroups#setLocationAndSize}
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
     * Метод {@link UserCabinetShowMyRateInGroups#addComponentsToContainer}
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
     * Метод {@link UserCabinetShowMyRateInGroups#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    /**
     * Статический метод {@link UserCabinetShowMyRateInGroups#showUserGroupRateForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя USER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showUserGroupRateForm(JSONObject userInfo, JSONObject response) {
        UserCabinetShowUserInfo frame = new UserCabinetShowUserInfo(userInfo, response);
        frame.setTitle("User Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link UserCabinetShowMyRateInGroups#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя USER {@link UserCabinetShowMyRateInGroups#backButton}
     * кнопки выхода {@link UserCabinetShowMyRateInGroups#logoutButton}
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
