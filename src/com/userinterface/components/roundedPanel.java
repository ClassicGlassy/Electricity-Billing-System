package com.userinterface.components;

import javax.swing.*;
import java.awt.*;

public class roundedPanel extends JPanel {
    int width, height;
    Color bg, fg;
    public roundedPanel(int width, int height, Color bg){
        this.width = width;
        this.height = height;
        this.bg = bg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(bg);
        g.fillRoundRect(0,0,width,height,30,30);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }
}
