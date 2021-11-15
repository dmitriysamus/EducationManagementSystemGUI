package educationManagementSystemGUI.cabinets.admin.user;

import educationManagementSystemGUI.cabinets.admin.AdminCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpLogout;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link AdminCabinetShowAllUsers} отображает форму метода
 * {@link AdminCabinet# showAllUsersButton}  пользователя с
 * ролью ADMIN.
 *
 * @author habatoo
 * @version 0.001
 */
public class AdminCabinetShowAllUsers extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel userLabel = new JLabel("Working with Users");
    JLabel userListLabel = new JLabel("List of Users");
    JTextArea userListTextArea = new JTextArea();
    JScrollPane areaScrollPane = new JScrollPane(userListTextArea);
    // при http GET запросе по адресу ...http://localhost:8080/api/auth/users

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
     * Метод {@link AdminCabinetShowAllUsers#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя ADMIN.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        userLabel.setBounds(10, 50, 180, 30);
        userListLabel.setBounds(10, 100, 180, 30);
        userListTextArea.setBounds(10, 150, 350, 150);
        areaScrollPane.setBounds(10, 150, 350, 150);
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
        container.add(userListLabel);
        container.add(userListTextArea);
        container.add(areaScrollPane);
    }

    /**
     * Метод {@link AdminCabinetShowAllUsers#addActionEvent}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);
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
        AdminCabinetShowAllUsers frame = new AdminCabinetShowAllUsers(userInfo, response);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setText((String) response.get("username"));
        container.add(label, BorderLayout.CENTER);

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
            JSONObject response = HttpLogout.httpLogout( userInfo);
            JOptionPane.showMessageDialog(this, response.get("message"));
            dispose();
            LoginForm.showLoginForm();
        }

    }

}
