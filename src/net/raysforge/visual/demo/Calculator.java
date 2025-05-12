package net.raysforge.visual.demo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calculator implements ActionListener {

	private JTextField tf;
	
	public Calculator() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(new FlowLayout());
		f.setSize(190, 230);

		tf = new JTextField();
		tf.setPreferredSize(new Dimension(150, 20));
		f.getContentPane().add(tf);

		String[] buttons = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "-", "x", ":", "=" };
		for (String text : buttons) {
			JButton b = new JButton(text);
			b.addActionListener(this);
			f.getContentPane().add(b);
		}
		f.setLocationRelativeTo(null);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		new Calculator();
	}

	int v1;
	int v2;
	char op;

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		String text = ((JButton) e.getSource()).getText();
		char c = text.charAt(0);

		if (c >= '0' && c <= '9') {
			tf.setText(tf.getText()+text);
		} else if (c != '=') {
			v1 = Integer.parseInt(tf.getText());
			op = c;
			tf.setText("");
		} else {
			v2 = Integer.parseInt(tf.getText());

			switch (op) {
			case '+':
				tf.setText("" + (v1 + v2));
				break;
			case '-':
				tf.setText("" + (v1 - v2));
				break;
			case 'x':
				tf.setText("" + (v1 * v2));
				break;
			case ':':
				tf.setText("" + (v1 / v2));
				break;

			default:
				break;
			}
			op=0;
		}
		//System.out.println("sdf");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
