package net.raysforge.visual.demo;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class LightsOut extends JToggleButton implements ActionListener {
	
	// TODO write a ButtonGrid component

	final static LightsOut[][] buttons = new LightsOut[5][5];
	// @formatter:off
	final static int pattern[][] = {
			{1,0,0,1,1},
			{0,0,1,0,1},
			{0,1,0,1,0},
			{1,0,1,0,0},
			{1,1,0,0,1},
			};
	// @formatter:on
	int x, y;

	public LightsOut(int x, int y) {
		this.x = x;
		this.y = y;
		addActionListener(this);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("LightsOut");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(640, 640);

		Container container = frame.getContentPane();
		container.setLayout(new GridLayout(5, 5));
		
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				container.add(buttons[x][y] = new LightsOut(x, y));
				buttons[x][y].setSelected(pattern[x][y] == 0);
			}
		}

		Random rnd = new Random();
		boolean rand = rnd.nextBoolean();
		if( rand == true) {
			for(int i=0;i<100;i++) {
				buttons[rnd.nextInt(5)][rnd.nextInt(5)].toggle();
			}
		}

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void toggle() {
		setSelected(!isSelected());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (x > 0)
			buttons[x - 1][y].toggle();
		if (x < 4)
			buttons[x + 1][y].toggle();
		if (y > 0)
			buttons[x][y - 1].toggle();
		if (y < 4)
			buttons[x][y + 1].toggle();
		
		if (checkWin()==true)
			JOptionPane.showMessageDialog(this, "You Win!");
	}

	private boolean checkWin() {
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				if (!buttons[x][y].isSelected())
					return false;
			}
		}
		return true;
	}

}
