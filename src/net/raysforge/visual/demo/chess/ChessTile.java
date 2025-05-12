package net.raysforge.visual.demo.chess;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

public class ChessTile extends JToggleButton implements ActionListener {

	static Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 62);
	private ChessBoard chessBoard;

	public ChessTile(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
		setFont(font);
		setFocusPainted(false);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		if (chessBoard.selected == null) {
			if( getText().length() > 0)
				chessBoard.selected = this;
		} else {
			chessBoard.move(chessBoard.selected, this);
			chessBoard.selected.setSelected(false);
			this.setSelected(false);
			chessBoard.selected = null;
			Result r = chessBoard.negamax(0, 0);
			if (r.bestMove != null) {
				r.bestMove.doMove();
				chessBoard.statusLabel.setText("board score: "+chessBoard.evaluate());
			}
		}


	}

}
