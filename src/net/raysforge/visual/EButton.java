package net.raysforge.visual;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;


public class EButton extends JButton implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -791333459801966233L;

	public EButton() {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	int x;
	int y;
	int off_x;
	int off_y;

	@Override
	public void mousePressed(MouseEvent e) {
		
		Component c = e.getComponent();
		off_x = e.getX();
		off_y = e.getY();
		x = c.getX();
		y = c.getY();		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
