package wam.configuration;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import wam.configuration.exceptions.WAMConfigurationException;

public class WAMConfiguration {
	
	private Map<String, String> classes = new HashMap<String, String>();
	private Map<String, String> targets = new HashMap<String, String>();
	private Set<ConfMainMethod> mainmethods = new HashSet<ConfMainMethod>();

	private Set<String> servlets = new HashSet<String>();
	private Set<String> jars = new HashSet<String>();
	private String classpath = "";
	private String app="";
	
	private File file;
	public Set<ConfMainMethod> getMainMethods()
	{
		return mainmethods;
	}
	public void addMainMethod(ConfMainMethod m)
	{
		mainmethods.add(m);
	}
	public WAMConfiguration() {
		
	}
	
	public WAMConfiguration(File file) {
		this.file = file;
	}
	
	public static WAMConfiguration load(String filename) throws WAMConfigurationException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();

			SAXParser saxParser = factory.newSAXParser();
			File file = new File(filename);
			WAMConfigParser parser = new WAMConfigParser(file);
			saxParser.parse( file, parser);

			return parser.getConfiguration();
			
		} catch (ParserConfigurationException e) {
			throw new WAMConfigurationException(e);
		} catch (SAXException e) {
			throw new WAMConfigurationException(e);
		} catch (IOException e) {
			throw new WAMConfigurationException(e);
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public Set<String> getClasses() {
		return new HashSet<String>(classes.keySet());
	}
	
	public String getTarget(String className) {
		return classes.get(className);
	}
	
	public String getClass(String target) {
		return targets.get(target);
	}
	
	public Collection<String> getAllTargets() {
		return classes.values();
	}
	
	public Collection<String> getAllServlets() {
		return servlets;
	}
	
	public String getAppName() {
		return app;
	}
	
	public void setAppName(String name) {
		app=name;
	}
	
	public void setClassPath(String cp) {
		classpath = cp;
	}
	
	public String getClassPath() {
		return classpath;
	}
	
	public Set<String> getAllClassPaths() {
		Set<String> classpaths = new HashSet<String>();
		classpaths.add(getClassPath());
		classpaths.addAll(getRequiredJars());
		return classpaths;
	}
	
	public void addClass(String className, String target) {
		classes.put(className, target);
		targets.put(target, className);
	}
	
	public void addServlet(String target) {
		servlets.add(target);
	}
	
	public void addRequiredJar(String jar) {
		jars.add(jar);
	}
	
	public Set<String> getRequiredJars() {
		return jars;
	}
	
}
