package net.raysforge.visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class VisualInstruction extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 5606332023569519122L;
	
	protected final Container myparent;
	
	int x;
	int y;
	int off_x;
	int off_y;

	int gridy;
	
	public VisualInstruction(Container myparent, String name) {
		this.myparent = myparent;
		setSize(100, 100);
		
		setLayout(new GridBagLayout());

		setBackground(Color.gray);
		
		add(new JLabel(name), getConstraints(gridy++, 0));

		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public static GridBagConstraints getConstraints(int gridy, double weighty) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.anchor = GridBagConstraints.NORTH;
		return gridBagConstraints;
	}

	List<Point> snapPositions = null;
	
	@Override
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
		off_x = e.getX();
		off_y = e.getY();
		x = c.getX();
		y = c.getY();
		snapPositions = MyClass.getSnapPositions(myparent, this);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private final static int snap = 10;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Component c = e.getComponent();
		x += e.getX() - off_x;
		y += e.getY() - off_y;
		
		for (Point p : snapPositions) {
			if( (x <= p.x+snap) && (x >= p.x-snap) && (y <= p.y+snap) && (y >= p.y-snap) )
			{
				x = p.x;
				y = p.y;
				break;
			}
		}
		
		c.setLocation(x, y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(this.getClass().getSimpleName(), 7, 12);
	}
	*/

	
	public String serialize()
	{
		return "type:"+getClass().getSimpleName()+";x:"+getX()+";y:"+getY()+";name:"+toString();
	}

	public abstract void focus();

}
