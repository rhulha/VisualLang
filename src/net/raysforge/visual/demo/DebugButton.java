package net.raysforge.visual.demo;

import java.awt.Graphics;

import javax.swing.JButton;

public class DebugButton extends JButton {
	
	public String debugString;
	
	/*
//		cell(0,1).set("O", Color.RED, COMPUTER);

 		es.addToolBarItem("Board Score", "", e -> cells.forEach(c -> {
 			int evaluate = evaluate();
			statusLabel.setText("board score: "+evaluate);
		}));

 		es.addToolBarItem("ai", "ai", e -> cells.forEach(c -> {
			negamax(COMPUTER);
			ep.repaint();
		}));


		if (level == 0) {
			p.score = currentScore;
		}

	 */

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(getParent().getFont());
		g.drawString(debugString, getWidth()-25, getHeight()-10);
		
	}

}
