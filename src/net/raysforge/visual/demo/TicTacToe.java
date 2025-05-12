package net.raysforge.visual.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JLabel;

import net.raysforge.easyswing.EasySwing;
import net.raysforge.easyswing.eventpanel.EventPanel;

// optimized version of www3.ntu.edu.sg/home/ehchua/programming/java/javagame_tictactoe_ai.html

public class TicTacToe extends JButton implements ActionListener {

	final static Font font = new Font("Comic Sans MS", Font.BOLD, 100);
	final static List<TicTacToe> cells = Stream.generate(() -> new TicTacToe()).limit(9).collect(Collectors.toList());
	final static ArrayList<TicTacToe[]> lines = new ArrayList<TicTacToe[]>();
	final static int EMPTY = 0;
	final static int COMPUTER = 1;
	final static int HUMAN = -1;
	static JLabel statusLabel;

	public int content;

	public TicTacToe() {
		addActionListener(this);
		setFocusPainted(false);
		setFont(font);
	}

	public static void main(String[] args) {
		// 8 lines (3 rows, 3 columns, 2 diagonals) 
		for (int i = 0; i < 3; i++) {
			lines.add(new TicTacToe[]{cells.get(0+i*3), cells.get(1+i*3), cells.get(2+i*3)}); // rows
			lines.add(new TicTacToe[]{cells.get(i+0*3), cells.get(i+1*3), cells.get(i+2*3)}); // cols
		}
		lines.add(new TicTacToe[]{cells.get(0+0*3), cells.get(1+1*3), cells.get(2+2*3)}); // diagonal
		lines.add(new TicTacToe[]{cells.get(2+0*3), cells.get(1+1*3), cells.get(0+2*3)}); // diagonal

		EasySwing es = new EasySwing("TicTacToe", 640, 640);
		EventPanel ep = es.setEventPanelAsMainContent();

		es.addToolBarItem("New Game", "", e -> cells.forEach(c -> c.set("", Color.RED, EMPTY)));
		es.addToolBarItem("Move AI", "", e -> {
			Result r = negamax(COMPUTER);
			if (r.bestCell != null)
				r.bestCell.set("O", Color.RED, COMPUTER);
		});
		
		statusLabel = es.getStatusLabel();
		
		cells.forEach(c -> ep.add(c));
		ep.setLayout(new GridLayout(3, 3));
		es.show();
	}

	private void set(String chr, Color c, int player) {
		setText(chr);
		setForeground(c);
		content = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getText().length() > 0)
			return;
		set("X", Color.GREEN, HUMAN);
		Result r = negamax(COMPUTER);
		if (r.bestCell != null) {
			r.bestCell.set("O", Color.RED, COMPUTER);
		}
		statusLabel.setText("board score: "+evaluate());
	}

	static boolean gameOver() {
		return cells.stream().filter(c -> c.content == EMPTY).count() == 0 || Math.abs(evaluate()) >= 50;
	}

	static class Result {
		int bestScore = Integer.MIN_VALUE;
		TicTacToe bestCell;
	}

	static Result negamax(int player) {
		Result r = new Result();
		if (gameOver()) {
			r.bestScore = player * evaluate(); // multiplying evaluate() with player makes bestScore reflect the value from the perspective of the current player
		} else {
			cells.stream().filter(c -> c.content == EMPTY).forEach(p -> {
				p.content = player; // Try this move for the current "player"
				int currentScore = -negamax(-player).bestScore;
				if (currentScore > r.bestScore) {
					r.bestScore = currentScore;
					r.bestCell = p;
				}
				p.content = EMPTY; // Undo move
			});
		}
		return r;
	}

	public static int evaluate() {
		return (int) (lines.stream().mapToInt(line -> evalCell(line[0], evalCell(line[1], line[2].content))).sum() +
		cells.stream().filter(c -> c.content == EMPTY).count()); // give a higher score for earlier solutions
	}

	/**
	 * The heuristic evaluation function for the given cell plus previous score
	 * @Return +100, +10, +1 for 3, 2, 1-in-a-line for computer.
	 *         -100, -10, -1 for 3, 2, 1-in-a-line for human.
	 *         0 otherwise 
	 *         http://www.milefoot.com/math/planecurves/expfcns.htm
	 *         http://www.wolframalpha.com/input/?i=f(x)=floor(10^x)
	 */
	public static int evalCell(TicTacToe cell, int score) {
		return cell.content * (int) Math.min( Math.pow(10, cell.content*score), 100);
	}

}
