import com.userinterface.register;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new register();
                frame.setLocationRelativeTo(null);
            }
        });



    }
}
