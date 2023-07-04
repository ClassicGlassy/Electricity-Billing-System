package com.userinterface.project;

import javax.swing.*;
import java.awt.*;

public class homepage {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Electricity Billing System");
        frame.setLayout(new BorderLayout());
        sidebar s_bar = new sidebar(0);
        test t= new test();
        frame.add(s_bar, BorderLayout.WEST);
//        frame.setBackground(Color.decode("#03045E"));
        JPanel background = new JPanel();
        background.setBackground(Color.decode("#E0E1DD"));
        frame.add(background);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
