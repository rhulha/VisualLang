package net.raysforge.visual;

import java.awt.Component;
import java.awt.Container;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class VariableComboBox extends JComboBox<String> implements PopupMenuListener {

	private Container myparent;
	private DefaultComboBoxModel<String> dcbm;

	public VariableComboBox(Container myparent) {
		this.myparent = myparent;
		setEditable(true);
		addPopupMenuListener(this);

		dcbm = (DefaultComboBoxModel<String>) getModel();
		addAllVars(myparent);
	}

	private void addAllVars(Container myparent) {
		for (Component component : myparent.getComponents()) {
			if (component instanceof VisualVariable) {
				VisualVariable v = (VisualVariable) component;
				dcbm.addElement(v.getVariableName()); // todo add real var ref so we can easy find all methods
			}
		}
	}

	private void refreshVariables() {
		Object save = getSelectedItem();
		removeAllItems();
		addAllVars(myparent);
		setSelectedItem(save);
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		if (e == null || e.getSource() == this) {
			refreshVariables();
		}
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
	}

}
