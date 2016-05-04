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


public class JustForFun {
	public static Automaton readtext(String path) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
    	String line;
    	Set<String> set=new HashSet<String>();
    	int maxlen=0;
    	Automaton f=Automaton.makeEmpty();
    	int cnt=0;
    	while ((line = br.readLine()) != null) {
    	   // process the line.
    		//System.out.println(line);
    		cnt++;
    		Automaton c=Automaton.makeString(line);
			System.out.println(cnt);

    		if(!c.subsetOf(f))
    		{
    			Automaton t=f.union(c);
    			f=ExtendedOperation.Widen(f, t);  



    		}
    		else{
    			System.out.println("converge "+cnt);
    			return f;
    		}

    	}
    	return f;
	}
	public static void main(String argv[]) throws IOException, ClassCastException, ClassNotFoundException{
		if(argv.length!=1)
    	{
    		System.out.println("Usage: AutomatonWidener string_folder");
    		System.exit(0);
    	}
		String stringpath=argv[0];
		Automaton a=readtext(stringpath);

	}
}
