
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * @author ismail
 */
public class App {

    private java.util.List<Departure> departures;
    private DisplayTable displayTable;

    public App() {
        departures=new java.util.ArrayList<Departure>();
        displayTable=new DisplayTable(departures);
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
        JFrame frame = new JFrame("Vasttrafik Display Board");
        frame.setBounds(150, 150, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {

            VasttrafikApiUtils.getDepartures("Vårväderstorget", new VasttrafikApiUtils.CallbackInterface() {
                @Override
                public void onSuccess(java.util.List<Departure> departureList) {
                    departures.clear();
                    System.out.println("size "+departureList.size());
                    for (Departure d:departureList) {
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
        int delay = 100; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                displayTable.updateTable();
            }
        };
        new Timer(delay, taskPerformer).start();

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
