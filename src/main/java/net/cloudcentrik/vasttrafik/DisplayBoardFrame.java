package net.cloudcentrik.vasttrafik;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DisplayBoardFrame extends JPanel{

    private boolean DEBUG = false;
    private static List<Departure> departures;

    public DisplayBoardFrame(List<Departure> departures) {
        super(new GridLayout(1,0));

        this.departures=departures;

        String[] columnNames = {"Line",
                "Arival Time",
                "Destination"};

        Object[][] data = {
                {departures.get(0).getName(),departures.get(0).getTime(),departures.get(0).getDirection()},
                {"Kathy", "Smith",
                        "Snowboarding"},
                {"John", "Doe",
                        "Rowing"},
                {"Sue", "Black",
                        "Knitting"},
                {"Jane", "White",
                        "Speed reading"},
                {"Joe", "Brown",
                        "Pool"}
        };

        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Line");
        columns.add("Arival Time");
        columns.add("Destination");

        for (int i = 0; i <this.departures.size(); i++) {
            values.add(new String[] {departures.get(i).getName(),departures.get(i).getTime(),departures.get(i).getDirection()});
        }


        //final JTable table = new JTable(data, columnNames);
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);
        table.setIntercellSpacing(new Dimension(20,2));
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    public static void createAndShowGUI(List<Departure> departures) {
        //Create and set up the window.
        JFrame frame = new JFrame("Vasttrafik display");
        frame.setBounds(400,300,500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DisplayBoardFrame newContentPane = new DisplayBoardFrame(departures);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
