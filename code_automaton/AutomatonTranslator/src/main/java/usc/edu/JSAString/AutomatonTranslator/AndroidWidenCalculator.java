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


public class AndroidWidenCalculator {
	public static Set<String> readFile(File f)
	{
		Set<String> r=new HashSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if(!line.equals(""))
		    		r.add(line);
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
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
    		/*if(line.length()>10000)
    		{
    			f=Automaton.makeAnyString();
    			break;
    		}*/
    		String strings[]=line.split("@@@");
    		Automaton c=Automaton.makeEmpty();
    		int len=strings.length/20+1;
    		//System.out.println(len+" "+strings.length);
    
    		for(int i=0;i<strings.length;i++)
    		{
    			if(strings[i].equals("\\\'\\\'")||strings[i].equals(""))
    			{
    				continue;
    			}
    			c=c.union(Automaton.makeString(strings[i]));
    			/*if(i%len==0)
    			{
    				Automaton t=f.union(c);
        			f=ExtendedOperation.Widen(f, t);  
            		c=Automaton.makeEmpty();

    			}*/
    		}
    		if(!c.subsetOf(f))
    		{
    			Automaton t=f.union(c);

    			f=ExtendedOperation.Widen(f, t);  
    			
    		}


    	}
    	//System.out.println(f.isFinite());
    	return f;
	}
	public static void main(String argv[]) throws OptionalDataException, InvalidClassException, ClassCastException, ClassNotFoundException, IOException
	{
		if(argv.length!=3)
		{
			System.out.println("usage: StringSetCalculator string_out widen_output gt");
			System.exit(0);
		}
		String stringpath=argv[1];
		String gtpath=argv[2];
		String stringoout_path=argv[0];
		int maxlen=0;
	   	HashMap<String,String> nametoid=new HashMap<String,String>();
	   	HashMap<String,String> widennametoid=new HashMap<String,String>();
    	String fasiidtonamepath=stringpath+File.separator+"idtable.txt";
    	String line1;
    	BufferedReader br1 = new BufferedReader(new FileReader(fasiidtonamepath));
    	while ((line1 = br1.readLine()) != null) {
    		String content[]=line1.split(":");
    		widennametoid.put(content[0].trim(), content[1].trim());

     	}
    	br1.close();

 
		//String gtpath=argv[2];
		double cnt=0;
		double avepre=0;
		double averecall=0;
		
		double cnt2=0;
		double avepre2=0;
		double averecall2=0;
		
		boolean myflag=false;
		double concatpre=0;
		double concatrecall=0;
		double concatcnt=0;

		double manippre=0;
		double maniprecall=0;
		double manipcnt=0;
		
		double convergepre=0;
		double convergerecall=0;
		double convergecnt=0;
		
		double mixpre=0;
		double mixrecall=0;
		double mixcnt=0;
		
		double concatpre2=0;
		double concatrecall2=0;
		double concatcnt2=0;

		double manippre2=0;
		double maniprecall2=0;
		double manipcnt2=0;
		
		double convergepre2=0;
		double convergerecall2=0;
		double convergecnt2=0;
		
		double mixpre2=0;
		double mixrecall2=0;
		double mixcnt2=0;
		
	   	double cntjsa=0;
			double aveprejsa=0;
			double averecalljsa=0;
			

			double concatprejsa=0;
			double concatrecalljsa=0;
			double concatcntjsa=0;

			double manipprejsa=0;
			double maniprecalljsa=0;
			double manipcntjsa=0;
			
			double convergeprejsa=0;
			double convergerecalljsa=0;
			double convergecntjsa=0;
			
			double mixprejsa=0;
			double mixrecalljsa=0;
			double mixcntjsa=0;
	    	  double total_Widen_time=0;

    	File folder = new File(gtpath);
    	File[] listOfFiles = folder.listFiles();
    	long totalstart=System.currentTimeMillis();
		for (int i = 0; i < listOfFiles.length; i++) {
    		String filename=listOfFiles[i].getName();
    		if(filename.endsWith(".gt"))
    		{
    			//For SSI
    			String casename=filename.replaceAll(".gt", "");
    			String gtfile=gtpath+"/"+casename+".gt";
    			File gtf=new File(gtfile);
    			if(gtf.exists())
    			{
    				Set<String> gtset=readFile(gtf);
    				
        			 File outputfile=new File(stringoout_path+"/"+casename+".txt");
   		    	  	Set<String> outputset=readFile(outputfile);
   		    	  	for(String s:outputset)
   		    	  	{
   		    	  		if(s.length()>maxlen)
   		    	  			maxlen=s.length();
   		    	  	}
   		    	 double allpositive=outputset.size();
		    	  outputset.retainAll(gtset);
		    	  double tp=outputset.size();
		    	  double fn=gtset.size()-outputset.size();
		    	  double precision=tp/allpositive;
		    	  double recall=tp/(tp+fn);
		    	  if(outputset.size()!=0)
		    	  {
		    		 // System.out.println(listOfFiles[i].getName()+" "+precision*100+"% "+recall*100+"%");
		        	  cnt++;
			    	  avepre+=precision;
			    	  averecall+=recall;
		    	  }
		 
		    	  if(listOfFiles[i].getName().startsWith("Concat"))
		    	  {
		    		  	myflag=true;
		    			 concatpre+=precision;
		    			 concatrecall+=recall;
		    			 concatcnt++;
		    	  }
		    	  if(listOfFiles[i].getName().startsWith("ReplaceAll"))
		    	  {
		    		  	myflag=true;
		    			 manippre+=precision;
		    			 maniprecall+=recall;
		    			 manipcnt++;
		    	  }

		    	  if(listOfFiles[i].getName().startsWith("Trim"))
		    	  {
		    		  	myflag=true;
		    		  	convergepre+=precision;
		    		  	convergerecall+=recall;
		    			 convergecnt++;
		    	  }

		    	  if(listOfFiles[i].getName().startsWith("Mix"))
		    	  {
		    		  	myflag=true;
		    		  	mixpre+=precision;
		    			 mixrecall+=recall;
		    			 mixcnt++;
		    	  }
		    	//for FASI
		    	String txtpath=stringpath+"/"+widennametoid.get(casename)+".automaton";
		    	
		    	File f=new File(txtpath);
		    	if(f.exists())
		    	{
			    	Recisiondata r=new Recisiondata();
		    		long s=System.currentTimeMillis();
		        	InputStream is=new FileInputStream(txtpath);
		        	Automaton a=Automaton.load(is);
		        	is.close();
		        	//System.out.println("States: "+a.getNumberOfStates());
			    		long e=System.currentTimeMillis();

			    		total_Widen_time+=(e-s);

		      			r=AccuracyCalculator.CalculateApp(gtset, a, casename,maxlen);
			    		 // System.out.println(r.name+" "+r.precision*100+"% "+r.recall*100+"%");
		        			//System.out.println(r2.name+" "+r2.precision+" "+r2.recall);
		      			if(r.recall!=0)
		      			{
		           			avepre2+=r.precision;
		        			averecall2+=r.recall;
		        			cnt2++;
		      			}
		      			else{
		           			avepre2+=precision;
		        			averecall2+=recall;
		        			cnt2++;
		      			}

		    	}
		    	else{
	    			avepre2+=precision;
        			averecall2+=recall;
        			cnt2++;
        			System.out.println("No");
		    	}
	
	      			
	  		
		    	}


    		}
    	}
		System.out.println("Ave SSI: "+(avepre)/(cnt)+" "+averecall/cnt +" "+cnt);
		System.out.println("Ave FASI: "+(avepre2)/(cnt2)+" "+averecall2/cnt2 +" "+cnt2);
		System.out.println("Ave JSA: "+(aveprejsa)/(cntjsa)+" "+averecalljsa/cntjsa +" "+cntjsa);

		if(myflag)
	    {
			 	concatpre/=concatcnt;
			 	concatrecall/=concatcnt;
		    System.out.println("Concat Average SSI: "+concatpre+" "+concatrecall);
		 	concatpre2/=concatcnt2;
		 	concatrecall2/=concatcnt2;
	    System.out.println("Concat Average FASI: "+concatpre2+" "+concatrecall2);
	 	concatprejsa/=concatcntjsa;
	 	concatrecalljsa/=concatcntjsa;
    System.out.println("Concat Average JSA: "+concatprejsa+" "+concatrecalljsa);
	    
			 	manippre/=manipcnt;
			 	maniprecall/=manipcnt;
		    System.out.println("Manip Average SSI: "+manippre+" "+maniprecall);  
		 	manippre2/=manipcnt2;
		 	maniprecall2/=manipcnt2;
	    System.out.println("Manip Average FASI: "+manippre2+" "+maniprecall2);
	 	manipprejsa/=manipcntjsa;
	 	maniprecalljsa/=manipcntjsa;
    System.out.println("Manip Average JSA: "+manipprejsa+" "+maniprecalljsa);
	    
			 	convergepre/=convergecnt;
			 	convergerecall/=convergecnt;
		    System.out.println("Converge Average SSI: "+convergepre+" "+convergerecall);
		 	convergepre2/=convergecnt2;
		 	convergerecall2/=convergecnt2;
	    System.out.println("Converge Average FASI: "+convergepre2+" "+convergerecall2);
	 	convergeprejsa/=convergecntjsa;
	 	convergerecalljsa/=convergecntjsa;
    System.out.println("Converge Average JSA: "+convergeprejsa+" "+convergerecalljsa);
	    
			 	mixpre/=mixcnt;
			 	mixrecall/=mixcnt;
		    System.out.println("Mix Average SSI: "+mixpre+" "+mixrecall);
		 	mixpre2/=mixcnt2;
		 	mixrecall2/=mixcnt2;
	    System.out.println("Mix Average FASI: "+mixpre2+" "+mixrecall2);
	 	mixprejsa/=mixcntjsa;
	 	mixrecalljsa/=mixcntjsa;
    System.out.println("Mix Average JSA: "+mixprejsa+" "+mixrecalljsa);
	    

	    }

	}
}
