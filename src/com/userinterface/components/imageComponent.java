package com.userinterface.components;

import javax.swing.*;

public class imageComponent extends JLabel {
//        Initializing Utility Package
    Utility _util = new Utility();
    public imageComponent(String URL, int width, int height){
        this.setIcon(_util.convertToIcon(URL,width,height));
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
