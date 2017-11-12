package net.cloudcentrik.vasttrafik;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisplayBoardFrame extends JPanel{

    private boolean DEBUG = false;
    private static List<Departure> departures;

    public DisplayBoardFrame(List<Departure> departures){
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
            //String now = new SimpleDateFormat("HH-mm").format(new Date());
            /*Date now=new Date();

            String strTime = departures.get(i).getTime();
            DateFormat format = new SimpleDateFormat("HH:mm");
            Date date = format.parse(strTime);*/

            values.add(new String[] {departures.get(i).getName(),departures.get(i).getTime(),departures.get(i).getDirection()});
        }


        //final JTable table = new JTable(data, columnNames);
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(100,50));
        header.setFont(new Font("Serif", Font.BOLD, 20));
        header.setBackground(Color.YELLOW);

        table.setBackground(Color.decode("#058dc7"));
        table.setForeground(Color.white);
        table.setGridColor(Color.yellow);
        //table.setShowGrid(false);
        table.setRowMargin(5);

        table.setRowHeight(table.getRowHeight() + 30);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setIntercellSpacing(new Dimension(40,10));

        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
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
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
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

    public static void createAndShowGUI(List<Departure> departures){
        //Create and set up the window.
        JFrame frame = new JFrame("Vasttrafik Display Board :: Vårvädersgatan");
        frame.setBounds(150,150,900,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DisplayBoardFrame newContentPane = new DisplayBoardFrame(departures);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
