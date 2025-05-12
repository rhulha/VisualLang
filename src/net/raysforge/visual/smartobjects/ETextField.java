package net.raysforge.visual.smartobjects;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.Caret;
import javax.swing.text.Document;

public class ETextField {
	
	JTextField tf = new JTextField();

	public Dimension getPreferredSize() {
		return tf.getPreferredSize();
	}

	public void addActionListener(ActionListener l) {
		tf.addActionListener(l);
	}

	public Document getDocument() {
		return tf.getDocument();
	}

	public void setComponentOrientation(ComponentOrientation o) {
		tf.setComponentOrientation(o);
	}

	public void setActionCommand(String command) {
		tf.setActionCommand(command);
	}

	public void setInheritsPopupMenu(boolean value) {
		tf.setInheritsPopupMenu(value);
	}

	public void setComponentPopupMenu(JPopupMenu popup) {
		tf.setComponentPopupMenu(popup);
	}

	public Caret getCaret() {
		return tf.getCaret();
	}

	public void setCaret(Caret c) {
		tf.setCaret(c);
	}

	public Container getParent() {
		return tf.getParent();
	}

	public boolean isEnabled() {
		return tf.isEnabled();
	}
	
	public String getText() {
		return tf.getText();
	}
	
	public void setText(String s) {
		tf.setText(s);
	}

	public void setPreferredSize(Dimension d) {
		tf.setPreferredSize(d);
	}

	public Component getJTextField() {
		return tf;
	}

}
