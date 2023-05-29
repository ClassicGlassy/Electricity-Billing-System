package com.userinterface.components;

import javax.swing.*;

public class textboxComponent extends JTextField {
//        Initializing Utility Package
    Utility _util = new Utility();
    public textboxComponent(int column){
        this.setColumns(column);
        this.setFont(_util.convertToFont("font/OpenSans.ttf",14));
    }

    public textboxComponent(int column,int fontSize){
        this.setColumns(column);
        this.setFont(_util.convertToFont("font/OpenSans.ttf",fontSize));
    }
}
