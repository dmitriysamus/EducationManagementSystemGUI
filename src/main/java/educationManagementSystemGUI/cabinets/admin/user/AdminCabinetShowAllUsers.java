package educationManagementSystemGUI.cabinets.admin.user;

import educationManagementSystemGUI.cabinets.admin.AdminCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link AdminCabinetShowAllUsers} отображает форму метода
 * {@link AdminCabinet#showAllUsersButton}  пользователя с
 * ролью ADMIN.
 *
 * @author habatoo
 * @version 0.001
 */
public class AdminCabinetShowAllUsers extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JLabel userLabel = new JLabel();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel userDeleteIdIdLabel = new JLabel("User Id for delete");
    JTextField userDeleteTextIdField = new JTextField();
    JButton userDeleteButton = new JButton("Delete User");
    // при http DELETE запросе по адресу .../api/auth/users/{id}

    AdminCabinetShowAllUsers(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link AdminCabinetShowAllUsers#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link AdminCabinetUserDelete#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя ADMIN.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        // TODO
        userDeleteIdIdLabel.setBounds(50, 100, 100, 30);
        userDeleteTextIdField.setBounds(150, 100, 150, 30);
        userDeleteButton.setBounds(350, 150, 100, 30);
    }

    /**
     * Метод {@link AdminCabinetShowAllUsers#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(userLabel);
        container.add(userDeleteIdIdLabel);
        container.add(userDeleteTextIdField);
        container.add(userDeleteButton);
    }

    /**
     * Метод {@link AdminCabinetShowAllUsers#addActionEvent}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        userDeleteButton.addActionListener(this);
    }

    /**
     * Статический метод {@link AdminCabinetShowAllUsers#showAllUserForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя ADMIN
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showAllUserForm(JSONObject userInfo, JSONObject response) {
        AdminCabinetUserDelete frame = new AdminCabinetUserDelete(userInfo, response);
        frame.setTitle("Admin Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link AdminCabinetShowAllUsers#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя ADMIN {@link AdminCabinetShowAllUsers#backButton}
     * кнопки выхода {@link AdminCabinetShowAllUsers#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of BACK button
        if (e.getSource() == backButton) {
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
