package educationManagementSystemGUI.cabinets.user;

import educationManagementSystemGUI.cabinets.AdminCabinet;
import educationManagementSystemGUI.forms.LoginForm;
import educationManagementSystemGUI.utils.HttpPutUtil;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCabinetUserEdit extends JFrame implements ActionListener {

    JSONObject userInfo;
    JSONObject response;
    JButton showButton = new JButton("Logout");

    Container container = getContentPane();
    JLabel userEditIdIdLabel = new JLabel("USER ID FOR EDIT");
    JTextField userEditTextIdField = new JTextField();
    JLabel userEditNameLabel = new JLabel("USER NAME FOR EDIT");
    JTextField userEditNameTextField = new JTextField();
    JLabel userEditEmailLabel = new JLabel("USER EMAIL FOR EDIT");
    JTextField userEditEmailTextField = new JTextField();
    JLabel userEditPasswordLabel = new JLabel("USER PASSWORD FOR EDIT");
    JTextField userEditPasswordTextField = new JTextField();
    JButton editButton = new JButton("SAVE EDIT");

    UserCabinetUserEdit(JSONObject userInfo, JSONObject response) {
        this.userInfo = userInfo;
        this.response = response;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
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

    public void addComponentsToContainer() {
        container.add(userEditIdIdLabel);
        container.add(userEditTextIdField);
        container.add(userEditNameLabel);
        container.add(userEditNameTextField);
        container.add(userEditEmailLabel);
        container.add(userEditEmailTextField);
        container.add(userEditPasswordLabel);
        container.add(userEditPasswordTextField);
        container.add(editButton);
        container.add(showButton);
    }

    public void addActionEvent() {
        editButton.addActionListener(this);
    }

    public static void showEditUserForm(JSONObject userInfo, JSONObject response) {
        UserCabinetUserEdit frame = new UserCabinetUserEdit(userInfo, response);
        frame.setTitle("Edit User Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Coding Part of Edit user Info button /api/auth/users/{id}
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGOUT button
        if (e.getSource() == showButton) {
            dispose();
            LoginForm.showLoginForm();
        }


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
            UserCabinetUserEdit.showEditUserForm(userInfo, response);

        }
    }

}