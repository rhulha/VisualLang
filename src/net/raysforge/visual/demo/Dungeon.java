package net.raysforge.visual.demo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Dungeon extends JToggleButton implements ActionListener {

	static int mx = 5;
	static int my = 7;
	final static Dungeon[][] cells = new Dungeon[mx][my];
	static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 22);
	static Random rnd = new Random();

	int x, y, life = 0, attack = 0;

	static int heroLife = 100;
	static int heroAttack = 6;
	private static JLabel heroLabel;

	public Dungeon(int x, int y) {
		this.x = x;
		this.y = y;
		addActionListener(this);
		setFont(font);

		if (rnd.nextDouble() > 0.5) {
			life = rnd.nextInt(17) + 1;
			attack = rnd.nextInt(7) + 1;
		}

	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Dungeon");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(480, 640);

		JPanel dungeon = new JPanel();
		dungeon.setLayout(new GridLayout(my, mx));

		JPanel hero = new JPanel();
		heroLabel = new JLabel("Hero Attack 6   Life 100");
		hero.add(heroLabel);

		f.getContentPane().add(dungeon, BorderLayout.CENTER);
		f.getContentPane().add(hero, BorderLayout.SOUTH);

		for (int y = 0; y < my; y++) {
			for (int x = 0; x < mx; x++) {
				dungeon.add(cells[x][y] = new Dungeon(x, y));
			}
		}

		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setSelected(true); // disable deselecting

		if (life == 0)
			return;
		heroLife -= attack;
		life -= heroAttack;

		heroLabel.setText("Hero Attack 6   Life " + heroLife);
		if (checkWin())
			JOptionPane.showMessageDialog(null, "You Win!");

	}

	private boolean checkWin() {
		for (int x = 0; x < mx; x++) {
			for (int y = 0; y < my; y++) {
				if (cells[x][y].life > 0)
					return false;
			}
		}
		return true;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (isSelected() && life > 0)
			g.drawString(attack + " " + life, 25, getHeight() - 10);

	}
}
