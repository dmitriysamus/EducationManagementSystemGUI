package educationManagementSystemGUI.cabinets.admin.group;

import educationManagementSystemGUI.cabinets.admin.AdminCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpPostUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCabinetCreateGroup extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel groupIdLabel = new JLabel("Group Id");
    JTextField groupIdTextField = new JTextField();
    JLabel groupLabel = new JLabel("Working with Group");
    JButton groupCreateButton = new JButton("Create Group");
    // при http POST запросе по адресу .../api/auth/groups/{groupNum}

    AdminCabinetCreateGroup(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link AdminCabinetCreateGroup#setLayoutManager}
     * устанавливает формат  layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link AdminCabinetCreateGroup#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя ADMIN.
     */
    public void setLocationAndSize() {
        // TODO
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        groupIdLabel.setBounds(10, 100, 180, 30);
        groupIdTextField.setBounds(10, 100, 180, 30);

        groupLabel.setBounds(10, 50, 180, 30);
        groupCreateButton.setBounds(10, 100, 180, 30);
    }

    /**
     * Метод {@link AdminCabinetCreateGroup#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(groupIdLabel);
        container.add(groupIdTextField);

        container.add(groupLabel);
        container.add(groupCreateButton);
    }

    /**
     * Метод {@link AdminCabinetCreateGroup#addActionEvent}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        groupCreateButton.addActionListener(this);
    }

    /**
     * Статический метод {@link AdminCabinetCreateGroup#showCabinetForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя ADMIN
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showCabinetForm(JSONObject userInfo, JSONObject response) {
        AdminCabinetCreateGroup frame = new AdminCabinetCreateGroup(userInfo, response);
        frame.setTitle("Admin Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link AdminCabinetCreateGroup#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     *
     * кнопки создания группы {@link AdminCabinetCreateGroup#groupCreateButton}
     * кнопки возврата в кабинет пользователя ADMIN {@link AdminCabinetDeleteGroup#backButton}
     * кнопки выхода {@link AdminCabinetCreateGroup#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of Create Group button /api/auth/groups/{groupNum}
        if (e.getSource() == groupCreateButton) {
            String groupNum;
            groupNum = groupIdTextField.getText();
            String url = "http://localhost:8080/api/auth/groups/" + groupNum;

            // TODO JSON to POST
            JSONObject response = HttpPostUtil.httpRequest(url, "{}", (String) this.userInfo.get("accessToken"));
            dispose();
            AdminCabinetCreateGroup.showCabinetForm(userInfo, response);
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