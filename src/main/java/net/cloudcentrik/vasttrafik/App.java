
/*
 * Copyright (c) 2017 Ismail Fakir
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 *  for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package net.cloudcentrik.vasttrafik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLEncoder;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * @author ismail
 */
public class App {

    private java.util.List<Departure> departures;
    private DisplayTable displayTable;
    private String stopName;
    private JFrame frame;

    public App() {

        stopName = "Hjalmar Brantingsplatsen";

        departures = new java.util.ArrayList<Departure>();
        displayTable = new DisplayTable(departures, stopName);

        createAndShowGUI();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting vasttrafik api");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }

    private void createAndShowGUI() {

        //Create and set up the window.
        frame = new JFrame(this.stopName + " Display Board");
        frame.setBounds(150, 150, 1100, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(this.getMenubar());

        try {

            VasttrafikApiUtils.getDepartures(stopName, new VasttrafikApiUtils.CallbackInterface() {
                @Override
                public void onSuccess(java.util.List<Departure> departureList) {
                    departures.clear();
                    System.out.println("size " + departureList.size());
                    for (Departure d : departureList) {
                        departures.add(d);
                    }

                }

                @Override
                public void onFailed(Throwable error) {
                    //
                    System.out.println(error.getMessage());
                }
            });


        } catch (Exception e) {

            System.err.println(e.getMessage());

        } finally {

        }

        //DisplayTable newContentPane = new DisplayTable(this.getDepartures());
        //DisplayTable displayTable = new DisplayTable(departures);
        displayTable.setOpaque(true); //content panes must be opaque
        frame.setContentPane(displayTable);

        //update Display Table
        int delay = 6000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                displayTable.updateTable();
            }
        };
        new Timer(delay, taskPerformer).start();



        //Display the window.
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private JMenuBar getMenubar(){
        JMenuBar menuBar=new JMenuBar();

        JMenu settingsMenu=new JMenu("Settings");
        JMenuItem stopMenu=new JMenuItem("stop");
        JMenuItem aboutMenu=new JMenuItem("About");
        aboutMenu.setActionCommand("about");
        aboutMenu.addActionListener(new aboutListener());
        stopMenu.setActionCommand("stop");
        stopMenu.addActionListener(new stopListener());

        settingsMenu.add(stopMenu);
        settingsMenu.add(aboutMenu);

        menuBar.add(settingsMenu);

        return menuBar;
    }

    private class stopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = new JDialog(frame, "Set Stop Name", true);
            //dialog.setPreferredSize(new Dimension(300,100));
            dialog.setLocationRelativeTo(frame);
            JPanel p = new JPanel();
            p.setPreferredSize(new Dimension(300,150));
            p.setBackground(Color.GREEN);
            JLabel textLavel=new JLabel("Choose stop");
            textLavel.setPreferredSize(new Dimension(250,30));
            p.add(textLavel);
            JTextField stopText = new JTextField();
            stopText.setPreferredSize(new Dimension(250,30));

            JButton  okButton=new JButton("Ok");
            okButton.setPreferredSize(new Dimension(100,30));
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String string = stopText.getText();
                    System.out.println(string);
                    dialog.dispose();
                    frame.update(frame.getGraphics());
                }
            });

            p.add(stopText);
            p.add(okButton);

            dialog.add(p);
            dialog.pack();
            dialog.setVisible(true);
        }
    }

    private class aboutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = new JDialog(frame, "About", true);

            dialog.setLocationRelativeTo(frame);
            JPanel p = new JPanel();
            p.setPreferredSize(new Dimension(350,200));
            p.setBackground(Color.ORANGE);
            JLabel textLavel=new JLabel("By @ Ismail Fakir");
            textLavel.setPreferredSize(new Dimension(150,30));
            p.add(textLavel);
            JTextField stopText = new JTextField();
            stopText.setPreferredSize(new Dimension(300,100));
            stopText.setText("A display board from vasttrafik real time data");
            stopText.setForeground(Color.black);
            stopText.setBackground(Color.WHITE);
            stopText.setEnabled(false);

            JButton  okButton=new JButton("Ok");
            okButton.setPreferredSize(new Dimension(100,30));
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    dialog.dispose();

                }
            });

            p.add(stopText);
            p.add(okButton);

            dialog.add(p);
            dialog.pack();
            dialog.setVisible(true);
        }
    }
}
