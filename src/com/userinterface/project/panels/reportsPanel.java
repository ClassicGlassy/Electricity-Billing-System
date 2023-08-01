package com.userinterface.project.panels;

import com.userinterface.components.labelComponent;

import javax.swing.*;
import java.awt.*;

public class reportsPanel extends JPanel {
//    ADMIN
    public reportsPanel(){
        setLayout(new GridBagLayout());

        labelComponent test = new labelComponent("Hello this is Report Panel", 50);
        add(test);
        setVisible(true);
    }
//    TENANT
    public reportsPanel(int userid){
        setLayout(new GridBagLayout());

        labelComponent test = new labelComponent("Hello this is Report Panel", 50);
        add(test);
        setVisible(true);
    }

}
