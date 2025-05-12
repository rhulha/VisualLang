package net.raysforge.visual;

import java.awt.Color;
import java.awt.Container;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import net.raysforge.visual.smartobjects.Types;

public class VisualMethod extends VisualInstruction {

	private static final long serialVersionUID = -773297158351751084L;

	private JTextField tfName;
	private JComboBox<String> comboBoxForType;

	public VisualMethod(Container myparent) {
		super(myparent, "Method");

		setBackground(Color.getHSBColor(0.3f, 0.3f, 0.9f));

		tfName = new JTextField();
		//tf.setPreferredSize(new Dimension(100, 40));
		add(tfName, getConstraints(gridy++, 0));

		comboBoxForType = new JComboBox<String>();
		DefaultComboBoxModel<String> dcbm = (DefaultComboBoxModel<String>) comboBoxForType.getModel();
		for (Class<?> type : Types.types) {
			dcbm.addElement(type.getSimpleName());
		}
		add(comboBoxForType, getConstraints(gridy++, 1));
	}

	public void focus() {
		tfName.requestFocus();
	}

	@Override
	public String toString() {
		return tfName.getText();
	}

	public String getMethodName() {
		return tfName.getText();
	}

	public void setMethodName(String s) {
		tfName.setText(s);
	}

	public String getType() {
		return (String) comboBoxForType.getSelectedItem();
	}

	public void setType(String t) {
		comboBoxForType.setSelectedItem(t);
	}

	public String serialize()
	{
		return super.serialize()+";class:"+getType();
	}
}
