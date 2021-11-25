package educationManagementSystemGUI.cabinets.admin.user;

import educationManagementSystemGUI.cabinets.admin.AdminCabinet;
import educationManagementSystemGUI.cabinets.admin.group.AdminCabinetCreateGroup;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpDeleteUtil;
import educationManagementSystemGUI.utils.HttpLogout;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link AdminCabinetUserDelete} отображает форму метода
 * {@link AdminCabinet# userDeleteButton}  пользователя с
 * ролью ADMIN.
 *
 * @author habatoo
 * @version 0.001
 */
public class AdminCabinetUserDelete extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel userLabel = new JLabel("Working with Users");
    JLabel methodLabel = new JLabel("Deleting User");
    JLabel userDeleteIdIdLabel = new JLabel("User Id for delete");
    JTextField userDeleteTextIdField = new JTextField();
    JButton userDeleteButton = new JButton("Delete User");
    // при http DELETE запросе по адресу .../api/auth/users/{id}

    AdminCabinetUserDelete(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link AdminCabinetUserDelete#setLayoutManager}
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

        userLabel.setBounds(10, 50, 180, 30);
        methodLabel.setBounds(10, 100, 180, 30);
        userDeleteIdIdLabel.setBounds(10, 150, 180, 30);
        userDeleteTextIdField.setBounds(200, 150, 180, 30);
        userDeleteButton.setBounds(10, 300, 180, 30);
    }

    /**
     * Метод {@link AdminCabinetUserDelete#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(userLabel);
        container.add(methodLabel);
        container.add(userDeleteIdIdLabel);
        container.add(userDeleteTextIdField);
        container.add(userDeleteButton);
    }

    /**
     * Метод {@link AdminCabinetUserDelete#addActionEvent}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        userDeleteButton.addActionListener(this);
    }

    /**
     * Статический метод {@link AdminCabinetUserDelete#showDeleteUserForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя ADMIN
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showDeleteUserForm(JSONObject userInfo, JSONObject response) {
        AdminCabinetUserDelete frame = new AdminCabinetUserDelete(userInfo, response);
        frame.setTitle("Admin Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link AdminCabinetUserDelete#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки удаления пользователя {@link AdminCabinetUserDelete#userDeleteButton}
     * кнопки возврата в кабинет пользователя ADMIN {@link AdminCabinetUserDelete#backButton}
     * кнопки выхода {@link AdminCabinetUserDelete#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of Delete Users Info button /api/auth/users/{id}
        if (e.getSource() == userDeleteButton) {
            String userIdText;
            userIdText = userDeleteTextIdField.getText();

            String url = "http://localhost:8080/api/auth/users/" + userIdText;
            JSONObject response = HttpDeleteUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            if (null != response && response.get("message").equals("User was deleted successfully!")) {
                JOptionPane.showMessageDialog(this, "User was deleted successfully!" +
                        "\nUser id = " + userIdText);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: User was not deleted!",
                        "Admin error",
                        JOptionPane.ERROR_MESSAGE);
            }

            dispose();
            AdminCabinet.showCabinetForm(userInfo, response);
        }

        //Coding Part of BACK button
        if (e.getSource() == backButton) {
            dispose();
            AdminCabinet.showCabinetForm(userInfo, response);
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
