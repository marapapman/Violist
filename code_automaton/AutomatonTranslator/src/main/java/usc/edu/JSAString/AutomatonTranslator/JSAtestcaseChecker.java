package usc.edu.JSAString.AutomatonTranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import dk.brics.automaton.Automaton;

public class JSAtestcaseChecker {
	public static void main( String[] args ) throws IOException, ClassCastException, ClassNotFoundException
    {

    	//CalculateApp("/home/dingli/svnfiles/StringTestCases/groundtruth/Concat.CircleLoop_Branch.gt","/home/dingli/svnfiles/StringTestCases/JSAtool/JSAResult/13.automaton","test");


    	String automatonpath="/home/dingli/svnfiles/StringTestCases/JSAtool/JSAResult2";
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
    
    	HashMap<String,Recisiondata> map=new HashMap<String,Recisiondata>();
    	//49 61
    	int autocnt=0;
    	int good=0;
    	int bad=0;
    	for (int i = 0; i < listOfFiles.length; i++) {
    		String filename=listOfFiles[i].getName();
    		String autopath=listOfFiles[i].getAbsolutePath();
    		
    		if(filename.endsWith(".automaton"))
    		{
    	    	InputStream is=new FileInputStream(autopath);
    	    	Automaton jsa=Automaton.load(is);
    			String id=filename.replaceAll(".automaton", "");
    			String casename=idtoname.get(id);
    			//System.out.println(id);
    	    	if(jsa.isFinite())
    	    	{
    	    		if(!AutomatonDumper.ifonlyASCII(jsa))
    	    			System.out.println(casename);
    	    		good++;
    	
    	    	}
    	    	else{
    	    		bad++;
    	    		System.out.println(casename);
    	    	}
    	    	
    		}
    	}
    	System.out.println(good);
    	System.out.println(bad);


    }
}
