package com.userinterface.project.panels;

import com.userinterface.components.*;
import com.userinterface.components.labelComponent;

import javax.swing.*;
import java.awt.*;

public class sidebarPanel extends JPanel {
    buttonComponent btn1, btn2, btn3, btn4 , btn5;
    imageComponent user_image;

    public sidebarPanel(int acc_type){
        setBackground(Color.decode("#E2EAFC"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 50;

        String user_Image_URL = "sprites/users/user_avatar_icon.png";

        //        Row 1: Dashboard
        btn1 = new buttonComponent("Dashboard","sprites/iconfinder/panels/dashboard.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
        gbc.gridx =0; gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(btn1,gbc);

        //        Row 5: Profile
        btn5 = new buttonComponent("Profile","sprites/iconfinder/panels/8542594_user_edit_icon.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
        gbc.gridy = 5;
        add(btn5,gbc);

        if(acc_type == 0){
            user_Image_URL = "sprites/users/admin_Ico.png";

            //        Row 2: Reports
            btn2 = new buttonComponent("Reports","sprites/iconfinder/panels/bar_chart.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
            gbc.gridy = 2;
            add(btn2,gbc);

            //        Row 3: Manage Users
            btn3 = new buttonComponent("Manage Users","sprites/iconfinder/panels/user_configure.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
            gbc.gridy = 3;
            add(btn3,gbc);

            //        Row 4: Generate Bills
            btn4 = new buttonComponent("Generate Bill","sprites/iconfinder/panels/history.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
            gbc.gridy = 4;
            add(btn4,gbc);
        }

        else{
            //        Row 2: Reports
            btn2 = new buttonComponent("Transactions","sprites/iconfinder/panels/receipt.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
            gbc.gridy = 2;
            add(btn2,gbc);
        }

        //        Profile
        user_image = new imageComponent(user_Image_URL,150,150);
        gbc.gridy = 0; gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(user_image,gbc);

        setVisible(true);
    }
}
