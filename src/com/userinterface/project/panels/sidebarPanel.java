package com.userinterface.project.panels;

import com.userinterface.components.*;

import javax.swing.*;
import java.awt.*;

public class sidebarPanel extends JPanel {
    buttonComponent btn1, btn2, btn3, btn4 , btn5;
    imageComponent user_image;

    public sidebarPanel(int acc_type, JFrame parentFrame , int userid){
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
        btn1.addActionListener(e -> {
            dashboardPanel dashboard = new dashboardPanel(userid, acc_type);
            parentFrame.getContentPane().remove(1);
            parentFrame.getContentPane().add(dashboard,BorderLayout.CENTER);
            parentFrame.getContentPane().validate();
            parentFrame.getContentPane().repaint();

        });
        add(btn1,gbc);

        //        Row 5: Profile
        btn5 = new buttonComponent("Profile","sprites/iconfinder/panels/8542594_user_edit_icon.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
        btn5.addActionListener(e -> {
            profilesPanel profile = new profilesPanel(userid);
            parentFrame.getContentPane().remove(1);
            parentFrame.getContentPane().add(profile,BorderLayout.CENTER);
            parentFrame.getContentPane().validate();
            parentFrame.getContentPane().repaint();

        });
        gbc.gridy = 5;
        add(btn5,gbc);

        if(acc_type == 0){
            user_Image_URL = "sprites/users/admin_Ico.png";

            //        Row 2: Reports
            btn2 = new buttonComponent("Reports","sprites/iconfinder/panels/bar_chart.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
            btn2.addActionListener(e -> {
                reportsPanel report = new reportsPanel();
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(report,BorderLayout.CENTER);
                parentFrame.getContentPane().validate();
                parentFrame.getContentPane().repaint();

            });
            gbc.gridy = 2;
            add(btn2,gbc);

            //        Row 3: Manage Users
            btn3 = new buttonComponent("Manage Users","sprites/iconfinder/panels/user_configure.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
            btn3.addActionListener(e -> {
                mnguserPanel users = new mnguserPanel(parentFrame);
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(users,BorderLayout.CENTER);
                parentFrame.getContentPane().validate();
                parentFrame.getContentPane().repaint();

            });
            gbc.gridy = 3;
            add(btn3,gbc);

            //        Row 4: Generate Bills
            btn4 = new buttonComponent("Generate Bill","sprites/iconfinder/panels/history.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
            btn4.addActionListener(e -> {
                generatebillPanel bill = new generatebillPanel();
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(bill,BorderLayout.CENTER);
                parentFrame.getContentPane().validate();
                parentFrame.getContentPane().repaint();
            });
            gbc.gridy = 4;
            add(btn4,gbc);
        }

        else{
            //        Row 2: Reports
            btn2 = new buttonComponent("Transactions","sprites/iconfinder/panels/receipt.png",30,50,50,Color.decode("#E2EAFC"), Color.decode("#2B3674"));
            btn2.addActionListener(e -> {
                reportsPanel report = new reportsPanel(userid);
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(report,BorderLayout.CENTER);
                parentFrame.getContentPane().validate();
                parentFrame.getContentPane().repaint();

            });
            gbc.gridy = 2;
            add(btn2,gbc);
        }

        //        Profile
        user_image = new imageComponent(user_Image_URL,150,150);
        gbc.gridy = 0; gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(user_image,gbc);

        setVisible(true);

//        System.out.println("Side Panel: "+getPreferredSize());
    }
}
