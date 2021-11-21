package educationManagementSystemGUI.cabinets.user.group;

import educationManagementSystemGUI.cabinets.teacher.group.TeacherCabinetAddUserToGroup;
import educationManagementSystemGUI.cabinets.user.UserCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpLogout;
import educationManagementSystemGUI.utils.HttpPostUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link UserCabinetAddMeToGroup} отображает форму метода
 * {@link UserCabinet# groupAddStudentButton}  пользователя с
 * ролью USER.
 *
 * @author habatoo
 * @version 0.001
 */
public class UserCabinetAddMeToGroup extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel groupLabel = new JLabel("Working with Group");
    JLabel methodLabel = new JLabel("Adding user to Group");
    JLabel groupIdLabel = new JLabel("Group Id");
    JTextField groupIdTextField = new JTextField();
    JButton userAddButton = new JButton("Add me to Group");
    // при http POST запросе по адресу .../api/auth/groups/students/{groupNum}/{studentId}

    public UserCabinetAddMeToGroup(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link UserCabinetAddMeToGroup#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link UserCabinetAddMeToGroup#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя USER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        groupIdLabel.setBounds(10, 150, 180, 30);
        groupIdTextField.setBounds(200, 150, 180, 30);

        groupLabel.setBounds(10, 50, 180, 30);
        methodLabel.setBounds(10, 100, 180, 30);
        userAddButton.setBounds(10, 300, 180, 30);
    }

    /**
     * Метод {@link UserCabinetAddMeToGroup#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя USER
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(groupIdLabel);
        container.add(groupIdTextField);

        container.add(groupLabel);
        container.add(methodLabel);
        container.add(userAddButton);
    }

    /**
     * Метод {@link UserCabinetAddMeToGroup#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    /**
     * Статический метод {@link UserCabinetAddMeToGroup#addUserToGroupForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя USER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void addUserToGroupForm(JSONObject userInfo, JSONObject response) {
        UserCabinetAddMeToGroup frame = new UserCabinetAddMeToGroup(userInfo, response);
        frame.setTitle("User Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link UserCabinetAddMeToGroup#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя USER {@link UserCabinetAddMeToGroup#backButton}
     * кнопки выхода {@link UserCabinetAddMeToGroup#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of Add User to Group button /api/auth/groups/students/{groupNum}/{studentId}
        if (e.getSource() == userAddButton) {
            String groupNum;
            String student;
            groupNum = groupIdTextField.getText();
            student = (String) userInfo.get("username");
            String url = "http://localhost:8080/api/auth/groups/students/" + groupNum + "/" + student;

            JSONObject response = HttpPostUtil.httpRequest(url, "{}", (String) this.userInfo.get("accessToken"));
            if (null != response && response.get("message").equals("Student added successfully!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Student added successfully!" +
                        "\nUser id = " + student +
                        "\nGroup id = " + groupNum);
            } else if (null != response && response.get("message").equals("Error: User (user) does not exist!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: User (user) does not exist!",
                        "User error",
                        JOptionPane.ERROR_MESSAGE);

            } else if (null != response && response.get("message").equals("Error: Group does not exist!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Group does not exist!",
                        "User error",
                        JOptionPane.ERROR_MESSAGE);

            } else if (null != response && response.get("message").equals("Error: User (user) has not role user!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: User (user) has not role user!",
                        "User error",
                        JOptionPane.ERROR_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Student can't be added!",
                        "User error",
                        JOptionPane.ERROR_MESSAGE);
            }

            dispose();
            TeacherCabinetAddUserToGroup.showTeacherForm(userInfo, response);

        }

        //Coding Part of BACK button
        if (e.getSource() == backButton) {
            dispose();
            UserCabinet.showCabinetForm(userInfo, response);
        }

        //Coding Part of LOGOUT button
        if (e.getSource() == logoutButton) {
            JSONObject response = HttpLogout.httpLogout(userInfo);
            JOptionPane.showMessageDialog(this, response.get("message"));
            dispose();
            LoginForm.showLoginForm();
        }

    }

}
