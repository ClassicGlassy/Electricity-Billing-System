package com.userinterface.components;

import javax.swing.*;

public class passwordComponent extends JPasswordField {
//        Initializing Utility Package
    Utility _util = new Utility();
    public passwordComponent(int width){
        this.setColumns(width);
        this.setFont(_util.convertToFont("font/OpenSans.ttf",14));
    }

    public passwordComponent(int width, int fontSize){
        this.setColumns(width);
        this.setFont(_util.convertToFont("font/OpenSans.ttf",fontSize));
    }
}
