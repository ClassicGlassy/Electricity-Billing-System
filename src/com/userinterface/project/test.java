package com.userinterface.project;

import javax.swing.*;
import java.awt.*;

public class test extends JPanel {

    test(){
        setLayout(new GridLayout(6,1));
        add(new Button("1"));
        add(new Button("2"));
        add(new Button("3"));
        add(new Button("4"));
        add(new Button("5"));
        add(new Button("6"));
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new test();
//    }
}

