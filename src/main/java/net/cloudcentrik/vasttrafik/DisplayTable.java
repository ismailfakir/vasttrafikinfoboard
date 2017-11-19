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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DisplayTable extends JPanel {

    private JTable table;
    private String stopName;

    public DisplayTable(List<Departure> departures,String stopName){
        super(new BorderLayout(10,10));
        this.setLayout(new BorderLayout(10,20));
        this.stopName=stopName;

        TableModel tableModel = new DefaultTableModel(getRows(departures), getColumns());
        table = new JTable(tableModel){

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);


                //Color bgc=departures.get(row).getBgColor();

                //  Alternate row color
                if (!isRowSelected(row)){
                    c.setBackground(row % 2 == 0 ? getBackground() : Color.BLACK);
                    c.setForeground(row % 2 == 0 ? getForeground() : Color.WHITE);

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
        table.setRowMargin(0);

        //table.setShowGrid(false);

        table.setRowHeight(table.getRowHeight() + 30);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setIntercellSpacing(new Dimension(1,1));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);

        table.setPreferredScrollableViewportSize(new Dimension(1100, 500));
        table.setFillsViewportHeight(true);

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

    /*
    Make Title of the table
     */
    private void setTitle(JPanel panelBorder){

        TitledBorder titled = new TitledBorder(stopName);
        titled.setTitleFont(new Font("Serif", Font.BOLD, 40));
        titled.setTitleColor(Color.DARK_GRAY);
        titled.setTitleJustification(TitledBorder.CENTER);
        panelBorder.setBorder(titled);
    }

    public void updateTable(){

        System.out.println("Updating table");
        try {

            VasttrafikApiUtils.getDepartures(this.stopName, new VasttrafikApiUtils.CallbackInterface() {
                @Override
                public void onSuccess(java.util.List<Departure> departureList) {

                    TableModel model = new DefaultTableModel(getRows(departureList), getColumns());
                    table.setModel(model);
                    //table.updateUI();
                }

                @Override
                public void onFailed(Throwable error) {
                    //
                }
            });


        } catch (Exception e) {

            System.err.println(e.getMessage());

        } finally {

        }

    }

    private Object[][] getRows(List<Departure> departureList){
        if(departureList.size()<1){
            List<String[]> tempList = new ArrayList<String[]>();
            tempList.add(new String[] {"","","",""});
            tempList.add(new String[] {"Connecting to vasttrafik","loding derparture list","","Please wait...."});
            return tempList.toArray(new Object[][] {});
        }
        List<Departure> departures=sortByDestination(departureList);
        List<String[]> values = new ArrayList<String[]>();
        values.clear();
        for (int i = 0; i <departures.size(); i++) {
            boolean isAfter=VasttrafikUtils.isAfter(departures.get(i).getTime(),departures.get(i).getDate());
            if(isAfter){
                values.add(new String[] {departures.get(i).getName(),
                        departures.get(i).getTrack(),departures.get(i).getTime(),
                        departures.get(i).getDirection()});
            }

        }
        return values.toArray(new Object[][] {});

    }

    private Object[] getColumns(){
        List<String> columns = new ArrayList<String>();
        columns.clear();
        columns.add("Line");
        columns.add("Track");
        columns.add("Arival Time");
        columns.add("Destination");

        return columns.toArray();
    }

    private List<Departure> sortByDestination(List<Departure> departures){

        List<Departure> list=departures;

        Collections.sort(list, new Comparator<Departure>() {
            @Override
            public int compare(Departure d2, Departure d1)
            {

                return  d1.getDirection().compareTo(d2.getDirection());
            }
        });
        Collections.reverse(list);
        return list;

    }

}
