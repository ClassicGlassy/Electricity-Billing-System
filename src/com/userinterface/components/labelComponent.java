package com.userinterface.components;

import javax.swing.*;

public class labelComponent extends JLabel {

    //        Initializing Utility Package
    Utility _util = new Utility();

    public labelComponent(String text,int fontSize){
        this.setFont(_util.convertToFont("font/OpenSans.ttf",fontSize));
        this.setText(text);
    }
    public labelComponent(String text){
        this.setFont(_util.convertToFont("font/OpenSans.ttf",16));
        this.setText(text);
    }
}
