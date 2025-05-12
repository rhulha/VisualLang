package net.raysforge.visual.smartobjects;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyListener;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;

public class EFrame {
	
	JFrame frame = new JFrame();

	public int getComponentCount() {
		return frame.getComponentCount();
	}

	public Component getComponent(int n) {
		return frame.getComponent(n);
	}

	public Component[] getComponents() {
		return frame.getComponents();
	}

	public void setDefaultCloseOperation(int operation) {
		frame.setDefaultCloseOperation(operation);
	}

	public Insets getInsets() {
		return frame.getInsets();
	}

	public String getTitle() {
		return frame.getTitle();
	}

	public void setTitle(String title) {
		frame.setTitle(title);
	}

	public Image getIconImage() {
		return frame.getIconImage();
	}

	public void setJMenuBar(JMenuBar menubar) {
		frame.setJMenuBar(menubar);
	}

	public JMenuBar getJMenuBar() {
		return frame.getJMenuBar();
	}

	public boolean isResizable() {
		return frame.isResizable();
	}

	public void setResizable(boolean resizable) {
		frame.setResizable(resizable);
	}

	public JRootPane getRootPane() {
		return frame.getRootPane();
	}

	public void setIconImage(Image image) {
		frame.setIconImage(image);
	}

	public Container getContentPane() {
		return frame.getContentPane();
	}

	public int getState() {
		return frame.getState();
	}

	public void setContentPane(Container contentPane) {
		frame.setContentPane(contentPane);
	}

	public Component getGlassPane() {
		return frame.getGlassPane();
	}

	public void pack() {
		frame.pack();
	}

	public void setGlassPane(Component glassPane) {
		frame.setGlassPane(glassPane);
	}

	public void setUndecorated(boolean undecorated) {
		frame.setUndecorated(undecorated);
	}

	public void setSize(int width, int height) {
		frame.setSize(width, height);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public boolean isValid() {
		return frame.isValid();
	}

	public boolean isVisible() {
		return frame.isVisible();
	}

	public void setEnabled(boolean b) {
		frame.setEnabled(b);
	}

	public void doLayout() {
		frame.doLayout();
	}

	public void addWindowListener(WindowListener l) {
		frame.addWindowListener(l);
	}

	public Color getForeground() {
		return frame.getForeground();
	}

	public void invalidate() {
		frame.invalidate();
	}

	public void addWindowStateListener(WindowStateListener l) {
		frame.addWindowStateListener(l);
	}

	public void setForeground(Color c) {
		frame.setForeground(c);
	}

	public void validate() {
		frame.validate();
	}

	public Color getBackground() {
		return frame.getBackground();
	}

	public void setBackground(Color c) {
		frame.setBackground(c);
	}

	public Font getFont() {
		return frame.getFont();
	}

	public Dimension getMinimumSize() {
		return frame.getMinimumSize();
	}

	public Dimension getMaximumSize() {
		return frame.getMaximumSize();
	}

	public Point getLocation() {
		return frame.getLocation();
	}

	public float getAlignmentX() {
		return frame.getAlignmentX();
	}

	public Point getLocationOnScreen() {
		return frame.getLocationOnScreen();
	}

	public float getAlignmentY() {
		return frame.getAlignmentY();
	}

	public void setLocation(int x, int y) {
		frame.setLocation(x, y);
	}

	public void setLocation(Point p) {
		frame.setLocation(p);
	}

	public Dimension getSize() {
		return frame.getSize();
	}

	public final void setAlwaysOnTop(boolean alwaysOnTop) throws SecurityException {
		frame.setAlwaysOnTop(alwaysOnTop);
	}

	public Rectangle getBounds() {
		return frame.getBounds();
	}

	public void addContainerListener(ContainerListener l) {
		frame.addContainerListener(l);
	}

	public boolean isAlwaysOnTopSupported() {
		return frame.isAlwaysOnTopSupported();
	}

	public final boolean isAlwaysOnTop() {
		return frame.isAlwaysOnTop();
	}

	public boolean isActive() {
		return frame.isActive();
	}

	public boolean isFocused() {
		return frame.isFocused();
	}

	public int getX() {
		return frame.getX();
	}

	public int getY() {
		return frame.getY();
	}

	public int getWidth() {
		return frame.getWidth();
	}

	public int getHeight() {
		return frame.getHeight();
	}

	public Rectangle getBounds(Rectangle rv) {
		return frame.getBounds(rv);
	}

	public Dimension getSize(Dimension rv) {
		return frame.getSize(rv);
	}

	public Point getLocation(Point rv) {
		return frame.getLocation(rv);
	}

	public void setMaximumSize(Dimension maximumSize) {
		frame.setMaximumSize(maximumSize);
	}

	public Component getComponentAt(int x, int y) {
		return frame.getComponentAt(x, y);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		frame.addPropertyChangeListener(propertyName, listener);
	}

	public Component getComponentAt(Point p) {
		return frame.getComponentAt(p);
	}

	public Point getMousePosition(boolean allowChildren) throws HeadlessException {
		return frame.getMousePosition(allowChildren);
	}

	public boolean isShowing() {
		return frame.isShowing();
	}

	public Component findComponentAt(int x, int y) {
		return frame.findComponentAt(x, y);
	}

	public void setLocationRelativeTo(Component c) {
		frame.setLocationRelativeTo(c);
	}

	public void setLocationByPlatform(boolean locationByPlatform) {
		frame.setLocationByPlatform(locationByPlatform);
	}

	public void setBounds(int x, int y, int width, int height) {
		frame.setBounds(x, y, width, height);
	}

	public boolean contains(int x, int y) {
		return frame.contains(x, y);
	}

	public boolean contains(Point p) {
		return frame.contains(p);
	}

	public void addComponentListener(ComponentListener l) {
		frame.addComponentListener(l);
	}

	public void addFocusListener(FocusListener l) {
		frame.addFocusListener(l);
	}

	public void addHierarchyListener(HierarchyListener l) {
		frame.addHierarchyListener(l);
	}

	public void addHierarchyBoundsListener(HierarchyBoundsListener l) {
		frame.addHierarchyBoundsListener(l);
	}

	public void addKeyListener(KeyListener l) {
		frame.addKeyListener(l);
	}

	public void addMouseListener(MouseListener l) {
		frame.addMouseListener(l);
	}

	public void addMouseMotionListener(MouseMotionListener l) {
		frame.addMouseMotionListener(l);
	}

	public void addMouseWheelListener(MouseWheelListener l) {
		frame.addMouseWheelListener(l);
	}

	public void addInputMethodListener(InputMethodListener l) {
		frame.addInputMethodListener(l);
	}

	public void requestFocus() {
		frame.requestFocus();
	}

	public boolean requestFocusInWindow() {
		return frame.requestFocusInWindow();
	}

	public void transferFocus() {
		frame.transferFocus();
	}

	public boolean hasFocus() {
		return frame.hasFocus();
	}

	public void add(PopupMenu popup) {
		frame.add(popup);
	}

	public String toString() {
		return frame.toString();
	}

	public void addToContentPane(ETextField tf) {
		getContentPane().add(tf.getJTextField());
		
	}

}
