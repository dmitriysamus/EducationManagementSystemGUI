package educationManagementSystemGUI.cabinets.admin;

import educationManagementSystemGUI.cabinets.admin.group.AdminCabinetAddTeacherToGroup;
import educationManagementSystemGUI.cabinets.admin.group.AdminCabinetCreateGroup;
import educationManagementSystemGUI.cabinets.admin.group.AdminCabinetDeleteGroup;
import educationManagementSystemGUI.cabinets.admin.user.AdminCabinetShowAllUsers;
import educationManagementSystemGUI.cabinets.admin.user.AdminCabinetUserDelete;
import educationManagementSystemGUI.cabinets.admin.user.AdminCabinetUserEdit;
import educationManagementSystemGUI.cabinets.admin.user.AdminCabinetUserInfo;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpDeleteUtil;
import educationManagementSystemGUI.utils.HttpGetUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link AdminCabinet} отображает форму рабочего кабинета пользователя с
 * ролью ADMIN. Отображаются доступные для роли ADMIN методы.
 *
 * @author habatoo
 * @version 0.001
 */
public class AdminCabinet extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");

    JLabel groupLabel = new JLabel("Working with Group");
    JButton groupAddButton = new JButton("Add Teacher to Group");
    // при http POST запросе по адресу .../api/auth/groups/{groupNum}/{teacher}
    JButton groupCreateButton = new JButton("Create Group");
    // при http POST запросе по адресу .../api/auth/groups/{groupNum}
    JButton groupDropButton = new JButton("Delete Group");
    // при http DELETE запросе по адресу .../api/auth/groups/{groupNum}

    JLabel userLabel = new JLabel("Working with Users");
    JButton showAllUsersButton = new JButton("Show All Users");
    // при http GET запросе по адресу .../api/auth/users
    JButton userInfoButton = new JButton("Show User Info");
    // при http GET запросе по адресу .../api/auth/users/getUserInfo
    JButton userInfoChangeButton = new JButton("Change User Info");
    // при http PUT запросе по адресу .../api/auth/users/{id}
    JButton userDeleteButton = new JButton("Delete User");
    // при http DELETE запросе по адресу .../api/auth/users/{id}

    JLabel tokenLabel = new JLabel("Working with Tokens");
    JButton tokenDeleteButton = new JButton("Delete old tokens");
    // при http DELETE запросе по адресу .../api/auth/users/tokens - очищает базу от токенов с истекшим сроком

    AdminCabinet(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link AdminCabinet#setLayoutManager}
     * устанавливает формат  layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link AdminCabinet#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя ADMIN.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);

        groupLabel.setBounds(10, 50, 180, 30);
        groupAddButton.setBounds(10, 100, 180, 30);
        groupCreateButton.setBounds(10, 150, 180, 30);
        groupDropButton.setBounds(10, 200, 180, 30);

        userLabel.setBounds(200, 50, 180, 30);
        showAllUsersButton.setBounds(200, 100, 180, 30);
        userInfoButton.setBounds(200, 150, 180, 30);
        userInfoChangeButton.setBounds(200, 200, 180, 30);
        userDeleteButton.setBounds(200, 250, 180, 30);

        tokenLabel.setBounds(10, 300, 180, 30);
        tokenDeleteButton.setBounds(10, 350, 180, 30);
    }

    /**
     * Метод {@link AdminCabinet#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);

        container.add(groupLabel);
        container.add(groupAddButton);
        container.add(groupCreateButton);
        container.add(groupDropButton);

        container.add(userLabel);
        container.add(showAllUsersButton);
        container.add(userInfoButton);
        container.add(userInfoChangeButton);
        container.add(userDeleteButton);

        container.add(tokenLabel);
        container.add(tokenDeleteButton);
    }

    /**
     * Метод {@link AdminCabinet#addActionEvent}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);

        groupAddButton.addActionListener(this);
        groupCreateButton.addActionListener(this);
        groupDropButton.addActionListener(this);

        showAllUsersButton.addActionListener(this);
        userInfoButton.addActionListener(this);
        userInfoChangeButton.addActionListener(this);
        userDeleteButton.addActionListener(this);
        tokenDeleteButton.addActionListener(this);
    }

    /**
     * Статический метод {@link AdminCabinet#showCabinetForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя ADMIN
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showCabinetForm(JSONObject userInfo, JSONObject response) {
        AdminCabinet frame = new AdminCabinet(userInfo, response);
        frame.setTitle("Admin Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link AdminCabinet#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки добавления преподавателя в группу {@link AdminCabinet#groupAddButton}
     * кнопки добавления группы {@link AdminCabinet#groupCreateButton}
     * кнопки удаления группы {@link AdminCabinet#groupDropButton}
     * <p>
     * кнопки отображения информации по всем пользователям {@link AdminCabinet#showAllUsersButton}
     * кнопки отображения информации по конкретному пользователю {@link AdminCabinet#userInfoButton}
     * кнопки удаления пользователя {@link AdminCabinet#userDeleteButton}
     * <p>
     * кнопки обновления токенов {@link AdminCabinet#tokenDeleteButton}
     * <p>
     * кнопки выхода {@link AdminCabinet#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of Add Teacher to Group button /api/auth/groups/{groupNum}/{teacher}
        if (e.getSource() == groupAddButton) {
            dispose();
            AdminCabinetAddTeacherToGroup.showCabinetForm(userInfo, response);
        }

        //Coding Part of Create Group button /api/auth/groups/{groupNum}
        if (e.getSource() == groupCreateButton) {
            dispose();
            AdminCabinetCreateGroup.showCabinetForm(userInfo, response);
        }

        //Coding Part of Delete Group button /api/auth/groups/{groupNum}
        if (e.getSource() == groupDropButton) {
            dispose();
            AdminCabinetDeleteGroup.showCabinetForm(userInfo, response);
        }

        //Coding Part of Show All Users button /api/auth/users
        if (e.getSource() == showAllUsersButton) {
            String url = "http://localhost:8080/api/auth/users";
            JSONObject response = HttpGetUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            dispose();
            AdminCabinetShowAllUsers.showAllUserForm(userInfo, response);
        }

        //Coding Part of Show Users Info button /api/auth/users/getUserInfo
        if (e.getSource() == userInfoButton) {
            String url = "http://localhost:8080/api/auth/users/getUserInfo";
            JSONObject response = HttpGetUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            dispose();
            AdminCabinetUserInfo.showUserInfoForm(userInfo, response);
        }

        //Coding Part of EDIT button
        if (e.getSource() == userInfoChangeButton) {
            dispose();
            AdminCabinetUserEdit.showEditUserForm(userInfo, response);
        }

        //Coding Part of Delete Users Info button /api/auth/users/{id}
        if (e.getSource() == userDeleteButton) {
            dispose();
            AdminCabinetUserDelete.showDeleteUserForm(userInfo, response);
        }

        //Coding Part of Delete Token button api/auth/users/tokens
        if (e.getSource() == tokenDeleteButton) {
            String url = "http://localhost:8080/api/auth/users/tokens";
            JSONObject response = HttpDeleteUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            JOptionPane.showMessageDialog(this, response.get("message"));
            dispose();
            AdminCabinet.showCabinetForm(userInfo, response);
        }

        //Coding Part of LOGOUT button
        if (e.getSource() == logoutButton) {
            dispose();
            LoginForm.showLoginForm();
        }

    }
}
