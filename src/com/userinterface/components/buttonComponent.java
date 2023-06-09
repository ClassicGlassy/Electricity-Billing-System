package com.userinterface.components;

import javax.swing.*;
import java.awt.*;

public class buttonComponent extends JButton {
//    Initializing Utility
    Utility _util = new Utility();
    public buttonComponent(String text,String IconURL, float FontSize,Color BG, Color FG){
        this.setText(text);
        this.setIcon(_util.convertToIcon(IconURL,25,25));
        this.setFont(_util.convertToFont("font/OpenSans.ttf",FontSize));
        this.setBackground(BG);
        this.setForeground(FG);
    }

    public buttonComponent(String text,Color BG, Color FG){
        this.setText(text);
        this.setFont(_util.convertToFont("font/OpenSans.ttf",15));
        this.setBackground(BG);
        this.setForeground(FG);
    }

    public buttonComponent(String text,String IconURL, float FontSize,int ico_width, int ico_height ,Color BG, Color FG){
        this.setText(text);
        this.setIcon(_util.convertToIcon(IconURL,ico_width,ico_height));
        this.setFont(_util.convertToFont("font/OpenSans.ttf",FontSize));
        this.setBackground(BG);
        this.setForeground(FG);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setIconTextGap(15);
        this.setBorder(null);
    }
}
