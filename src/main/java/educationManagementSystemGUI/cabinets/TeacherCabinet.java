package educationManagementSystemGUI.cabinets;

import educationManagementSystemGUI.forms.LoginForm;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherCabinet extends UserCabinet {

    TeacherCabinet(JSONObject userInfo, JSONObject response) {
        super();
    }

    public static void showCabinetForm(JSONObject userInfo, JSONObject response) {
        TeacherCabinet frame = new TeacherCabinet(userInfo, response);
        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout());
        JLabel label = new JLabel();
        label.setText((String) userInfo.get("username"));
        container.add(label);

        frame.setTitle("Teacher Cabinet");
        frame.setVisible(true);
        frame.getContentPane().add(label);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of SHOW button
        if (e.getSource() == showButton) {
            dispose();
            LoginForm.showLoginForm();

        }
    }
}
