package net.raysforge.visual.demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableAndGBC {

    private String[] columnNames = {"Source", "Hit", "Last", "Ur_Diff"};
    private JTable table;
    private Object[][] data = {{"Swing Timer", 2.99, 5, 1.01},
        {"Swing Worker", 7.10, 5, 1.010}, {"TableModelListener", 25.05, 5, 1.01}};
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);

    public JTableAndGBC() {
        JPanel panel = new JPanel(new GridBagLayout());
        table = new JTable(model);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JScrollPane pane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setTableHeader(null);
        panel.add(pane, gbc);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JTableAndGBC();
            }
        });
    }
}