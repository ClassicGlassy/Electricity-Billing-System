import com.userinterface.login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new login();
                frame.setLocationRelativeTo(null);
            }
        });



    }
}
