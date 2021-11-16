package educationManagementSystemGUI.cabinets.teacher.group;

import educationManagementSystemGUI.cabinets.teacher.TeacherCabinet;
import educationManagementSystemGUI.cabinets.teacher.user.TeacherCabinetShowUserInfo;
import educationManagementSystemGUI.cabinets.user.UserCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpDeleteUtil;
import educationManagementSystemGUI.utils.HttpLogout;
import educationManagementSystemGUI.utils.HttpPostUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link TeacherCabinetDropUserFromGroup} отображает форму метода
 * {@link UserCabinet#groupDropButton}  пользователя с
 * ролью USER.
 *
 * @author habatoo
 * @version 0.001
 */
public class TeacherCabinetDropUserFromGroup extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel userIdLabel = new JLabel("User Id");
    JTextField userIdTextField = new JTextField();
    JLabel groupIdLabel = new JLabel("Group Id");
    JTextField groupIdTextField = new JTextField();
    JLabel groupLabel = new JLabel("Working with Group");
    JButton userDeleteButton = new JButton("Delete User from Group");
    // при http DELETE запросе по адресу .../api/auth/groups/students/{groupNum}/{studentId}

    public TeacherCabinetDropUserFromGroup(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link TeacherCabinetDropUserFromGroup#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link TeacherCabinetDropUserFromGroup#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя USER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        userIdLabel.setBounds(10, 100, 180, 30);
        userIdTextField.setBounds(200, 100, 180, 30);
        groupIdLabel.setBounds(10, 150, 180, 30);
        groupIdTextField.setBounds(200, 150, 180, 30);

        groupLabel.setBounds(10, 50, 180, 30);
        userDeleteButton.setBounds(10, 300, 180, 30);
    }

    /**
     * Метод {@link TeacherCabinetDropUserFromGroup#addComponentsToContainer}
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
        container.add(userDeleteButton);
    }

    /**
     * Метод {@link TeacherCabinetDropUserFromGroup#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        userDeleteButton.addActionListener(this);
    }

    /**
     * Статический метод {@link TeacherCabinetDropUserFromGroup#showTeacherForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя TEACHER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showTeacherForm(JSONObject userInfo, JSONObject response) {
        TeacherCabinetDropUserFromGroup frame = new TeacherCabinetDropUserFromGroup(userInfo, response);
        frame.setTitle("Teacher Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link TeacherCabinetDropUserFromGroup#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя USER {@link TeacherCabinetDropUserFromGroup#backButton}
     * кнопки выхода {@link TeacherCabinetDropUserFromGroup#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of Delete User from Group button /api/auth/groups/students/{groupNum}/{studentId}
        if (e.getSource() == userDeleteButton) {
            String groupNum;
            String student;
            groupNum = groupIdTextField.getText();
            student = userIdTextField.getText();
            String url = "http://localhost:8080/api/auth/groups/students/" + groupNum + "/" + student;

            // TODO JSON to DELETE
            JSONObject response = HttpDeleteUtil.httpRequest(url, (String) this.userInfo.get("accessToken"));
            dispose();
            TeacherCabinetDropUserFromGroup.showTeacherForm(userInfo, response);

            if (null != response && response.get("message").equals("Student deleted successfully!")) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!" +
                        "\nUser id = " + student +
                        "\nGroup id = " + groupNum);
            } else if (null != response && response.get("message").equals("Error: User (user) does not exist in the group!")) {
                JOptionPane.showMessageDialog(this, "Error: User (user) does not exist in the group!");

            } else if (null != response && response.get("message").equals("Error: Group does not exist!")) {
                JOptionPane.showMessageDialog(this, "Error: Group does not exist!");

            } else {
                JOptionPane.showMessageDialog(this, "Error: Student can't be deleted!");
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
