package usc.edu.JSAString.AutomatonTranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.OptionalDataException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RunAutomaton;

public class JavaAppCompare {
	public static Set<String> readTxt(String gtpath) throws IOException{

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
    		//line=line.replaceAll("[0-9]+", "");
    		line=line.replaceAll("\"", "\\\\");
    		//System.out.println(line);
    		gtset.add(line);
    	}
    	//System.out.println(maxlen);
    	return gtset;
	}
	public static Recisiondata CalculateApp(String groundtruth, String automatonpath,String Casename) throws OptionalDataException, InvalidClassException, ClassCastException, ClassNotFoundException, IOException
	{
    	InputStream is=new FileInputStream(automatonpath);
    	Automaton jsa=Automaton.load(is);
    	RunAutomaton checker=new RunAutomaton(jsa);
    	BufferedReader br = new BufferedReader(new FileReader(groundtruth));
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
    		if(checker.run(line))
    		{
    			tp.add(line);
    		}

    	}
    	//System.out.println(maxlen);
    	long num=0;
    	if(jsa.isFinite())
    	{
    		num=jsa.getFiniteStrings().size();
    		if(jsa.run(""))
    		{
    			num++;
    		}
    		System.out.println(num);
    		double precision=(double)tp.size()/(double)num;
        	double recall=(double)tp.size()/(double)gtset.size();
        	Recisiondata R=new Recisiondata();
        	R.name=Casename;
        	R.precision=precision;
        	R.recall=recall;
        	return R;
    	}
    	else
    	{

    		double precision=0;
        	double recall=1;
        	Recisiondata R=new Recisiondata();
        	R.name=Casename;
        	R.precision=precision;
        	R.recall=recall;
        	return R;
    	}

    	//System.out.println(Casename+" "+precision*100+"% "+recall*100+"%");
	}
    public static void main( String[] args ) throws IOException, ClassCastException, ClassNotFoundException
    {
 		if(args.length!=3)
    	{
    		System.out.println("Usage: JSAcompare groundtruth_folder automaton_folder IR_path");
    		System.exit(0);
    	}
  		String gtpath=args[0];
    	String automatonpath=args[1];
    	String irpath=args[2];
    	File folder = new File(irpath);
    	File[] listOfFiles = folder.listFiles();
		int cnt=0;
		 
     	for (int i = 0; i < listOfFiles.length; i++) {
    		String filename=listOfFiles[i].getName();
    		String irfilepath=listOfFiles[i].getAbsolutePath();
    		String gtfilepath=gtpath+File.separator+filename.replace(".txt", ".gt");
    		String autofilepath=automatonpath+File.separator+filename.replace(".txt", ".automaton");
    		File auto=new File(autofilepath);
    		File gt=new File(gtfilepath);
    		if(auto.exists()&&gt.exists())
    		{
    			Set<String> gtstrings=readTxt(gtfilepath);
    			Set<String> irstrings=readTxt(irfilepath);
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
        			Recisiondata r=CalculateApp(gtfilepath,autofilepath,filename.replace(".txt", ".gt"));

        			if(recall==1)
        			{
        				cnt++;
        				//System.out.println(gtfilepath);
            			System.out.println(gtfilepath);
            			System.out.println(precision+" "+recall);
            			System.out.println(r.precision+" "+r.recall);
        			}
        			
        			



    			}




    		}




    	}
     	System.out.println(cnt);


    	
    }
}
