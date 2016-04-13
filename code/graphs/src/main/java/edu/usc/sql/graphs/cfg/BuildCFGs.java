package edu.usc.sql.graphs.cfg;

import java.util.Map;

public class BuildCFGs {
	
	public static Map<String, CFGInterface> buildCFGs(String rootDir, String component) {
		if (component.endsWith(".class")) {
			return SootCFG.buildCFGs(rootDir, component);
		} else if(component.endsWith(".php")) {
			return PixyCFG.buildCFGs(rootDir, component);			 
		}
		return null;
	}
}
