package net.raysforge.visual;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPanel;

import net.raysforge.easyswing.EasyPanel;
import net.raysforge.easyswing.EasySplitPane;
import net.raysforge.easyswing.EasySwing;
import net.raysforge.easyswing.EasyTabbedPane;
import net.raysforge.easyswing.EasyTree;

// http://stackoverflow.com/questions/1186333/how-can-i-capture-all-mouse-events-in-a-jframe-swing

public class VisualLang {

	private EasySwing es;
	private EasyTabbedPane classesPanel;
	private EasyTree tree;
	Binding b = new Binding();
	GroovyShell gs = new GroovyShell(b);
	int newX = 0;

	public VisualLang() {
		es = new EasySwing("VisualLang", 1024, 768);
		Container contentPane = es.getContentPane();
		contentPane.setLayout(new BorderLayout());

		JMenu miFile = es.addMenuItem("File");
		es.addMenuItem(miFile, "New Class", "newClass", (e) -> newClass());
		es.addMenuItem(miFile, "Open", "openClass", (e) -> loadClass());
		es.addMenuItem(miFile, "Save", "saveClass", (e) -> saveClass());

		es.addToolBarItem("New Class", "newClass", (e) -> newClass());
		es.addToolBarItem("add variable", "addVar", (e) -> {
			VisualVariable v = new VisualVariable(getActiveClassPanel());
			MyClass.variables.add(v);
			addInstruction(v, newX += 100, 100);
		});
		es.addToolBarItem("add method", "addMeth", (e) -> addInstruction(new VisualMethod(getActiveClassPanel()), newX += 100, 100));
		es.addToolBarItem("add expression", "addExpr", (e) -> addInstruction(new VisualExpression(getActiveClassPanel()), newX += 100, 100));
		es.addToolBarItem("run", "run", (e) -> run());

		classesPanel = new EasyTabbedPane();

		tree = new EasyTree("Classes");
		tree.setEditable(true);
		tree.addActionListener(e -> openClass(((EasyTree) e.getSource()).getSelectedNode().getUserObject().toString()));

		EasySplitPane esp = es.setSplitPaneAsMainContent(true, 220);
		esp.setLeft(tree);
		esp.setRight(classesPanel);
		contentPane.validate();
		es.show();
	}

	private void saveClass() {
		String name = classesPanel.getActiveTabName();
		File f = es.getFileFromFileSaveDialog(name+".vlang");
		if (f != null)
			MyClass.save(f.getAbsolutePath(), getActiveClassPanel());
	}

	private void loadClass() {
		File f = es.getFileFromFileOpenDialog();
		if (f != null)
			MyClass.load(f.getAbsolutePath(), this, getActiveClassPanel());
	}

	private void newClass() {
		tree.startEditing(tree.addNode("Class"));
		openClass("Class");
	}

	private void openClass(String name) {
		EasyPanel ep = new EasyPanel();
		ep.addToolBarItem("extends");
		ep.addToolBarTextField();
		ep.addToolBarItem("implements");
		ep.addToolBarTextField();
		JPanel p = new JPanel();
		p.setLayout(null);
		ep.setCenter(p);
		classesPanel.addTab(name, ep);
	}

	private JComponent getActiveClassPanel() {
		return ((EasyPanel) classesPanel.getActiveTab()).getCenterComponent();
	}

	public VisualInstruction addInstruction(VisualInstruction i, int x, int y) {
		i.setLocation(x, y);
		getActiveClassPanel().add(i);
		getActiveClassPanel().repaint();
		getActiveClassPanel().validate();
		i.focus();
		return i;
	}

	private void run() {
		//b.setVariable("user", userInfo);
		//b.setVariable("server", port);
		//b.setVariable("window", this);
		StringBuffer sb = new StringBuffer();
		sb.append("import net.raysforge.visual.smartobjects.*;");
		for (Component component : getActiveClassPanel().getComponents()) {
			if (component instanceof VisualVariable) {
				VisualVariable var = (VisualVariable) component;
				sb.append(var.getRealType() + " " + var.getVariableName() + "= new " + var.getRealType() + "();");
			} else if (component instanceof VisualExpression) {
				VisualExpression ex = (VisualExpression) component;
				sb.append(ex.getVariableName() + "." + ex.getMethodName() + "(" + ex.getCommaSeparatedParameter() + ");");
			}
		}

		System.out.println(sb);
		try {
			gs.evaluate(sb.toString());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new VisualLang();
	}

}
