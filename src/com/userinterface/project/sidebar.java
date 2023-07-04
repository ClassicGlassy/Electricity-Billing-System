package com.userinterface.project;

import com.userinterface.components.*;

import javax.swing.*;
import java.awt.*;

public class sidebar extends JPanel {
    buttonComponent dashboard, reports, mng_user, generate_bill, profile;

    public sidebar(int selectedInd){

        setMinimumSize(new Dimension(500,0));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //        Row 1: Dashboard
        dashboard = new buttonComponent("Dashboard","sprites/iconfinder/Typicons/216242_home_icon.png",30,50,50,Color.gray, Color.white);
        gbc.gridx =0; gbc.gridy = 1;
        add(dashboard,gbc);

//        Row 2: Reports
        reports = new buttonComponent("Reports","sprites/iconfinder/Typicons/216136_bar_chart_icon.png",30,50,50,Color.gray, Color.white);
        gbc.gridy = 2;
        add(reports,gbc);

//        Row 3: Manage Users
        mng_user = new buttonComponent("Manage Users","sprites/iconfinder/Typicons/8542490_user_cog_icon.png",30,50,50,Color.gray, Color.white);
        gbc.gridy = 3;
        add(mng_user,gbc);

//        Row 4: Generate Bills
        generate_bill = new buttonComponent("Generate Bill","sprites/iconfinder/Typicons/9035965_receipt_sharp_icon.png",30,50,50,Color.gray, Color.white);
        gbc.gridy = 4;
        add(generate_bill,gbc);

//        Row 5: Profile
        profile = new buttonComponent("Profile","sprites/iconfinder/Typicons/8542594_user_edit_icon.png",30,50,50,Color.gray, Color.white);
        gbc.gridy = 5;
        add(profile,gbc);


        setBackground(Color.RED);
        setVisible(true);
    }
}
