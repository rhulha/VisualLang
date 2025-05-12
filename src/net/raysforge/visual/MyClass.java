package net.raysforge.visual;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyClass {

	public static HashMap<String, VisualVariable> variablesMap = new HashMap<String, VisualVariable>();
	public static List<VisualVariable> variables = new ArrayList<VisualVariable>();

	public static VisualVariable searchVar(String name) {
		for (VisualVariable v : variables) {
			if (v.getVariableName().equals(name))
				return v;
		}
		return null;
	}

	public static List<Point> getSnapPositions(Container cs, Component ignore) {
		List<Point> points = new ArrayList<Point>();
		Component[] components = cs.getComponents();
		for (Component c : components) {
			if (c == ignore) {
			} else if (c instanceof VisualInstruction) {
				VisualInstruction i = (VisualInstruction) c;
				points.add(new Point(i.getX() + i.getWidth(), i.getY()));
				points.add(new Point(i.getX(), i.getY() + i.getHeight()));
			} else {
				System.out.println("weird");
			}
		}
		return points;
	}

	public static void save(String name, Container cont) {
		try (FileWriter fw = new FileWriter(name)) {
			for (Component c : cont.getComponents()) {
				if (c instanceof VisualInstruction) {
					VisualInstruction i = (VisualInstruction) c;
					fw.write(i.serialize() + "\n");
				} else {
					System.out.println("weird");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void load(String fname, VisualLang el, Container mainPanel) {
		// consider StreamTokenizer
		try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(";");

				HashMap<String, String> hm = new HashMap<String, String>();
				for (String tupel : split) {
					String[] kv = tupel.split(":");

					hm.put(kv[0], kv.length > 1 ? kv[1] : "");
				}

				String type = hm.get("type");
				if (type.equals("VisualExpression")) {
					VisualExpression exp = new VisualExpression(mainPanel);
					exp.setVariableName(hm.get("name"));
					exp.setMethodName(hm.get("method"));
					el.addInstruction(exp, Integer.parseInt(hm.get("x")), Integer.parseInt(hm.get("y")));
					exp.paramPanel.refreshParameterList(exp.getVarType());
					exp.setCommaSeparatedParameter(hm.get("parameter"));
				} else if (type.equals("VisualVariable")) {
					VisualVariable v = new VisualVariable(mainPanel);
					v.setVariableName(hm.get("name"));
					v.setType(hm.get("class"));
					el.addInstruction(v, Integer.parseInt(hm.get("x")), Integer.parseInt(hm.get("y")));
					variables.add(v);
				} else if (type.equals("VisualMethod")) {
					VisualMethod m = new VisualMethod(mainPanel);
					m.setMethodName(hm.get("name"));
					m.setType(hm.get("class"));
					el.addInstruction(m, Integer.parseInt(hm.get("x")), Integer.parseInt(hm.get("y")));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
