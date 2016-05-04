package sql.usc.StringResolver.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

public class ClassReader {
	public static Set<String> readClasses(String classpath) throws ClassFormatException, IOException
	{
		  File folder = new File(classpath);
		  Set<String> r=new HashSet<String>();
		  Collection<File> files = FileUtils.listFiles(folder,new RegexFileFilter(".*\\.class"), DirectoryFileFilter.DIRECTORY);
		  Iterator<File> iterf=files.iterator();
		  while(iterf.hasNext())
		  {
			 JavaClass jc= new ClassParser(iterf.next().getAbsolutePath()).parse();
			  r.add(jc.getClassName());
		  }
		return r;
	}
}
