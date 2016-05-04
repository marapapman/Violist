package usc.edu.JSAString.AutomatonTranslator;

import java.io.BufferedReader;
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
class Recisiondata{
	String name;
	double precision;
	double recall;
	public String toString(){
		return this.name+" "+this.precision*100+"% "+this.recall*100+"%";
	}
}
public class AccuracyCalculator {
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
    	}
    	else
    	{
    		System.out.println(Casename);

    		num=AutomatonDumper.cntFiniteStrings(jsa, maxlen);
    	}
    	double precision=(double)tp.size()/(double)num;
    	double recall=(double)tp.size()/(double)gtset.size();
    	Recisiondata R=new Recisiondata();
    	R.name=Casename;
    	R.precision=precision;
    	R.recall=recall;
    	return R;
    	//System.out.println(Casename+" "+precision*100+"% "+recall*100+"%");
	}
	public static Recisiondata CalculateApp(Set<String> gt, Automaton a,String Casename,int max) throws OptionalDataException, InvalidClassException, ClassCastException, ClassNotFoundException, IOException
	{
		int maxlen=0;
    	Set<String> tp=new HashSet<String>();
    	RunAutomaton checker=new RunAutomaton(a);

		for(String s:gt)
		{
    		if(s.length()>maxlen)
    			maxlen=s.length();
			if(checker.run(s))
			{
				tp.add(s);
			}
		}
		if(maxlen==0)
			maxlen=max;
    	long num=0;
    	if(a.isFinite())
    	{
    		num=a.getFiniteStrings().size();
    		//System.out.println(a.getFiniteStrings());
    	}
    	else
    	{
    		/*Set<String> s=a.getStrings(maxlen);
    		
    		if(s.containsAll(gt))
    		{
    			num=s.size();
    		}
    		else{
    			num=Long.MAX_VALUE;
    		}*/
    		if(max-a.getCommonPrefix().length()>100)
    			num= 10000000;
    		else
    			num=AutomatonDumper.cntFiniteStrings(a, maxlen);
    		//System.out.println(num);
    	}

    	//	num=AutomatonDumper.cntFiniteStrings(a, maxlen);
    	double precision=(double)tp.size()/(double)num;
    	double recall=(double)tp.size()/(double)gt.size();
    	Recisiondata R=new Recisiondata();
    	R.name=Casename;
    	R.precision=precision;
    	R.recall=recall;
    	return R;
    	//System.out.println(Casename+" "+precision*100+"% "+recall*100+"%");
	}
}
