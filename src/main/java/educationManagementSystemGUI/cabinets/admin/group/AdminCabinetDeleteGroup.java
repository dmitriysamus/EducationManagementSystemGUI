package educationManagementSystemGUI.cabinets.admin.group;

import educationManagementSystemGUI.cabinets.admin.AdminCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpDeleteUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link AdminCabinetDeleteGroup} отображает форму метода
 * {@link AdminCabinet#groupDropButton}  пользователя с
 * ролью ADMIN.
 *
 * @version 0.001
 * @author habatoo
 */
public class AdminCabinetDeleteGroup extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel groupIdLabel = new JLabel("Group Id");
    JTextField groupIdTextField = new JTextField();
    JLabel groupLabel = new JLabel("Working with Group");
    JButton groupDropButton = new JButton("Drop Group");
    // при http DELETE запросе по адресу .../api/auth/groups/{groupNum}

    AdminCabinetDeleteGroup(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link AdminCabinetDeleteGroup#setLayoutManager}
     * устанавливает формат  layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link AdminCabinetDeleteGroup#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя ADMIN.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        // TODO
        groupIdLabel.setBounds(10, 100, 180, 30);
        groupIdTextField.setBounds(10, 100, 180, 30);

        groupLabel.setBounds(10, 50, 180, 30);
        groupDropButton.setBounds(10, 100, 180, 30);
    }

    /**
     * Метод {@link AdminCabinetDeleteGroup#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(groupIdLabel);
        container.add(groupIdTextField);

        container.add(groupLabel);
        container.add(groupDropButton);
    }

    /**
     * Метод {@link AdminCabinetDeleteGroup#addActionEvent}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        groupDropButton.addActionListener(this);
    }

    /**
     * Статический метод {@link AdminCabinetDeleteGroup#showCabinetForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя ADMIN
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showCabinetForm(JSONObject userInfo, JSONObject response) {
        AdminCabinetDeleteGroup frame = new AdminCabinetDeleteGroup(userInfo, response);
        frame.setTitle("Admin Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link AdminCabinetDeleteGroup#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     *
     * кнопки добавления преподавателя в группу {@link AdminCabinetDeleteGroup#groupDropButton}
     * кнопки возврата в кабинет пользователя ADMIN {@link AdminCabinetDeleteGroup#backButton}
     * кнопки выхода {@link AdminCabinetDeleteGroup#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of Delete Group button /api/auth/groups/{groupNum}
        if (e.getSource() == groupDropButton) {
            String groupNum;
            groupNum = groupIdTextField.getText();
            String url = "http://localhost:8080/api/auth/groups/" + groupNum;

            // TODO JSON to DELETE
            JSONObject response = HttpDeleteUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            dispose();
            AdminCabinetDeleteGroup.showCabinetForm(userInfo, response);
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
