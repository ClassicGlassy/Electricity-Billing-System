package com.userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Project extends JFrame {
    public Project(String Title){
        super(Title);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JMenuBar mb = new JMenuBar();

        setJMenuBar(mb);
        setLayout(new FlowLayout());

        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);
        mb.add(master);

        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setMnemonic('D');
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        master.add(newCustomer);

        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setMnemonic('M');
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        master.add(customerDetails);

        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setMnemonic('N');
        depositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        master.add(depositDetails);

        JMenuItem calculateBill = new JMenuItem("Calculate Bills");
        calculateBill.setMnemonic('N');
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        master.add(calculateBill);


        JMenu info = new JMenu("Information");
        info.setForeground(Color.RED);
        mb.add(info);

        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setMnemonic('N');
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        info.add(updateInfo);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setMnemonic('N');
        viewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        info.add(viewInfo);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

//    TODO Remove Main function. Debugging Only
public static void main(String[] args) {
    new Project("Admin");
}
}
