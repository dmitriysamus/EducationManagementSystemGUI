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
 * Класс {@link TeacherCabinetRateUserInLesson} отображает форму метода
 * {@link UserCabinet# rateStudentInLessonButton}  пользователя с
 * ролью USER.
 *
 * @author habatoo, dmitriysamus
 * @version 0.001
 */
public class TeacherCabinetRateUserInLesson extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    Container container = getContentPane();
    JButton logoutButton = new JButton("Logout");
    JButton backButton = new JButton("Back");

    JLabel lessonIdLabel = new JLabel("Lesson Id");
    JTextField lessonIdTextField = new JTextField();
    JLabel studentIdLabel = new JLabel("Student id");
    JTextField studentIdTextField = new JTextField();
    JLabel gradeLabel = new JLabel("Grade (pass / fail)");
    JTextField gradeTextField = new JTextField();

    JLabel lessonLabel = new JLabel("Working with Lesson");
    JLabel methodLabel = new JLabel("Rating user in lesson");
    JButton rateUserButton = new JButton("Rate User");
    // при http POST запросе по адресу .../api/auth/groups/rate/{lessonId}

    public TeacherCabinetRateUserInLesson(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * Метод {@link TeacherCabinetRateUserInLesson#setLayoutManager}
     * устанавливает формат layout manager
     * по умолчанию.
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * Метод {@link TeacherCabinetRateUserInLesson#setLocationAndSize}
     * ориентирует месторасположение элементов
     * формы кабинета пользователя USER.
     */
    public void setLocationAndSize() {
        logoutButton.setBounds(10, 530, 180, 30);
        backButton.setBounds(200, 530, 180, 30);

        lessonIdLabel.setBounds(10, 150, 180, 30);
        lessonIdTextField.setBounds(200, 150, 180, 30);
        studentIdLabel.setBounds(10, 200, 180, 30);
        studentIdTextField.setBounds(200, 200, 180, 30);
        gradeLabel.setBounds(10, 250, 180, 30);
        gradeTextField.setBounds(200, 250, 180, 30);

        lessonLabel.setBounds(10, 50, 180, 30);
        methodLabel.setBounds(10, 100, 180, 30);
        rateUserButton.setBounds(10, 300, 180, 30);
    }

    /**
     * Метод {@link TeacherCabinetRateUserInLesson#addComponentsToContainer}
     * добавляет элементовы формы кабинета пользователя USER
     * в контейнер для отображения.
     */
    public void addComponentsToContainer() {
        container.add(logoutButton);
        container.add(backButton);

        container.add(lessonIdLabel);
        container.add(lessonIdTextField);
        container.add(studentIdLabel);
        container.add(studentIdTextField);
        container.add(gradeLabel);
        container.add(gradeTextField);

        container.add(lessonLabel);
        container.add(methodLabel);
        container.add(rateUserButton);
    }

    /**
     * Метод {@link TeacherCabinetRateUserInLesson#addActionEvent}
     * добавляет элементовы формы кабинета пользователя USER
     * для реагирования на нажатия кнопок.
     */
    public void addActionEvent() {
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);

        rateUserButton.addActionListener(this);
    }

    /**
     * Статический метод {@link TeacherCabinetRateUserInLesson#showTeacherForm(JSONObject, JSONObject)}
     * создает форму кабинета пользователя USER
     * для отображения.
     *
     * @param userInfo JSONObject
     * @param response JSONObject
     */
    public static void showTeacherForm(JSONObject userInfo, JSONObject response) {
        TeacherCabinetRateUserInLesson frame = new TeacherCabinetRateUserInLesson(userInfo, response);
        frame.setTitle("Teacher Cabinet");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Метод {@link TeacherCabinetRateUserInLesson#actionPerformed(ActionEvent)}
     * добавляет логику обработки при нажатии
     * <p>
     * кнопки возврата в кабинет пользователя USER {@link TeacherCabinetRateUserInLesson#backButton}
     * кнопки выхода {@link TeacherCabinetRateUserInLesson#logoutButton}
     *
     * @param e ActionEvent событие нажатия кнопки для обработки
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Coding Part of Rate Student button /api/auth/groups/rate/{lessonId}
        if (e.getSource() == rateUserButton) {
            String lessonNum;
            String studentId;
            String grade;
            lessonNum = lessonIdTextField.getText();
            studentId = studentIdTextField.getText();
            grade = gradeTextField.getText();

            String JSON_STRING = "{\"grade\":\"" + grade + "\",\"student\":\"" + studentId + "\"} ";
            String url = "http://localhost:8080/api/auth/groups/rate/" + lessonNum;

            JSONObject response = HttpPostUtil.httpRequest(url, JSON_STRING, (String) this.userInfo.get("accessToken"));
            dispose();
            TeacherCabinetRateUserInLesson.showTeacherForm(userInfo, response);

            if (null != response && response.get("message").equals("Student rated successfully!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Student rated successfully!" +
                        "\nLesson id = " + lessonNum +
                        "\nStudent id = " + studentId +
                        "\nGrade = " + grade);
            } else if (null != response && response.get("message").equals("Error: Lesson does not exist!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Lesson does not exist!",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (null != response && response.get("message").equals("Error: User does not exist!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: User does not exist!",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (null != response && response.get("message").equals(
                    "Error: Student does not exists in the group!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Student does not exists in the group!",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (null != response && response.get("message").equals("Error: Incorrect grade!")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Incorrect grade!",
                        "Teacher error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: Student can't be rated!",
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
            JSONObject response = HttpLogout.httpLogout(userInfo);
            JOptionPane.showMessageDialog(this, response.get("message"));
            dispose();
            LoginForm.showLoginForm();
        }

    }

}
