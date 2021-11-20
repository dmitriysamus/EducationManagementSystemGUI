package educationManagementSystemGUI.cabinets.admin.user;

import educationManagementSystemGUI.cabinets.admin.AdminCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpLogout;
import educationManagementSystemGUI.utils.HttpPutUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link AdminCabinetUserEdit} отображает форму метода
 * {@link AdminCabinet# userInfoChangeButton}  пользователя с
 * ролью ADMIN.
 *
 * @author habatoo
 * @version 0.001
 */
public class AdminCabinetUserEdit extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    Container container = getContentPane();

    JLabel userLabel = new JLabel("Working with Users");
    JLabel methodLabel = new JLabel("Edit User Info");
    JLabel userEditIdIdLabel = new JLabel("User Id for edit");
    JTextField userEditTextIdField = new JTextField();
    JLabel userEditNameLabel = new JLabel("User name for edit");
    JTextField userEditNameTextField = new JTextField();
    JLabel userEditEmailLabel = new JLabel("User email for edit");
    JTextField userEditEmailTextField = new JTextField();
    JLabel userEditPasswordLabel = new JLabel("User password for edit");
    JTextField userEditPasswordTextField = new JTextField();
    JButton editButton = new JButton("Save edit data");
    // при http PUT запросе по адресу .../api/auth/users/{id}

    public AdminCabinetUserEdit(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link AdminCabinetUserEdit#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link AdminCabinetUserEdit#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя ADMIN.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        userLabel.setBounds(10, 50, 180, 30);
        methodLabel.setBounds(10, 100, 180, 30);
        userEditIdIdLabel.setBounds(10, 150, 180, 30);
        userEditTextIdField.setBounds(200, 150, 180, 30);
        userEditNameLabel.setBounds(10, 200, 180, 30);
        userEditNameTextField.setBounds(200, 200, 180, 30);
        userEditEmailLabel.setBounds(10, 250, 180, 30);
        userEditEmailTextField.setBounds(200, 250, 180, 30);
        userEditPasswordLabel.setBounds(10, 300, 180, 30);
        userEditPasswordTextField.setBounds(200, 300, 180, 30);
        editButton.setBounds(10, 350, 180, 30);
    }

    /**
     * Метод {@link AdminCabinetUserEdit#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(userLabel);
        container.add(methodLabel);
        container.add(userEditIdIdLabel);
        container.add(userEditTextIdField);
        container.add(userEditNameLabel);
        container.add(userEditNameTextField);
        container.add(userEditEmailLabel);
        container.add(userEditEmailTextField);
        container.add(userEditPasswordLabel);
        container.add(userEditPasswordTextField);
        container.add(editButton);
    }

    /**
     * Метод {@link AdminCabinetUserEdit#addActionEvent}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);
        editButton.addActionListener(this);
    }

    /**
     * Статический метод {@link AdminCabinetUserEdit#showEditUserForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя ADMIN
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showEditUserForm(JSONObject userInfo, JSONObject response) {
        AdminCabinetUserEdit frame = new AdminCabinetUserEdit(userInfo, response);
        frame.setTitle("Admin Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link AdminCabinetUserEdit#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки редактирования пользователя {@link AdminCabinetUserEdit#editButton}
     * кнопки возврата в кабинет пользователя ADMIN {@link AdminCabinetUserEdit#backButton}
     * кнопки выхода {@link AdminCabinetUserEdit#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of SHOW button
        if (e.getSource() == editButton) {
            String userIdText;
            userIdText = userEditTextIdField.getText();
            String userNameText;
            userNameText = userEditNameTextField.getText();
            String userEmailText;
            userEmailText = userEditEmailTextField.getText();
            String userPasswordText;
            userPasswordText = userEditPasswordTextField.getText();

            String JSON_STRING = "{\"id\":\"" + userIdText +
                    "\",\"username\":\"" + userNameText +
                    "\",\"email\":\"" + userEmailText +
                    "\",\"password\":\"" + userPasswordText + "\"} ";

            String url = "http://localhost:8080/api/auth/users/" + userIdText;
            JSONObject response = HttpPutUtil.httpRequest(url, JSON_STRING, (String) this.userInfo.get("accessToken"));
            JOptionPane.showMessageDialog(this, response.get("message"));
            dispose();
            AdminCabinetUserEdit.showEditUserForm(userInfo, response);

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