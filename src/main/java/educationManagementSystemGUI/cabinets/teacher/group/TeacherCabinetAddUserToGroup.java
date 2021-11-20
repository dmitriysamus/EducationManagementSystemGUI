package educationManagementSystemGUI.cabinets.teacher.group;

import educationManagementSystemGUI.cabinets.teacher.TeacherCabinet;
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
 * Класс {@link TeacherCabinetAddUserToGroup} отображает форму метода
 * {@link UserCabinet# groupAddStudentButton}  пользователя с
 * ролью USER.
 *
 * @author habatoo
 * @version 0.001
 */
public class TeacherCabinetAddUserToGroup extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel groupLabel = new JLabel("Working with Group");
    JLabel methodLabel = new JLabel("Adding user to Group");
    JLabel userIdLabel = new JLabel("User Id");
    JTextField userIdTextField = new JTextField();
    JLabel groupIdLabel = new JLabel("Group Id");
    JTextField groupIdTextField = new JTextField();
    JButton userAddButton = new JButton("Add User to Group");
    // при http POST запросе по адресу .../api/auth/groups/students/{groupNum}/{studentId}

    public TeacherCabinetAddUserToGroup(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link TeacherCabinetAddUserToGroup#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link TeacherCabinetAddUserToGroup#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя USER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        userIdLabel.setBounds(10, 150, 180, 30);
        userIdTextField.setBounds(200, 150, 180, 30);
        groupIdLabel.setBounds(10, 200, 180, 30);
        groupIdTextField.setBounds(200, 200, 180, 30);

        groupLabel.setBounds(10, 50, 180, 30);
        methodLabel.setBounds(10, 100, 180, 30);
        userAddButton.setBounds(10, 300, 180, 30);
    }

    /**
     * Метод {@link TeacherCabinetAddUserToGroup#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя USER
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(userIdLabel);
        container.add(userIdTextField);
        container.add(groupIdLabel);
        container.add(groupIdTextField);

        container.add(groupLabel);
        container.add(methodLabel);
        container.add(userAddButton);
    }

    /**
     * Метод {@link TeacherCabinetAddUserToGroup#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        userAddButton.addActionListener(this);
    }

    /**
     * Статический метод {@link TeacherCabinetAddUserToGroup#showTeacherForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя TEACHER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showTeacherForm(JSONObject userInfo, JSONObject response) {
        TeacherCabinetAddUserToGroup frame = new TeacherCabinetAddUserToGroup(userInfo, response);
        frame.setTitle("Teacher Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link TeacherCabinetAddUserToGroup#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя USER {@link TeacherCabinetAddUserToGroup#backButton}
     * кнопки выхода {@link TeacherCabinetAddUserToGroup#logoutButton}
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
            student = userIdTextField.getText();
            String url = "http://localhost:8080/api/auth/groups/students/" + groupNum + "/" + student;

            // TODO JSON to POST
            JSONObject response = HttpPostUtil.httpRequest(url, "{}", (String) this.userInfo.get("accessToken"));
            dispose();
            TeacherCabinetAddUserToGroup.showTeacherForm(userInfo, response);

            if (null != response && response.get("message").equals("Student added successfully!")) {
                JOptionPane.showMessageDialog(this, "Student added successfully!" +
                        "\nUser id = " + student +
                        "\nGroup id = " + groupNum);
            } else if (null != response && response.get("message").equals("Error: User (user) does not exist!")) {
                JOptionPane.showMessageDialog(this, "Error: User (user) does not exist!");

            } else if (null != response && response.get("message").equals("Error: Group does not exist!")) {
                JOptionPane.showMessageDialog(this, "Error: Group does not exist!");

            } else if (null != response && response.get("message").equals("Error: User (user) has not role user!")) {
                JOptionPane.showMessageDialog(this, "Error: User (user) has not role user!");

            } else {
                JOptionPane.showMessageDialog(this, "Error: Student can't be added!");
            }
        }

        //Coding Part of BACK button
        if (e.getSource() == backButton) {
            dispose();
            TeacherCabinet.showCabinetForm(userInfo, response);
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
