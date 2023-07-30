package com.userinterface.project.panels;

import com.userinterface.components.labelComponent;

import javax.swing.*;
import java.awt.*;

public class generatebillPanel extends JPanel {
    public generatebillPanel(){
        setLayout(new GridBagLayout());

        System.out.println(getPreferredSize());

        labelComponent test = new labelComponent("Hello this is bill Panel", 50);
        add(test);
        setVisible(true);

    }
}
