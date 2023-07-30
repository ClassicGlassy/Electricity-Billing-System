package com.userinterface.project.panels;

import com.userinterface.components.labelComponent;

import javax.swing.*;
import java.awt.*;

public class mnguserPanel extends JPanel {
    public mnguserPanel(){
        setLayout(new GridBagLayout());

        labelComponent test = new labelComponent("Hello this is users Panel", 50);
        add(test);
        setVisible(true);
    }
}
