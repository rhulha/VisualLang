package net.raysforge.visual.demo.chess;

public class Move {
	public final int[] move;
	private ChessBoard cb;
	private int x;
	private int y;

	public Move(ChessBoard cb, int x, int y, int[] move) {
		this.cb = cb;
		this.x = x;
		this.y = y;
		this.move = move;
	}

	public void doMove() {
		ChessTile from = cb.getCell(x, y);
		ChessTile to = cb.getCell(x+move[0], y+move[1]);
		cb.move(from, to);
	}

	public void undoMove() {
		ChessTile from = cb.getCell(x+move[0], y+move[1]);
		ChessTile to = cb.getCell(x, y);
		cb.move(from, to);
	}
}
