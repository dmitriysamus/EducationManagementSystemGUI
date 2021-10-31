package educationManagementSystemGUI.cabinets;

public class TeacherCabinet extends UserCabinet {
}

///*
//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.methods.*;
//
//import java.awt.*;
//
//public class SwingClient extends javax.swing.JFrame {
//    HttpClient client = new HttpClient();
//
//    public SwingClient() {
//        initComponents();
//    }
//
//    /**
//     * Gets a page via http and displays the cookies and the page
//     */
//    public void testCookies() {
//        // Sometimes the Internet is slow.
//        this.setCursor(Cursor.WAIT_CURSOR);
//        // We set up a GET request using the URL entered in the URL
//        // textfield.
//        HttpMethod method = new GetMethod(this.urlTextField.getText());
//        try {
//            client.executeMethod(method);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // Set the response label to the returned value.
//        this.responseCodeLabel.setText(method.getStatusCode() + "");
//        // Now, we start building the text to display.
//        StringBuffer response = new StringBuffer();
//        // First, we loop through the currently set cookies.
//        Cookie[] cookies = client.getState().getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            response.append(cookies[i].getName());
//            response.append("=");
//            response.append(cookies[i].getValue());
//            response.append("\n");
//        }
//        response.append("================================");
//        response.append("\n");
//        // Next, we get the response as a String
//        response.append(method.getResponseBodyAsString());
//        // Finally, we display the response.
//        this.responseText.setText(response.toString());
//        // Some clean-up.
//        method.releaseConnection();
//        method.recycle();
//        // Set the cursor back
//        this.setCursor(Cursor.DEFAULT_CURSOR);
//    }
