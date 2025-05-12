package net.raysforge.visual.demo.chess;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JLabel;

import net.raysforge.easyswing.EasySwing;
import net.raysforge.easyswing.eventpanel.EventPanel;

public class ChessBoard {

	List<ChessTile> cells = Stream.generate(() -> new ChessTile(this)).limit(64).collect(Collectors.toList());
	ChessTile selected;
	JLabel statusLabel;

	public ChessBoard() {
		EasySwing es = new EasySwing("Chess", 840, 840);
		EventPanel ep = es.setEventPanelAsMainContent();

		statusLabel = es.getStatusLabel();

		ep.setLayout(new GridLayout(8, 8));

		for (int i = 0; i < cells.size(); i++) {
			ChessTile cell = cells.get(i);

			ep.add(cell);
			if ((i / 8 + i % 8) % 2 == 1)
				cell.setBackground(Color.GRAY);

			if (i / 8 == 1)
				cell.setText(ChessPiece.black_pawn.icon);
			if (i / 8 == 6)
				cell.setText(ChessPiece.white_pawn.icon);
		}

		cells.get(0).setText(ChessPiece.black_rook.icon);
		cells.get(1).setText(ChessPiece.black_knight.icon);
		cells.get(2).setText(ChessPiece.black_bishop.icon);
		cells.get(3).setText(ChessPiece.black_queen.icon);
		cells.get(4).setText(ChessPiece.black_king.icon);
		cells.get(5).setText(ChessPiece.black_bishop.icon);
		cells.get(6).setText(ChessPiece.black_knight.icon);
		cells.get(7).setText(ChessPiece.black_rook.icon);

		cells.get(56).setText(ChessPiece.white_rook.icon);
		cells.get(57).setText(ChessPiece.white_knight.icon);
		cells.get(58).setText(ChessPiece.white_bishop.icon);
		cells.get(59).setText(ChessPiece.white_queen.icon);
		cells.get(60).setText(ChessPiece.white_king.icon);
		cells.get(61).setText(ChessPiece.white_bishop.icon);
		cells.get(62).setText(ChessPiece.white_knight.icon);
		cells.get(63).setText(ChessPiece.white_rook.icon);

		es.show();
	}

	int evaluate() {
		int score = 0;
		for (int i = 0; i < cells.size(); i++) {
			ChessTile cell = cells.get(i);

			ChessPiece chessPiece = ChessPiece.pieces.get(cell.getText());
			if (chessPiece != null)
				score += chessPiece.worth;
		}
		return score;
	}

	Result negamax(int depth, int player) {
		Result r = new Result();
		if (gameOver() || depth == 3) {
			r.bestScore = player * evaluate();
		} else {
			getMoves().forEach(m -> {
				m.doMove();
				int currentScore = -negamax(depth+1,-player).bestScore;
				if (currentScore > r.bestScore) {
					r.bestScore = currentScore;
					r.bestMove = m;
				}
				m.undoMove();
			});
		}
		return r;
	}

	ChessTile getCell(int x, int y) {
		return cells.get(y * 8 + x);
	}

	void move(ChessTile from, ChessTile to) {
		to.setText(from.getText());
		from.setText("");
	}

	private Iterable<Move> getMoves() {
		List<Move> moves = new ArrayList<>();

		for (int i = 0; i < cells.size(); i++) {
			ChessTile cell = cells.get(i);
			if( cell.getText().length() == 0)
				continue;
			ChessPiece cp = ChessPiece.pieces.get(cell.getText());

			int x = i % 8;
			int y = i / 8;
			for (int j = 0; j < cp.moves.length; j++) {
				int[] move = cp.moves[j];
				try {
					if (getCell(x + move[0], y + move[1]).getText().length() == 0)
						moves.add(new Move(this, x, y, move));
				} catch (IndexOutOfBoundsException e) {
				}
			}
		}

		return moves;
	}

	private boolean gameOver() {
		return false;
	}

	public static void main(String[] args) {
		new ChessBoard();
	}
}
