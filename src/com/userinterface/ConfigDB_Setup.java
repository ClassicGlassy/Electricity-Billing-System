package com.userinterface;

import com.initial.Init;
import com.initial.SerializeFile;
import com.userinterface.components.buttonComponent;
import com.userinterface.components.labelComponent;
import com.userinterface.components.passwordComponent;
import com.userinterface.components.textboxComponent;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ConfigDB_Setup extends JFrame {
    JLabel DBusername, DBpassword,DBPort;
    JTextField DBusernameT,DBPortT;
    JPasswordField DBpasswordT;
    JButton DBsave,DBreset;

    public ConfigDB_Setup(){
        super("Configure Database");
        setLayout(new BorderLayout());

        //        Initializing Panel to add Elements named loginPanel
        JPanel DBConfigPanel = new JPanel(new GridBagLayout());
        DBConfigPanel.setBackground(Color.WHITE);

        //        GridBag Constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,10,20);

//        Row 1: Username
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy=0;gbc.gridx=0;
        gbc.gridwidth = 2;

        DBusername = new labelComponent("Username:");
        DBConfigPanel.add(DBusername,gbc);

        gbc.gridx=2;


        DBusernameT = new textboxComponent(5);
        DBConfigPanel.add(DBusernameT,gbc);


//        Row 2: Password
        gbc.gridy=1;gbc.gridx=0;
        gbc.gridwidth = 2;

        DBpassword = new labelComponent("Password:");
        DBConfigPanel.add(DBpassword,gbc);

        gbc.gridx=2;


        DBpasswordT = new passwordComponent(5);
        DBConfigPanel.add(DBpasswordT,gbc);

//        Row 3: Port
        gbc.gridy=2;gbc.gridx=0;
        gbc.gridwidth = 2;

        DBPort = new labelComponent("Port:");
        DBConfigPanel.add(DBPort,gbc);

        gbc.gridx=2;

        DBPortT = new textboxComponent(5);
        DBConfigPanel.add(DBPortT,gbc);

//        Row 4: Save and Reset Button

        gbc.gridy=3;gbc.gridx=0;
        gbc.anchor = GridBagConstraints.CENTER;

        DBsave = new buttonComponent("Save",Color.BLACK,Color.white);
        DBConfigPanel.add(DBsave,gbc);
        DBsave.addActionListener(e ->save());

        gbc.gridx=3;
        DBreset = new buttonComponent("Retry",Color.BLACK,Color.white);
        DBConfigPanel.add(DBreset,gbc);
        DBreset.addActionListener(e ->retry());


        add(DBConfigPanel,BorderLayout.CENTER);


        //        Properties of Login Frame.
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setVisible(true);


    }

    private void save(){
        if(!Objects.equals(DBusername.getText(), "") && !Objects.equals(DBPortT.getText(), "")){
            new SerializeFile(DBusernameT.getText(),String.valueOf(DBpasswordT.getPassword()),DBPortT.getText());
            JOptionPane.showMessageDialog(null,"Configuration save successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
            reconnectDB();
        }
        else{
            JOptionPane.showMessageDialog(null,"Please fill all the fields","Unsuccessful",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retry(){
        reconnectDB();
    }

    private void reconnectDB(){
        setVisible(false);
        new Init();
    }
}
