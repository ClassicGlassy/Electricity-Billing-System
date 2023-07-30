package com.userinterface.project;

import com.userinterface.project.panels.dashboardPanel;
import com.userinterface.project.panels.profilesPanel;
import com.userinterface.project.panels.sidebarPanel;

import javax.swing.*;
import java.awt.*;

public class applicationFrame extends JFrame{

    public applicationFrame(int acc_type)
    {
        setTitle("Electricity Billing System");
        setLayout(new BorderLayout());

//        SidePanel
        add(new sidebarPanel(acc_type, this),BorderLayout.WEST);
        add(new dashboardPanel(),BorderLayout.CENTER);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        System.out.println("Frame: "+ getPreferredSize());
    }

//    TODO Remove Main Function
    public static void main(String[] args) {
        new applicationFrame(1);
    }

}
