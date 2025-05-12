package net.raysforge.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.lang.reflect.Method;

import javax.swing.JPanel;

public class ParameterPanel extends JPanel {
	
	private VisualExpression ve;

	public ParameterPanel(VisualExpression ve) {
		this.ve = ve;
		setBackground(Color.BLUE);
		setLayout(new GridBagLayout());
	}
	
	public void refreshParameterList(String type) {
		String selectedMethod = ve.getMethodName();
		System.out.println("refreshParameterList: " + selectedMethod);
		removeAll();
		if (selectedMethod != null) {
			Method method = ve.getMethods(type).get(selectedMethod);
			int length = method.getParameterTypes().length;
			setMinimumSize(new Dimension(66, 22*length));
			for (int i = 0; i < length; i++) {
				VariableComboBox comboBox = new VariableComboBox(ve.myparent);
				add(comboBox,  VisualInstruction.getConstraints(i, i==length-1 ? 1 : 0));
			}
			System.out.println(selectedMethod + " " + length);
		}
		ve.validate();
	}


}
