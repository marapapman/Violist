package usc.edu.JSAString.AutomatonTranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import sql.usc.StringResolver.ExtendedOperation;
import dk.brics.automaton.Automaton;

public class MarketappWidener {
	public static Automaton readTxt(String irpath) throws IOException{

    	BufferedReader br = new BufferedReader(new FileReader(irpath));
    	String line;
    	Set<String> gtset=new HashSet<String>();
    	Set<String> tp=new HashSet<String>();
    	int maxlen=0;
		Automaton c=Automaton.makeEmpty();

    	while ((line = br.readLine()) != null) {
			c=c.union(Automaton.makeString(line));

    	}
    	//System.out.println(maxlen);
    	Automaton r= ExtendedOperation.Widen(c, c);
    	return r;
	}
	public static void main(String argv[]) throws IOException, ClassCastException, ClassNotFoundException{
		if(argv.length!=1)
    	{
    		System.out.println("Usage: AutomatonWidener string_folder ");
    		System.exit(0);
    	}
		String stringpath=argv[0];
    	File folder = new File(stringpath);
    	File[] listOfFiles = folder.listFiles();
    	long start=System.currentTimeMillis();

		for (int i = 0; i < listOfFiles.length; i++) {
    		String filename=listOfFiles[i].getName();
    		String txtpath=listOfFiles[i].getAbsolutePath();
    		if(filename.endsWith(".txt"))
    		{
    			String casename=filename.replaceAll(".txt", "");
    			
    			System.out.println(casename);
    			//Set<String> gt=new HashSet<String>();

    			Automaton a=readTxt(txtpath);


    			
    		}
    	}
    	long end=System.currentTimeMillis();
    	System.out.println("end"+(end-start));



	}
}
