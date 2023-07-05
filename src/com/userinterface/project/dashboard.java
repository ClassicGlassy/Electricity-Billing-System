package com.userinterface.project;

import com.userinterface.project.menu.sidebarPanel;

import javax.swing.*;
import java.awt.*;

public class dashboard extends defaultFrame{

    public dashboard(int acc_type)
    {
        add(new sidebarPanel(acc_type),BorderLayout.WEST);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new dashboard(0);
    }

}
