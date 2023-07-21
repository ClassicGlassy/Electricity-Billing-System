package com.userinterface.project;

import com.userinterface.project.panels.sidebarPanel;

import javax.swing.*;
import java.awt.*;

public class applicationFrame extends JFrame{

    public applicationFrame(int acc_type)
    {
        setTitle("Electricity Billing System");

        setLayout(new BorderLayout());

        add(new sidebarPanel(acc_type),BorderLayout.WEST);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new applicationFrame(1);
    }

}
