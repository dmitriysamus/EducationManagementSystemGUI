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
 * Класс {@link TeacherCabinetCreateLesson} отображает форму метода
 * {@link UserCabinet# createLessonButton}  пользователя с
 * ролью USER.
 *
 * @author habatoo, dmitriysamus
 * @version 0.001
 */
public class TeacherCabinetCreateLesson extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel groupIdLabel = new JLabel("Group Id");
    JTextField groupIdTextField = new JTextField();
    JLabel lessonNameLabel = new JLabel("Lesson name");
    JTextField lessonTextField = new JTextField();

    JLabel lessonLabel = new JLabel("Working with Lesson");
    JLabel methodLabel = new JLabel("Creating lesson");
    JButton createLessonButton = new JButton("Create Lesson");
    // при http POST запросе по адресу .../api/auth/groups/{groupNum}/lesson

    public TeacherCabinetCreateLesson(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link TeacherCabinetCreateLesson#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link TeacherCabinetCreateLesson#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя USER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        groupIdLabel.setBounds(10, 150, 180, 30);
        groupIdTextField.setBounds(200, 150, 180, 30);
        lessonNameLabel.setBounds(10, 200, 180, 30);
        lessonTextField.setBounds(200, 200, 180, 30);

        lessonLabel.setBounds(10, 50, 180, 30);
        methodLabel.setBounds(10, 100, 180, 30);
        createLessonButton.setBounds(10, 300, 180, 30);
    }

    /**
     * Метод {@link TeacherCabinetCreateLesson#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя USER
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(groupIdLabel);
        container.add(groupIdTextField);
        container.add(lessonNameLabel);
        container.add(lessonTextField);

        container.add(lessonLabel);
        container.add(methodLabel);
        container.add(createLessonButton);
    }

    /**
     * Метод {@link TeacherCabinetCreateLesson#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        createLessonButton.addActionListener(this);
    }

    /**
     * Статический метод {@link TeacherCabinetCreateLesson#showTeacherForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя USER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showTeacherForm(JSONObject userInfo, JSONObject response) {
        TeacherCabinetCreateLesson frame = new TeacherCabinetCreateLesson(userInfo, response);
        frame.setTitle("Teacher Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link TeacherCabinetCreateLesson#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя USER {@link TeacherCabinetCreateLesson#backButton}
     * кнопки выхода {@link TeacherCabinetCreateLesson#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of Create Lesson button /api/auth/groups/{groupNum}/lesson
        if (e.getSource() == createLessonButton) {
            String groupNum;
            String lesson;
            groupNum = groupIdTextField.getText();
            lesson = lessonTextField.getText();
            String JSON_STRING = "{\"name\":\"" + lesson + "\"} ";
            String url = "http://localhost:8080/api/auth/groups/" + groupNum + "/lesson";

            JSONObject response = HttpPostUtil.httpRequest(url, JSON_STRING, (String) this.userInfo.get("accessToken"));
            if (null != response && response.get("message").equals("Lesson created successfully!")) {
                JOptionPane.showMessageDialog(this, "Lesson created successfully!" +
                        "\nGroup id = " + groupNum +
                        "\nLesson name = " + lesson);
            } else if (null != response && response.get("message").equals("Error: Lesson exists in the group!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Lesson exists in the group!",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);

            } else if (null != response && response.get("message").equals("Error: Group does not exist!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Group does not exist!",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Lesson can't be created!",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);
            }

            dispose();
            TeacherCabinetCreateLesson.showTeacherForm(userInfo, response);

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
