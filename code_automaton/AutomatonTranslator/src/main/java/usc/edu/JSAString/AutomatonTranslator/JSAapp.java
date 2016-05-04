package usc.edu.JSAString.AutomatonTranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.OptionalDataException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import sql.usc.StringResolver.ExtendedOperation;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RunAutomaton;

/**
 * Hello world!
 *Use for simple test cases 
 */

public class JSAapp 
{
	public static Set<String> readGT(String gtpath) throws IOException{

    	BufferedReader br = new BufferedReader(new FileReader(gtpath));
    	String line;
    	Set<String> gtset=new HashSet<String>();
    	Set<String> tp=new HashSet<String>();
    	int maxlen=0;
    	while ((line = br.readLine()) != null) {
    	   // process the line.
    		//System.out.println(line);
    		if(line.length()>maxlen)
    			maxlen=line.length();
    		gtset.add(line);
    	}
    	//System.out.println(maxlen);
    	return gtset;
	}

    public static void main( String[] args ) throws IOException, ClassCastException, ClassNotFoundException
    {
 		if(args.length!=2)
    	{
    		System.out.println("Usage: JSAsolver groundtruth_folder automaton_folder");
    		System.exit(0);
    	}
  		String gtpath=args[0];
    	String automatonpath=args[1];
    	String idtonamepath=automatonpath+File.separator+"idtable.txt";
    	HashMap<String,String> idtoname=new HashMap<String,String>();
    	String line;
    	BufferedReader br = new BufferedReader(new FileReader(idtonamepath));
    	while ((line = br.readLine()) != null) {
    		String content[]=line.split(":");
    		idtoname.put(content[1].trim(), content[0].trim());

     	}
    	br.close();
    	File folder = new File(automatonpath);

    	File[] listOfFiles = folder.listFiles();

    	for (int i = 0; i < listOfFiles.length; i++) {
    		String filename=listOfFiles[i].getName();
    		String autopath=listOfFiles[i].getAbsolutePath();
    		if(filename.endsWith(".automaton"))
    		{
    			System.out.println(filename);
    			String id=filename.replaceAll(".automaton", "");
    			if(idtoname.get(id)!=null)
    			{
        			String gtfile=gtpath+File.separator+idtoname.get(id)+".gt";
        			File f=new File(gtfile);
        			//System.out.println(idtoname.get(id)+" "+id);
        			Recisiondata r=AccuracyCalculator.CalculateApp(gtfile,autopath,idtoname.get(id));
        			System.out.println(r.toString());
    			}


    		}
    	}

    	
    }
}

