package com.userinterface;

import javax.swing.*;
import java.awt.*;


public class Login extends JFrame {

    public Login(String title){
        super(title);           // Sets the Title of the JFrame

        setLayout(new BorderLayout());  // Border layout as JFrame's layout manager

        Utility converter = new Utility();
        Container container = getContentPane();

        JLabel icon = new JLabel(converter.convertToIcon("sprites/FontAwesome/user_Normal.png",100,100));
        container.add(icon,BorderLayout.NORTH);

        JPanel bg_panel = new JPanel();
        bg_panel.setBackground(Color.GREEN);
        bg_panel.setLayout(new GridBagLayout());

//        GridBag Constrains
        GridBagConstraints gc = new GridBagConstraints();

//        gc.anchor = GridBagConstraints.WEST;
//        gc.weightx=0;
//        gc.weighty=0.5;
//
//        gc.gridx = 0;
//        gc.gridy = 0;
//
//        JLabel usernameLabel = new JLabel("Username: ");
//        usernameLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//        bg_panel.add(usernameLabel);
//
////                Second Column
//        gc.anchor = GridBagConstraints.EAST;
////        gc.gridx = 0;
//        gc.gridy = 1;
//
//        JTextField nameField = new JTextField(10);
//        nameField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//        bg_panel.add(nameField);

        Font labelFont = converter.convertToFont("font/OpenSans.ttf",24);


        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(labelFont);
        JLabel occupationLabel = new JLabel("Occupation: ");
        occupationLabel.setFont(labelFont);

        JTextField nameField = new JTextField(10);
        JTextField occupationField = new JTextField(10);


        //        First column ////

//        gc.anchor = GridBagConstraints.LINE_END;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx=0;
        gc.weighty=0;

        gc.gridx = 0;
        gc.gridy = 0;

        bg_panel.add(nameLabel,gc);
        gc.gridx = 0;
        gc.gridy = 1;

        bg_panel.add(nameField,gc);



//        Second Column
//        gc.anchor = GridBagConstraints.LINE_START;


        gc.weighty=0;

        gc.gridx = 0;
        gc.gridy = 2;
        bg_panel.add(occupationLabel,gc);

        gc.weightx=0;
        gc.weighty=0;
        gc.gridy = 3;
        bg_panel.add(occupationField,gc);



        container.add(bg_panel,BorderLayout.CENTER);





        setSize(360,640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }


}