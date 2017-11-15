/*
package net.cloudcentrik.vasttrafik;

import javax.management.monitor.Monitor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class UpdateWorker extends SwingWorker<TableModel, Void> {

    private Monitor monitor;
    private Timer updateTimer;

    public UpdateWorker(Monitor monitor, Timer updateTimer) {
        this.monitor = monitor;
        this.updateTimer = updateTimer;
    }

    @Override
    protected TableModel doInBackground() throws Exception {
        Vector<Vector> rowData = dbmonitor.getJobsData();
        Vector columnNames = dbmonitor.getColumnNames();

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        return model;
    }

    @Override
    protected void done() {
        try {
            TableModel model = get();
            monitor.updateTable(model);
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        updateTimer.restart();
    }
}*/
