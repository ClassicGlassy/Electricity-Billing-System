package com.userinterface;

import javax.swing.*;
import java.awt.*;

public class Project extends JFrame {
    Project(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar mb = new JMenuBar();

        setJMenuBar(mb);
        setLayout(new FlowLayout());


        JMenu master = new JMenu("Master");
        mb.add(master);

        JMenuItem newCustomer = new JMenuItem("New Customer");
        master.add(newCustomer);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

//    TODO Remove Main function. Debugging Only
public static void main(String[] args) {
    new Project();
}
}
