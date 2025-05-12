package net.raysforge.visual.demo;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import net.raysforge.visual.smartobjects.EFrame;
import net.raysforge.visual.smartobjects.ETextField;

public class DemoApp2 {

	public static void main(String[] args) {
		
		EFrame f = new EFrame();
		f.setDefaultCloseOperation(3);
		f.setSize(190, 230);
		
		ETextField tf = new ETextField();
		
		JTable t = new JTable();
		//t.setShowGrid(false);
	    t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    t.setTableHeader(null);
		DefaultTableModel dtm = (DefaultTableModel) t.getModel();
		dtm.setColumnCount(1);
		//dtm.setColumnIdentifiers((Object[]) null);
	    for (int i = 0; i < 10; i++) {
			dtm.setRowCount(i+1);
			dtm.setValueAt("asdasdasds"+i, i, 0);
		}
		JScrollPane sp = new JScrollPane(t);
		//sp.setColumnHeader(null);
		
		
		Dimension d = new Dimension();
		d.setSize(150, 20);
		tf.setPreferredSize(d);
		f.addToContentPane(tf);
		f.getContentPane().add(sp);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}
