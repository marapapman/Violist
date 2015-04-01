package reflection.ReflectTest;
import LoggerLib.Logger;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class ReflectTest {
 private static Class[] findClasses(String thePackage, String interfaceName)
                         throws ClassNotFoundException {
    File directory=new File(thePackage);
    Class iface;
    try {
      iface=Class.forName("basic."+interfaceName);
			Logger.reportString("basic."+interfaceName,"reflection.ReflectTest.ReflectTest11");
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException(interfaceName);
    }
    if (!directory.isDirectory()) {
      System.err.println(thePackage+" is not a directory?");
    }
    Class[] retval;
    String[] unfilteredFiles=directory.list();
    Vector classes=new Vector();
    for (int i=0; i<unfilteredFiles.length; i++) {
      if (unfilteredFiles[i].endsWith(".class")) {
        try {
          Class c=Class.forName(thePackage+"."+
               unfilteredFiles[i].substring(0,unfilteredFiles[i].length()-6));
          if (iface.isAssignableFrom(c)) {
            classes.addElement(c);
          }
        } catch (ClassNotFoundException e) {
          System.err.println("Class not found in .class file: "+
                             unfilteredFiles[i]);
        }
      }
    }
    retval=new Class[classes.size()];
    for (int i=0; i<retval.length; i++) retval[i]=(Class)(classes.elementAt(i));
    return retval;
  }

  public static void main(String[] args) throws NoSuchMethodException,
                                                SecurityException,
                                                ClassNotFoundException,
                                                InstantiationException,
                                                InvocationTargetException,
                                                IllegalAccessException{

   findClasses("problems","Problem");

  }
}
