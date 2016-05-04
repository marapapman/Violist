package usc.edu.JSAString.AutomatonTranslator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;

public class AutomatonDumper {
	static long MAX_NUM=10000000;
	private static boolean validChar(char c)
	{
		int n=(int)c;
		if(n<0||n>256)
			return false;
		return true;
	}
	private static long getFiniteStrings(State s, HashSet<String> strings, StringBuilder path, int limit) {
		//System.out.println(path.toString()+" "+limit);
		long cnt=0;
		if(limit <=0)
			return 0;
		List<Transition> list = new ArrayList<Transition>();
		list.addAll(s.getTransitions());
		Comparator<Transition> comparator = new Comparator<Transition>() {
		    public int compare(Transition c1, Transition c2) {
		        return c1.getMin() - c2.getMin(); // use your logic
		    }
		};
		Collections.sort(list,comparator);
		for (Transition t : list) {
			//System.out.println(t.getMin()+" "+t.getMax());
			if(!(validChar(t.getMax())&&validChar(t.getMin())))
			{
				return Long.MAX_VALUE;
			}
			for (int n = t.getMin(); n <= t.getMax(); n++) {
				path.append((char)n);

				if (t.getDest().isAccept()) {
					cnt++;
					if(strings!=null)
					{
						strings.add(path.toString());
					}
				}
				long temp=getFiniteStrings(t.getDest(), strings, path, limit-1);
				if(temp==Long.MAX_VALUE)
				{
					cnt=temp;
				}
				else{
					cnt+=getFiniteStrings(t.getDest(), strings, path, limit-1);
				}
				path.deleteCharAt(path.length() - 1);
			}
		}
		return cnt;
	}
	private static long countFiniteStrings(State s,  int limit) {
		//System.out.println(path.toString()+" "+limit);
		long cnt=0;
		if(limit <=0)
			return 0;
		int i=0;
		for (Transition t : s.getTransitions()) {
			i++;
			int tnum=t.getMax()-t.getMin()+1;
			if(t.getDest().isAccept())
				cnt+=tnum;
			long temp=countFiniteStrings(t.getDest(), limit-1);

			cnt+=tnum*temp;
			if(cnt>MAX_NUM)
				return MAX_NUM;
		}
		return cnt;
	}
	private static boolean CheckASCII(State s, HashSet<State> pathstates) {
		pathstates.add(s);
		boolean flag=true;
		for (Transition t : s.getTransitions()) {

			for (int n = t.getMin(); n <= t.getMax(); n++) {
				if(!validChar((char)n))
					return false;
			}
			if (pathstates.contains(t.getDest()))
				continue;
			flag&=CheckASCII(t.getDest(),pathstates);

		}
		return flag;
	}

	public static Set<String> dumpFiniteStrings(Automaton a, int limit)
	{
		HashSet<String> strings=new HashSet<String>();
		StringBuilder path=new StringBuilder();
		getFiniteStrings(a.getInitialState(),strings,path,limit);
		return strings;
	}
	public static long cntFiniteStrings(Automaton a, int limit)
	{
		a.getFiniteStrings();
		HashSet<String> strings=new HashSet<String>();
		StringBuilder path=new StringBuilder();
		//long cnt=getFiniteStrings(a.getInitialState(),null,path,limit);
		long cnt2=countFiniteStrings(a.getInitialState(),limit);
		//System.out.println(cnt2);
		return cnt2;
	}
	public static boolean ifonlyASCII(Automaton a)
	{
		HashSet<State> pathstates=new HashSet<State>();
		return CheckASCII(a.getInitialState(),pathstates);
	}
	
}
