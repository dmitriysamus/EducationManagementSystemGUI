package educationManagementSystemGUI.cabinets.teacher;

import educationManagementSystemGUI.cabinets.teacher.group.TeacherCabinetAddUserToGroup;
import educationManagementSystemGUI.cabinets.teacher.group.TeacherCabinetDropUserFromGroup;
import educationManagementSystemGUI.cabinets.teacher.group.TeacherCabinetShowAllGroups;
import educationManagementSystemGUI.cabinets.teacher.group.TeacherCabinetShowAllRates;
import educationManagementSystemGUI.cabinets.teacher.lesson.TeacherCabinetCreateLesson;
import educationManagementSystemGUI.cabinets.teacher.lesson.TeacherCabinetCreateTaskInLesson;
import educationManagementSystemGUI.cabinets.teacher.lesson.TeacherCabinetRateUserInLesson;
import educationManagementSystemGUI.cabinets.teacher.user.TeacherCabinetChangeUserInfo;
import educationManagementSystemGUI.cabinets.teacher.user.TeacherCabinetShowAllUsers;
import educationManagementSystemGUI.cabinets.teacher.user.TeacherCabinetShowUserInfo;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpLogout;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс {@link TeacherCabinet} отображает форму рабочего кабинета пользователя с
 * ролью TEACHER. Отображаются доступные для роли TEACHER методы.
 *
 * @author habatoo
 * @version 0.001
 */
public class TeacherCabinet extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");

    JLabel userLabel = new JLabel("Working with Users");
    JButton showAllUsersButton = new JButton("Show All Users");
    // при http GET запросе по адресу .../api/auth/users
    JButton userInfoButton = new JButton("Show User Info");
    // при http GET запросе по адресу .../api/auth/users/getUserInfo
    JButton userInfoChangeButton = new JButton("Change User Info");
    // при http PUT запросе по адресу .../api/auth/users/{id}

    JLabel groupLabel = new JLabel("Working with Group");
    JButton groupShowAllGroupsButton = new JButton("Show All Groups");
    // TODO
    JButton groupAddStudentButton = new JButton("Add User to Group");
    // при http POST запросе по адресу .../api/auth/groups/students/{groupNum}/{studentId}
    JButton groupDropButton = new JButton("Delete User from Group");
    // при http DELETE запросе по адресу .../api/auth/groups/{groupNum}
    JButton groupRateButton = new JButton("All Rate in Groups");
    // TODO only user rates

    JLabel lessonLabel = new JLabel("Working with Lessons");
    JButton createLessonButton = new JButton("Create Lesson");
    // при http POST запросе по адресу .../api/auth/groups/{groupNum}/lesson
    JButton createTaskInLessonButton = new JButton("Create Task in Lesson");
    // createTask - при http POST запросе по адресу .../api/auth/groups/lessons/{lessonId}
    JButton rateStudentInLessonButton = new JButton("Rate User in Lesson");
    // rateStudent - при http POST запросе по адресу .../api/auth/groups/rate/{lessonId}

    TeacherCabinet(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link TeacherCabinet#setLayoutManager}
     * устанавливает формат  layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link TeacherCabinet#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя TEACHER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);

        groupLabel.setBounds(10, 50, 180, 30);
        groupShowAllGroupsButton.setBounds(10, 100, 180, 30);
        groupAddStudentButton.setBounds(10, 150, 180, 30);
        groupDropButton.setBounds(10, 200, 180, 30);
        groupRateButton.setBounds(10, 250, 180, 30);

        userLabel.setBounds(200, 50, 180, 30);
        showAllUsersButton.setBounds(200, 100, 180, 30);
        userInfoButton.setBounds(200, 150, 180, 30);
        userInfoChangeButton.setBounds(200, 200, 180, 30);

        lessonLabel.setBounds(10, 300, 180, 30);
        createLessonButton.setBounds(10, 350, 180, 30);
        createTaskInLessonButton.setBounds(10, 400, 180, 30);
        rateStudentInLessonButton.setBounds(10, 450, 180, 30);
    }

    /**
     * Метод {@link TeacherCabinet#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);

        container.add(groupLabel);
        container.add(groupShowAllGroupsButton);
        container.add(groupAddStudentButton);
        container.add(groupDropButton);
        container.add(groupRateButton);

        container.add(userLabel);
        container.add(showAllUsersButton);
        container.add(userInfoButton);
        container.add(userInfoChangeButton);

        container.add(lessonLabel);
        container.add(createLessonButton);
        container.add(createTaskInLessonButton);
        container.add(rateStudentInLessonButton);
    }

    /**
     * Метод {@link TeacherCabinet#addActionEvent}
     * добавляет элементовы формы кабинета пользователя ADMIN
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);

        groupShowAllGroupsButton.addActionListener(this);
        groupAddStudentButton.addActionListener(this);
        groupDropButton.addActionListener(this);
        groupRateButton.addActionListener(this);

        showAllUsersButton.addActionListener(this);
        userInfoButton.addActionListener(this);
        userInfoChangeButton.addActionListener(this);

        createLessonButton.addActionListener(this);
        createTaskInLessonButton.addActionListener(this);
        rateStudentInLessonButton.addActionListener(this);
    }

    /**
     * Статический метод {@link TeacherCabinet#showCabinetForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя ADMIN
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showCabinetForm(JSONObject userInfo, JSONObject response) {
        TeacherCabinet frame = new TeacherCabinet(userInfo, response);
        frame.setTitle("Teacher Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link TeacherCabinet#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки выхода {@link TeacherCabinet#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of Show All Groups button
        if (e.getSource() == groupShowAllGroupsButton) {
            dispose();
            TeacherCabinetShowAllGroups.showTeacherForm(userInfo, response);
        }

        //Coding Part of Add User to Group button
        if (e.getSource() == groupAddStudentButton) {
            dispose();
            TeacherCabinetAddUserToGroup.showTeacherForm(userInfo, response);
        }

        //Coding Part of Drop User from Group button
        if (e.getSource() == groupDropButton) {
            dispose();
            TeacherCabinetDropUserFromGroup.showTeacherForm(userInfo, response);
        }

        //Coding Part of Show All Rates button
        if (e.getSource() == groupRateButton) {
            dispose();
            TeacherCabinetShowAllRates.showTeacherForm(userInfo, response);
        }

        //Coding Part of Show All Users button
        if (e.getSource() == showAllUsersButton) {
            dispose();
            TeacherCabinetShowAllUsers.showTeacherForm(userInfo, response);
        }

        //Coding Part of Show User Info button
        if (e.getSource() == userInfoButton) {
            dispose();
            TeacherCabinetShowUserInfo.showTeacherForm(userInfo, response);
        }

        //Coding Part of Show All Rates button
        if (e.getSource() == userInfoChangeButton) {
            dispose();
            TeacherCabinetChangeUserInfo.showTeacherForm(userInfo, response);
        }

        //Coding Part of Create Lesson button
        if (e.getSource() == createLessonButton) {
            dispose();
            TeacherCabinetCreateLesson.showTeacherForm(userInfo, response);
        }

        //Coding Part of Create task in Lesson button
        if (e.getSource() == createTaskInLessonButton) {
            dispose();
            TeacherCabinetCreateTaskInLesson.showTeacherForm(userInfo, response);
        }

        //Coding Part of Rate User in Lesson button
        if (e.getSource() == rateStudentInLessonButton) {
            dispose();
            TeacherCabinetRateUserInLesson.showTeacherForm(userInfo, response);
        }

        //Coding Part of Logout button
        if (e.getSource() == logoutButton) {
            JSONObject response = HttpLogout.httpLogout(userInfo);
            JOptionPane.showMessageDialog(this, response.get("message"));
            dispose();
            LoginForm.showLoginForm();
        }

    }
}
