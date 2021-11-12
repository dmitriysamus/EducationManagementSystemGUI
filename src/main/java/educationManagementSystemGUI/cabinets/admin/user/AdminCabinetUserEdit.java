package educationManagementSystemGUI.cabinets.admin.user;

import educationManagementSystemGUI.cabinets.admin.AdminCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpPutUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link AdminCabinetUserEdit} отображает форму метода
 * {@link AdminCabinet#userInfoChangeButton}  пользователя с
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

    AdminCabinetUserEdit(JSONObject userInfo, JSONObject response) {
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

        userEditIdIdLabel.setBounds(50, 100, 100, 30);
        userEditTextIdField.setBounds(150, 100, 150, 30);
        userEditNameLabel.setBounds(50, 150, 100, 30);
        userEditNameTextField.setBounds(150, 150, 150, 30);
        userEditEmailLabel.setBounds(50, 200, 100, 30);
        userEditEmailTextField.setBounds(150, 200, 150, 30);
        userEditPasswordLabel.setBounds(50, 250, 100, 30);
        userEditPasswordTextField.setBounds(150, 250, 150, 30);
        editButton.setBounds(50, 300, 100, 30);

    }

    /**
     * Метод {@link AdminCabinetUserEdit#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

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
                    "\"username\":\"" + userNameText +
                    "\"email\":\"" + userEmailText +
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
            dispose();
            LoginForm.showLoginForm();
        }
    }

}