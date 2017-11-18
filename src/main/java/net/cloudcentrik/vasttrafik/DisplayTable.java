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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayTable extends JPanel {

    private JTable table;

    public DisplayTable(List<Departure> departures){
        super(new BorderLayout(10,10));
        this.setLayout(new BorderLayout(10,20));

        TableModel tableModel = new DefaultTableModel(getRows(departures), getColumns());
        table = new JTable(tableModel){

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);

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

        table.setRowHeight(table.getRowHeight() + 30);
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setIntercellSpacing(new Dimension(1,1));

        table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
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

        TitledBorder titled = new TitledBorder("Vårvädersgatan");
        titled.setTitleFont(new Font("Serif", Font.BOLD, 40));
        titled.setTitleColor(Color.DARK_GRAY);
        titled.setTitleJustification(TitledBorder.CENTER);
        panelBorder.setBorder(titled);
    }

    public void updateTable(){

        System.out.println("Updating table");
        try {

            VasttrafikApiUtils.getDepartures("Vårväderstorget", new VasttrafikApiUtils.CallbackInterface() {
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

    private Object[][] getRows(List<Departure> departures){
        List<String[]> values = new ArrayList<String[]>();
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
        columns.add("Line");
        columns.add("Track");
        columns.add("Arival Time");
        columns.add("Destination");

        return columns.toArray();
    }

}
