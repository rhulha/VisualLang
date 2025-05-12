package net.raysforge.visual.smartobjects;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Types {
	
	public static List<Class<?>> types = new ArrayList<Class<?>>();

	public static Map<String, Class<?>> typeMap = new HashMap<String, Class<?>>();
	
	static {

		types.add(EString.class);
		types.add(EFrame.class);
		types.add(ETextField.class);
		types.add(Void.class);
		types.add(Dimension.class);
		
		typeMap.put("String", EString.class);
		typeMap.put("Frame", EFrame.class);
		typeMap.put("TextField", ETextField.class);
		typeMap.put("Void", Void.class);
		typeMap.put("Dimension", Dimension.class);
		
	}

}
