package net.cloudcentrik.vasttrafik;

import net.cloudcentrik.vasttrafik.example.TableTimeChange;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;

public class DisplayBoardFrame extends JPanel{

    private boolean DEBUG = false;
    private static List<Departure> departures;

    public DisplayBoardFrame(List<Departure> departures){
        super(new BorderLayout(10,10));
        this.setLayout(new BorderLayout(10,20));

        System.out.println(departures.size());

        this.departures=new ArrayList<Departure>();
        this.departures.addAll(departures);

        VasttrafikUtils.isAfter(departures.get(0).getTime(),departures.get(0).getDate());

        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();

        columns.add("Line");
        columns.add("Track");
        columns.add("Arival Time");
        columns.add("Destination");

        for (int i = 0; i <this.departures.size(); i++) {
            values.add(new String[] {departures.get(i).getName(),
                    departures.get(i).getTrack(),departures.get(i).getTime(),
                    departures.get(i).getDirection()});
        }


        //final JTable table = new JTable(data, columnNames);
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        JTable table = new JTable(tableModel){

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);

                //  Alternate row color
                if (!isRowSelected(row)){
                    c.setBackground(row % 2 == 0 ? getBackground() : Color.BLACK);
                    c.setForeground(row % 2 == 0 ? getForeground() : Color.WHITE);
                    //VasttrafikUtils.getCurrentTime();
                }

                return c;
            }

        };

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(100,50));
        header.setFont(new Font("Serif", Font.BOLD, 20));
        header.setBackground(Color.YELLOW);

        table.setBackground(Color.decode("#058dc7"));
        table.setForeground(Color.white);
        table.setGridColor(Color.yellow);
        //table.setShowGrid(false);
        table.setRowMargin(0);

        table.setRowHeight(table.getRowHeight() + 30);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setIntercellSpacing(new Dimension(1,1));

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

        JPanel panelBorder = new JPanel();
        //set title border
        setTitle(panelBorder);

        panelBorder.add(scrollPane);

        //Add the scroll pane to this panel.
        add(panelBorder,BorderLayout.PAGE_END);
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

    public void createAndShowGUI(List<Departure> departures){
        //Create and set up the window.
        JFrame frame = new JFrame("Vasttrafik Display Board :: Vårvädersgatan");
        frame.setBounds(150,150,900,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DisplayBoardFrame newContentPane = new DisplayBoardFrame(departures);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Timer
        Timer timer = new Timer(1000, new DisplayBoardFrame.TimerListener());
        timer.start();


        //Display the window.
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //
            System.out.println("I am alive :"+new Date().toString());
        }
    }

    /*
    Make Title of the table
     */
    private void setTitle(JPanel panelBorder){

        //JPanel panelBorder = new JPanel();
        /*panelBorder.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Vårvädersgatan",
                TitledBorder.CENTER, TitledBorder.TOP));
        panelBorder.setFont(new Font("Serif", Font.BOLD, 30));*/

        TitledBorder titled = new TitledBorder("Vårvädersgatan");
        titled.setTitleFont(new Font("Serif", Font.BOLD, 40));
        titled.setTitleColor(Color.DARK_GRAY);
        titled.setTitleJustification(TitledBorder.CENTER);
        panelBorder.setBorder(titled);


    }
}
