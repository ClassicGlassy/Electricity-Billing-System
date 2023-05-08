package com.userinterface;

import javax.swing.*;
import java.awt.*;

public class mainFrame extends JFrame {

    public mainFrame(String title){
        super(title);

//        Set Layout Manager
        setLayout(new BorderLayout());


//        Create Swing components
        JTextArea textArea = new JTextArea();
        JButton button = new JButton("Click me!");

        details detailsPanel = new details();


//        Add Swing Components to it's content pane
        Container c = getContentPane();

        c.add(textArea, BorderLayout.CENTER);
        c.add(button, BorderLayout.SOUTH);
        c.add(detailsPanel, BorderLayout.WEST);



//        Add behaviour
        button.addActionListener(e -> textArea.append("Hello\n"));

    }

    public static void main(String[] args) {
        new mainFrame("Hello");
    }
}
