package usc.edu.JSAString.AutomatonTranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import sql.usc.StringResolver.ExtendedOperation;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RunAutomaton;

public class AutomatonWidener {
	public static Automaton readtext(String path) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
    	String line;
    	Set<String> set=new HashSet<String>();
    	int maxlen=0;
    	Automaton f=Automaton.makeEmpty();
    	
    	while ((line = br.readLine()) != null) {
    	   // process the line.
    		//System.out.println(line);
    		/*
    		if(line.length()>10000)
    		{
    			f=Automaton.makeAnyString();
    			break;
    		}*/
    		String strings[]=line.split("@@@");
    		Automaton c=Automaton.makeEmpty();
    		int len=strings.length/20+1;
    		System.out.println(len+" "+strings.length);
    		if(strings.length>1000)
    		{
    			f=Automaton.makeAnyString();
				break;
    		}

    		for(int i=0;i<strings.length;i++)
    		{
    			c=c.union(Automaton.makeString(strings[i]));
    			/*if(i%len==0)
    			{
    				Automaton t=f.union(c);
        			f=ExtendedOperation.Widen(f, t);  
            		c=Automaton.makeEmpty();

    			}*/
    		}
    		if(c.getNumberOfStates()>500)
    		{
    			f=Automaton.makeAnyString();
    			break;
    		}
    		if(!c.subsetOf(f))
    		{
    			Automaton t=f.union(c);

    			f=ExtendedOperation.Widen(f, t);  
    			


    		}
    	}
    	System.out.println(f.isFinite());
    	return f;
	}
	/*public static Automaton readtext(String path) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
    	String line;
    	Set<String> set=new HashSet<String>();
    	int maxlen=0;
    	Automaton f=Automaton.makeEmpty();
    	
    	while ((line = br.readLine()) != null) {
    	   // process the line.
    		//System.out.println(line);
    		String strings[]=line.split(" ");
    		Automaton c=Automaton.makeEmpty();
    		for(int i=0;i<strings.length;i++)
    		{
    			set.add(strings[i]);
    		}

    	}
    	br.close();
    	int cnt=0;
    	for(String s:set){
    		RunAutomaton ra=new  RunAutomaton(f);

    		if(!ra.run(s))
    		{
        		Automaton c=Automaton.makeString(s);
    			Automaton t=f.union(c);
    			System.out.println(cnt+"/"+set.size());
    			f=ExtendedOperation.Widen(f, t);
    		}
			cnt++;

			System.out.println(f.isFinite());
			//System.out.println(t.equals(f));


    	}
    	return f;
	}*/
	public static void main(String argv[]) throws IOException, ClassCastException, ClassNotFoundException{
		if(argv.length!=2)
    	{
    		System.out.println("Usage: AutomatonWidener string_folder output_folder");
    		System.exit(0);
    	}
		String stringpath=argv[0];
		String outputpathpath=argv[1];
		//String gtpath=argv[2];

    	File folder = new File(stringpath);
    	File[] listOfFiles = folder.listFiles();
    	double totaltime=0;
    	long totalstart=System.currentTimeMillis();
    	PrintWriter pw=new PrintWriter(outputpathpath+"/idtable.txt");
		for (int i = 0; i < listOfFiles.length; i++) {
    		String filename=listOfFiles[i].getName();
    		String txtpath=listOfFiles[i].getAbsolutePath();
    		if(filename.endsWith(".txt"))
    		{
    			String casename=filename.replaceAll(".txt", "");
    			String outputfile=outputpathpath+"/"+i+".automaton";
    			String dotfilepath=outputpathpath+"/"+i+".dot";
    			System.out.println("Casename: "+casename);
    			pw.println(casename+":"+i);
    			//Set<String> gt=new HashSet<String>();
    	    	long start=System.currentTimeMillis();

    			Automaton a=readtext(txtpath);
    	    	long end=System.currentTimeMillis();
    	    	System.out.println("Time: "+(end-start) +" "+i+" "+a.getNumberOfStates());
    	    	totaltime+=(end-start);
       			OutputStream os=new FileOutputStream(outputfile);
    			a.store(os);
    			os.close();
    			String dot=a.toDot();
    	    	PrintWriter dotpw=new PrintWriter(dotfilepath);
    	    	dotpw.print(dot);
    	    	dotpw.close();
    	    	/*if((end-start)<10000)
    	    	{
        			OutputStream os=new FileOutputStream(outputfile);
        			a.store(os);
        			os.close();
        			String dot=a.toDot();
        	    	PrintWriter dotpw=new PrintWriter(dotfilepath);
        	    	dotpw.print(dot);
        	    	dotpw.close();
    	    	}*/

    			//System.out.println(AutomatonDumper.cntFiniteStrings(a, 3));
    		}
    	}
		pw.close();
    	long totalend=System.currentTimeMillis();
    	System.out.println("Total Time: "+totaltime);
    	
		/*BufferedReader br = new BufferedReader(new FileReader(stringvalue));
		String line;
		int cnt=0;
		Automaton result=Automaton.makeEmpty();
    	while ((line = br.readLine()) != null) {
    		String content[]=line.split(" ");
    		Automaton current=Automaton.makeEmpty();
    		for(int i=0;i<content.length;i++)
    		{
    			current=current.union(Automaton.makeString(content[i]));
    		}
    		if(cnt==0)
    		{
    			result=current;
    		}
    		else{
    			result=ExtendedOperation.Widen(result, current);
    		}
    		cnt++;

     	}
    	System.out.println(result.toDot());
    	Set<String> s=AutomatonDumper.dumpFiniteStrings(result, 7);
       	System.out.println(s);
    	System.out.println(s.size());*/
	}
}
