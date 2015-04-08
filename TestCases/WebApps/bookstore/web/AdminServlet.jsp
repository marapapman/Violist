<html>
<body>
<h1> This page is only used to force the dumping of cobertura coverage information </h1>

<% 
		try {
		    String className = "net.sourceforge.cobertura.coveragedata.ProjectData";
		    String methodName = "saveGlobalProjectData";
		    Class saveClass = Class.forName(className);
		    java.lang.reflect.Method saveMethod = saveClass.getDeclaredMethod(methodName, new Class[0]);
		    saveMethod.invoke(null,new Object[0]);
			out.println("Dumped data");
		} catch (Throwable t) {
			out.println("Something bad happened: " + t.toString());
			t.printStackTrace();
		}

%>



</body>
</html>

