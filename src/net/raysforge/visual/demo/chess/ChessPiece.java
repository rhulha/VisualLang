package net.raysforge.visual.demo.chess;

import java.util.HashMap;

public class ChessPiece {

	public final String icon;
	public final int worth;
	public final int[][] moves;
	public final boolean continuous;
	
	public final static HashMap<String, ChessPiece> pieces = new HashMap<>();

	public ChessPiece(String icon, boolean white, int worth, int[][] moves, boolean continuous) {
		this.icon = icon;
		this.worth = worth;
		this.moves = moves;
		this.continuous = continuous;
		pieces.put(icon, this);
	}
	
	static int[][] king_queen = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
	static int[][] rook = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
	static int[][] knight = new int[][]{{1,2},{-1,2},{1,0},{-1,0}};
	static int[][] bishop = new int[][]{{1,1},{-1,-1},{-1,1},{1,-1}};

	static ChessPiece white_king = new ChessPiece("\u2654", true, 100, king_queen, false); //	U+2654	&#9812; "♔"
	static ChessPiece white_queen = new ChessPiece("♕", true, 80, king_queen, true); //	U+2655	&#9813;
	static ChessPiece white_rook = new ChessPiece("♖", true, 60, rook, true); //	U+2656	&#9814;
	static ChessPiece white_knight = new ChessPiece("♘", true, 40, knight, false); // 	U+2658	&#9816;
	static ChessPiece white_bishop = new ChessPiece("♗", true, 39, bishop, true); // 	U+2657	&#9815;
	static ChessPiece white_pawn = new ChessPiece("♙", true, 10, new int[][]{{0,1},{0,2}}, false); //	U+2659	&#9817;
	static ChessPiece black_king = new ChessPiece("♚", false, -100, king_queen, false); //	U+265A	&#9818;
	static ChessPiece black_queen = new ChessPiece("♛", false, -80, king_queen, true); //	U+265B	&#9819;
	static ChessPiece black_rook = new ChessPiece("♜", false, -60, rook, true); //	U+265C	&#9820;
	static ChessPiece black_knight = new ChessPiece("♞", false, -40, knight, false); // 	U+265E	&#9822;
	static ChessPiece black_bishop = new ChessPiece("♝", false, -39, bishop, true); // 	U+265D	&#9821;
	static ChessPiece black_pawn = new ChessPiece("♟", false, -10, new int[][]{{0,-1},{0,-2}}, false); // 	U+265F	&#9823

}
