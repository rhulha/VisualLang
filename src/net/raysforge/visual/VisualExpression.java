package net.raysforge.visual;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.raysforge.visual.smartobjects.Types;

public class VisualExpression extends VisualInstruction {

	private static final long serialVersionUID = -773297158351751084L;

	private JComboBox<String> comboBoxForMethod;
	private JComboBox<String> comboBoxForVar;
	private JTextField textFieldTargetVar;
	private JCheckBox cbCast;

	private JTextField methodTextfield;

	public ParameterPanel paramPanel;

	public VisualExpression(Container myparent) {
		super(myparent, "Expression");
		setSize(160, 200);

		setBackground(Color.getHSBColor(0.9f, 0.3f, 0.9f));

		comboBoxForVar = new VariableComboBox(myparent);
		comboBoxForVar.addActionListener((e) -> refreshMethods(getVarType()));

		add(comboBoxForVar, getConstraints(gridy++, 0));

		comboBoxForMethod = new JComboBox<String>();
		comboBoxForMethod.setEditable(true);
		add(comboBoxForMethod, getConstraints(gridy++, 0));
		comboBoxForMethod.addItemListener((e) -> paramPanel.refreshParameterList(getVarType()));

		//comboBoxForMethod.addActionListener((e)-> refreshParameterList(getVarType()));

		methodTextfield = (JTextField) comboBoxForMethod.getEditor().getEditorComponent();
		methodTextfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						comboFilter();
					}
				});
			}
		});

		add(paramPanel = new ParameterPanel(this), getConstraints(gridy++, 0));

		add(new JSeparator(), getConstraints(gridy++, 0));

		cbCast = new JCheckBox("cast");
		add(cbCast, getConstraints(gridy++, 0));

		textFieldTargetVar = new JTextField();
		textFieldTargetVar.setToolTipText("targetVar");
		add(textFieldTargetVar, getConstraints(gridy++, 1));

		refreshMethods(getVarType());
	}

	public void comboFilter() {
		if (!comboBoxForMethod.isPopupVisible()) {
			comboBoxForMethod.showPopup();
		}
		String selectedVar = (String) comboBoxForVar.getSelectedItem();
		VisualVariable var = MyClass.searchVar(selectedVar);
		if (var == null) {
			return;
		}
		refreshMethods(var.getType());
	}

	public String getVarType() {
		VisualVariable v = MyClass.searchVar((String) comboBoxForVar.getSelectedItem());
		return v == null ? null : v.getType();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//refreshVariables();
	}

	public Map<String, Method> getMethods(String type) {
		Method[] ms = Types.typeMap.get(type).getDeclaredMethods();
		//return Arrays.stream(ms).collect(Collectors.toMap(Method::getName, Function.identity())); // gives: Duplicate key
		HashMap<String, Method> methods = new HashMap<String, Method>();
		for (Method method : ms) {
			methods.put(method.getName(), method);
		}
		return methods;
	}

	private void refreshMethods(String type) {
		if (type == null)
			return;
		String filter = methodTextfield.getText();

		DefaultComboBoxModel<String> dcbm = (DefaultComboBoxModel<String>) comboBoxForMethod.getModel();
		Object save = comboBoxForMethod.getSelectedItem();
		dcbm.removeAllElements();
		Method[] ms = Types.typeMap.get(type).getDeclaredMethods();
		Arrays.sort(ms, new MethodComparator());
		for (Method meth : ms) {
			if (meth.getName().toLowerCase().contains(filter.toLowerCase()))
				dcbm.addElement(meth.getName());
		}
		comboBoxForMethod.setSelectedItem(save);
		methodTextfield.setText(filter);
		//System.out.println("filter: " + filter);
	}

	public String getVariableName() {
		return (String) comboBoxForVar.getSelectedItem();
	}

	public void setVariableName(String v) {
		comboBoxForVar.setSelectedItem(v);
	}

	public String getMethodName() {
		return (String) comboBoxForMethod.getSelectedItem();
	}

	public void setMethodName(String name) {
		comboBoxForMethod.setSelectedItem(name);
	}

	public String[] getParameter() {
		int rc = paramPanel.getComponentCount();
		String[] params = new String[rc];
		for (int i = 0; i < rc; i++) {
			params[i] = (String) ((VariableComboBox)paramPanel.getComponent(i)).getSelectedItem();
		}
		return params;
	}

	public String getCommaSeparatedParameter() {
		return String.join(",", getParameter());
	}

	@Override
	public String toString() {
		String name = getVariableName();
		System.out.println("name " + name);
		return name;
	}

	@Override
	public String serialize() {
		return super.serialize() + ";cast:" + cbCast.isSelected() + ";method:" + getMethodName() + ";parameter:" + getCommaSeparatedParameter() + ";target:" + textFieldTargetVar.getText();
	}

	@Override
	public void focus() {
		comboBoxForVar.getEditor().getEditorComponent().requestFocus();
	}

	@SuppressWarnings("unchecked")
	public void setCommaSeparatedParameter(String string) {
		String[] split = string.split(",");
		for (int i = 0; i < split.length; i++) {
			((JComboBox<String>)paramPanel.getComponent(i)).setSelectedItem(split[i]);

		}
		
	}

}
