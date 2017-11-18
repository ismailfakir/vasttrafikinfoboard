package net.cloudcentrik.vasttrafik;

import javax.swing.*;

public class DisplayBoard {


    private JPanel panel1;
    private JButton displayButton;

    public DisplayBoard(){

        panel1=new JPanel();
        displayButton=new JButton("Display");
        panel1.add(displayButton);

        panel1.setBounds(100,100,300,300);

        panel1.setVisible(true);

    }
}
