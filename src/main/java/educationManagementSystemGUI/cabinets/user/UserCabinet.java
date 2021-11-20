package educationManagementSystemGUI.cabinets.user;

import educationManagementSystemGUI.cabinets.user.group.UserCabinetAddMeToGroup;
import educationManagementSystemGUI.cabinets.user.group.UserCabinetDropMeFromGroup;
import educationManagementSystemGUI.cabinets.user.group.UserCabinetShowAllGroups;
import educationManagementSystemGUI.cabinets.user.group.UserCabinetShowMyRateInGroups;
import educationManagementSystemGUI.cabinets.user.user.UserCabinetChangeUserInfo;
import educationManagementSystemGUI.cabinets.user.user.UserCabinetShowUserInfo;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpLogout;
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
    JButton logoutButton = new JButton("Logout");

    JLabel userLabel = new JLabel("Working with Users");
    JButton userInfoButton = new JButton("Show User Info");
    // при http GET запросе по адресу .../api/auth/users/getUserInfo
    JButton userInfoChangeButton = new JButton("Change User Info");
    // при http PUT запросе по адресу .../api/auth/users/{id}

    JLabel groupLabel = new JLabel("Working with Group");
    JButton groupShowAllGroupsButton = new JButton("Show All Groups");
    // TODO
    JButton groupAddStudentButton = new JButton("Add User to Group");
    // при http POST запросе по адресу .../api/auth/groups/students/{groupNum}/{studentId}
    JButton groupDropButton = new JButton("Delete User from Group");
    // при http DELETE запросе по адресу .../api/auth/groups/{groupNum}
    JButton groupRateButton = new JButton("My Rate in Groups");
    // TODO only user rates

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

        groupLabel.setBounds(10, 50, 180, 30);
        groupShowAllGroupsButton.setBounds(10, 100, 180, 30);
        groupAddStudentButton.setBounds(10, 150, 180, 30);
        groupDropButton.setBounds(10, 200, 180, 30);
        groupRateButton.setBounds(10, 250, 180, 30);

        userLabel.setBounds(200, 50, 180, 30);
        userInfoButton.setBounds(200, 100, 180, 30);
        userInfoChangeButton.setBounds(200, 150, 180, 30);
    }

    /**
     * Метод {@link UserCabinet#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя USER
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);

        container.add(groupLabel);
        container.add(groupShowAllGroupsButton);
        container.add(groupAddStudentButton);
        container.add(groupDropButton);
        container.add(groupRateButton);

        container.add(userLabel);
        container.add(userInfoButton);
        container.add(userInfoChangeButton);
    }

    /**
     * Метод {@link UserCabinet#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);

        groupShowAllGroupsButton.addActionListener(this);
        groupAddStudentButton.addActionListener(this);
        groupDropButton.addActionListener(this);
        groupRateButton.addActionListener(this);

        userInfoButton.addActionListener(this);
        userInfoChangeButton.addActionListener(this);
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
        frame.setTitle("User Cabinet");
        frame.setVisible(true);
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

        //Coding Part of Show All Groups button
        if (e.getSource() == groupShowAllGroupsButton) {
            dispose();
            UserCabinetShowAllGroups.showUserInfoForm(userInfo, response);
        }

        //Coding Part of Show All Groups button
        if (e.getSource() == groupAddStudentButton) {
            dispose();
            UserCabinetAddMeToGroup.addUserToGroupForm(userInfo, response);
        }

        //Coding Part of Show All Groups button
        if (e.getSource() == groupDropButton) {
            dispose();
            UserCabinetDropMeFromGroup.dropUserFromGroupForm(userInfo, response);
        }

        //Coding Part of Show My Rate in Groups button
        if (e.getSource() == groupRateButton) {
            dispose();
            UserCabinetShowMyRateInGroups.showUserGroupRateForm(userInfo, response);
        }

        //Coding Part of Show User Info button
        if (e.getSource() == userInfoButton) {
            dispose();
            UserCabinetShowUserInfo.showUserInfoForm(userInfo, response);
        }

        //Coding Part of Change User Info button
        if (e.getSource() == userInfoChangeButton) {
            dispose();
            UserCabinetChangeUserInfo.showEditUserForm(userInfo, response);
        }

        //Coding Part of LOGOUT button
        if (e.getSource() == logoutButton) {
            JSONObject response = HttpLogout.httpLogout( userInfo);
            JOptionPane.showMessageDialog(this, response.get("message"));
            dispose();
            LoginForm.showLoginForm();
        }
    }
}