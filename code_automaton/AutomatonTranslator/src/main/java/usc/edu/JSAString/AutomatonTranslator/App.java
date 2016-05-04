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


public class App 
{
    public static void main( String[] args ) throws IOException, ClassCastException, ClassNotFoundException
    {

    	//CalculateApp("/home/dingli/svnfiles/StringTestCases/groundtruth/Concat.CircleLoop_Branch.gt","/home/dingli/svnfiles/StringTestCases/JSAtool/JSAResult/13.automaton","test");

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
    	String[] x={"Concat","ReplaceAll",	"ReplaceFirst",	"toUpperCase",	"toLowerCase",	"Trim",	"Mix"};
    	String[] y={"Branch","SingleLoop",	"NestedLoop",	"InterProcedural",	"CircleLoop",	"Branch_Interprocedural",	"SingleLoop_Interprocedural","SingleLoop_Branch","NestedLoop_Branch","NestedLoop_Interprocedural","CircleLoop_Branch","CircleLoop_Interprocedural","Mix"};

    	HashMap<String,Recisiondata> map=new HashMap<String,Recisiondata>();
    	//49 61
    	int autocnt=0;
    	for (int i = 0; i < listOfFiles.length; i++) {
    		String filename=listOfFiles[i].getName();
    		String autopath=listOfFiles[i].getAbsolutePath();
    		if(filename.endsWith(".automaton"))
    		{
    			autocnt++;
    			String id=filename.replaceAll(".automaton", "");
    			//System.out.println(autocnt+" "+id+" "+idtoname.get(id));
    			String gtfile=gtpath+File.separator+idtoname.get(id)+".gt";
    			Recisiondata r=AccuracyCalculator.CalculateApp(gtfile,autopath,idtoname.get(id));
    			//System.out.println(r.name+" "+r.precision);
    			map.put(r.name,r);
    		}
    	}
    	for(int i=0;i<y.length;i++)
    	{
    		for(int j=0;j<x.length;j++)
    		{
    			String check=x[j]+"."+y[i];
    			Recisiondata data=map.get(check);
    			System.out.print(data.precision*100+",");
    			
    		}
    		System.out.println();
    	}
    }
}
