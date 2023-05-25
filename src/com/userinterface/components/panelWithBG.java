package com.userinterface.components;

import javax.swing.*;
import java.awt.*;

public class panelWithBG extends JPanel {
    private final Image image;

    public panelWithBG(String URL) {
        ImageIcon temp = new ImageIcon(ClassLoader.getSystemResource(URL));
        image = temp.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
