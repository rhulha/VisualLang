package net.raysforge.visual;

import java.awt.Color;
import java.awt.Container;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import net.raysforge.visual.smartobjects.Types;

public class VisualVariable extends VisualInstruction {

	private static final long serialVersionUID = -773297158351751084L;

	private JTextField tfName;
	private JComboBox<String> comboBoxForType;

	private JCheckBox cbInit;
	private JCheckBox cbArray;
	
	//private JCheckBox cbProtected;

	public VisualVariable(Container myparent) {
		super(myparent, "Variable");
		setSize(160, 200);
		
		setBackground(Color.getHSBColor(0.6f, 0.3f, 0.9f));

		tfName = new JTextField();
		//tf.setPreferredSize(new Dimension(100, 40));
		add(tfName, getConstraints(gridy++, 0));

		comboBoxForType = new JComboBox<String>();
		comboBoxForType.setEditable(true);
		DefaultComboBoxModel<String> dcbm = (DefaultComboBoxModel<String>) comboBoxForType.getModel();
		for (Entry<String, Class<?>> entry : Types.typeMap.entrySet()) {
			dcbm.addElement(entry.getKey());
		}
		add(comboBoxForType, getConstraints(gridy++, 0));

		cbArray = new JCheckBox("Array");
		add(cbArray, getConstraints(gridy++, 0));

		cbInit = new JCheckBox("Initialize");
		add(cbInit, getConstraints(gridy++, 1));


	}

	public void focus() {
		tfName.requestFocus();
	}

	@Override
	public String toString() {
		return tfName.getText();
	}

	public String getVariableName() {
		return tfName.getText();
	}

	public void setVariableName(String s) {
		tfName.setText(s);
	}

	public String getType() {
		return (String) comboBoxForType.getSelectedItem();
	}

	public void setType(String t) {
		comboBoxForType.setSelectedItem(t);
	}

	@Override
	public String serialize()
	{
		return super.serialize()+";class:"+getType()+";init:"+cbInit.isSelected();
	}

	public String getRealType() {
		return Types.typeMap.get(getType()).getSimpleName();
	}
}
