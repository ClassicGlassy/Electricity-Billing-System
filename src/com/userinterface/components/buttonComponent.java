package com.userinterface.components;

import javax.swing.*;

public class buttonComponent extends JButton {
//    Initializing Utility
    Utility _util = new Utility();
    public buttonComponent(String text,String IconURL, float FontSize){
        this.setText(text);
        this.setIcon(_util.convertToIcon(IconURL,25,25));
        this.setFont(_util.convertToFont("font/OpenSans.ttf",FontSize));
    }

    public buttonComponent(String text){
        this.setText(text);
        this.setFont(_util.convertToFont("font/OpenSans.ttf",15));
    }
}
