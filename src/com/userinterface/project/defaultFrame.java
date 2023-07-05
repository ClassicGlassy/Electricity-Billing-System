package com.userinterface.project;

import javax.swing.*;
import com.userinterface.project.menu.sidebar_Admin;

import java.awt.*;

public class defaultFrame extends JFrame {
    public defaultFrame(){
        setTitle("Electricity Billing System");

        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
