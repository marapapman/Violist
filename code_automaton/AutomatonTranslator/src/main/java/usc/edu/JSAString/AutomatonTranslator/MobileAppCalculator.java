package usc.edu.JSAString.AutomatonTranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.OptionalDataException;
import java.util.HashSet;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RunAutomaton;

public class MobileAppCalculator {

    public static void main( String[] args ) throws IOException, ClassCastException, ClassNotFoundException
    {
 		if(args.length!=2)
    	{
    		System.out.println("Usage: JSAsolver groundtruth_folder IR_path");
    		System.exit(0);
    	}
  		String gtpath=args[0];
    	String irpath=args[1];
    	File folder = new File(irpath);
    	File[] listOfFiles = folder.listFiles();
		int cnt=0;
		double aveprecision=0;
     	for (int i = 0; i < listOfFiles.length; i++) {
    		String filename=listOfFiles[i].getName();
    		String irfilepath=listOfFiles[i].getAbsolutePath();
    		String gtfilepath=gtpath+File.separator+filename.replace(".txt", ".gt");
    		File gt=new File(gtfilepath);
    		if(gt.exists())
    		{
    			Set<String> gtstrings=JavaAppCompare.readTxt(gtfilepath);
    			Set<String> irstrings=JavaAppCompare.readTxt(irfilepath);
    	    	/*InputStream is=new FileInputStream(autofilepath);
    	    	Automaton jsa=Automaton.load(is);
    	    	is.close();
    	    	if(irstrings.size()>0 && jsa.isFinite())
    	    	{
        			System.out.println(gtfilepath);

    	    	}*/
    			if(irstrings.size()>0)
    			{
        			double irsize=irstrings.size();
        			double gtsize=gtstrings.size();
        			irstrings.retainAll(gtstrings);
        			double tp=irstrings.size();
        			double precision=tp/irsize, recall=tp/gtsize;
        			if(recall==1) //we do not output Unknown variables, if the recall is not 100%, we have Unknowns
        			{
	        			cnt++;
	        			//System.out.println(gtfilepath);
	            		System.out.println(gtfilepath);
	        			System.out.println(irsize+" "+gtsize);
	
	            		System.out.println(precision+" "+recall);
	            		aveprecision+=precision;
        			}
    			}




    		}




    	}
     	System.out.println(aveprecision/cnt);

     	System.out.println(cnt);


    	
    }
}
