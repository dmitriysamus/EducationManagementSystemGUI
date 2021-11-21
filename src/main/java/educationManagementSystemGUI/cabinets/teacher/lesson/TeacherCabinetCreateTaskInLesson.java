package educationManagementSystemGUI.cabinets.teacher.lesson;

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
 * Класс {@link TeacherCabinetCreateTaskInLesson} отображает форму метода
 * {@link UserCabinet# createTaskInLessonButton}  пользователя с
 * ролью USER.
 *
 * @author habatoo, dmitriysamus
 * @version 0.001
 */
public class TeacherCabinetCreateTaskInLesson extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel lessonIdLabel = new JLabel("Lesson Id");
    JTextField lessonIdTextField = new JTextField();
    JLabel taskLabel = new JLabel("Task name");
    JTextField taskTextField = new JTextField();

    JLabel lessonLabel = new JLabel("Working with Lesson");
    JLabel methodLabel = new JLabel("Creating task in lesson");
    JButton createTaskButton = new JButton("Create Task");
    // при http POST запросе по адресу .../api/auth/groups/lessons/{lessonId}

    public TeacherCabinetCreateTaskInLesson(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link TeacherCabinetCreateTaskInLesson#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link TeacherCabinetCreateTaskInLesson#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя USER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        lessonIdLabel.setBounds(10, 150, 180, 30);
        lessonIdTextField.setBounds(200, 150, 180, 30);
        taskLabel.setBounds(10, 200, 180, 30);
        taskTextField.setBounds(200, 200, 180, 30);

        lessonLabel.setBounds(10, 50, 180, 30);
        methodLabel.setBounds(10, 100, 180, 30);
        createTaskButton.setBounds(10, 300, 180, 30);
    }

    /**
     * Метод {@link TeacherCabinetCreateTaskInLesson#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя USER
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(lessonIdLabel);
        container.add(lessonIdTextField);
        container.add(taskLabel);
        container.add(taskTextField);

        container.add(lessonLabel);
        container.add(methodLabel);
        container.add(createTaskButton);
    }

    /**
     * Метод {@link TeacherCabinetCreateTaskInLesson#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        createTaskButton.addActionListener(this);
    }

    /**
     * Статический метод {@link TeacherCabinetCreateTaskInLesson#showTeacherForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя USER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showTeacherForm(JSONObject userInfo, JSONObject response) {
        TeacherCabinetCreateTaskInLesson frame = new TeacherCabinetCreateTaskInLesson(userInfo, response);
        frame.setTitle("Teacher Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link TeacherCabinetCreateTaskInLesson#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя USER {@link TeacherCabinetCreateTaskInLesson#backButton}
     * кнопки выхода {@link TeacherCabinetCreateTaskInLesson#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of Create Lesson button /api/auth/groups/lessons/{lessonId}
        if (e.getSource() == createTaskButton) {
            String lessonNum;
            String task;
            lessonNum = lessonIdTextField.getText();
            task = taskTextField.getText();
            String JSON_STRING = "{\"name\":\"" + task + "\"} ";
            String url = "http://localhost:8080/api/auth/groups/lessons/" + lessonNum;

            JSONObject response = HttpPostUtil.httpRequest(url, JSON_STRING, (String) this.userInfo.get("accessToken"));
            dispose();
            TeacherCabinetCreateTaskInLesson.showTeacherForm(userInfo, response);

            if (null != response && response.get("message").equals("Task created successfully!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Task created successfully!" +
                        "\nLesson id = " + lessonNum +
                        "\nTask name = " + task);
            } else if (null != response && response.get("message").equals("Error: Lesson does not exist")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Lesson does not exist",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Task can't be created!",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);
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
