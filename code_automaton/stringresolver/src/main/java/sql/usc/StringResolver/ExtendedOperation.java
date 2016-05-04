/*
 * this package is used to implememt the operation of widen and replacement from
 * Bultan's paper "Symbolic String Verification: An automata-based Approach"
 */
package sql.usc.StringResolver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;

import dk.brics.automaton.Transition;
class VisitedSet{
	int num=0;
	Set<State> set=new HashSet<State>();
	public VisitedSet(Set<State> a)
	{
		num=a.size();
		this.set=a;
	}
	public int hashCode() {
		return 31 * set.hashCode() + num;
		}
	public boolean equals(Object obj)
	{
		//System.out.println("call");
		if(!(obj instanceof VisitedSet))
			return false;
		VisitedSet a=(VisitedSet)obj;
		if(num==a.num&&set.equals(a.set))
			return true;
		return false;
		
	}
}
public class ExtendedOperation {
	public static final char parameter_seperator=251;
	static final char special1=254;
	static final char special2=253;
	static final char epsilon=252;
 	public static Automaton makeFilter()
 	{
 		char max=250;
		Automaton a = new Automaton();
		State s = new State();
		s.setAccept(true);

		s.addTransition(new Transition(Character.MIN_VALUE, max, s));
		a.setDeterministic(true);
		a.setInitialState(s);
		return a;
 	}
 	public static Automaton makeAnyString()
 	{
 		char max=250;
		Automaton a = new Automaton();
		State s = new State();
		s.setAccept(true);

		s.addTransition(new Transition(Character.MIN_VALUE, ';', s));
		s.addTransition(new Transition('?', max, s));
		s.addTransition(new Transition('=', '=', s));

		a.setDeterministic(true);
		a.setInitialState(s);
		return a;
 	}
 	public static Automaton complement(Automaton a)
 	{
 		Automaton b=a.complement();
		return b.intersection(makeAnyString());
 	}
	//Step 1 in the replacement
	private static Automaton extendtarget(Automaton a0)
	{
		Automaton a=a0.clone();
		a.determinize();
		a.expandSingleton();
		HashMap<State, State> m = new HashMap<State, State>();
		Set<State> states = a.getStates();
		for (State s : states)
			m.put(s, new State());
		for (State s : states) {
			State p = m.get(s);
			p.setAccept(false);
			for (Transition t : s.getTransitions())			
				p.addTransition(new Transition(t.getMin(), t.getMax(), m.get(t.getDest())));
			s.addTransition(new Transition(special1, p));
			p.addTransition(new Transition(special2, s));


			//p.getTransitions();
		}
		
		return a;
	}
	//construct the Automaton Mh
	private static Automaton ConstructMh(Automaton a2)
	{
		Automaton a=makeAnyString();
		a=a.concatenate(a2);
		a=a.concatenate(makeAnyString());
		return complement(a);
	}
	//construct the Automaton in Step 2 of replacement
	private static Automaton ConstructM2(Automaton a2)
	{
		Automaton a=a2.clone();
		a.expandSingleton();
		a.determinize();
		Automaton mh=ConstructMh(a);
		State mhi=mh.getInitialState();
		State ai=a.getInitialState();
		Set<State> Fmh=mh.getAcceptStates();
		Set<State> Fa=a.getAcceptStates();
		for (State s : Fmh)
			s.addTransition(new Transition(special1,ai));
		for (State s : Fa)
		{
			s.addTransition(new Transition(special2,mhi));
			s.setAccept(false);
		}

		return mh;
	}
	private static Hashtable<State,Set<State>> special2reach(Automaton a)
	{
		//optimization is needed
		 Hashtable<State,Set<State>> table=new  Hashtable<State,Set<State>>();
		Set<State> states=a.getStates();
		for (State s : states)
		{
			table.put(s, new HashSet<State>());
		}
		boolean flag=true;
		while(flag)
		{
			flag=false;


			for (State s : states)
			{
				Set<State> reachset2=table.get(s);
				int oldsize=reachset2.size();
				for (Transition t : s.getTransitions())		
					{	
							if(t.getMax()==t.getMin()&&t.getMax()==special2)
								reachset2.add(t.getDest());
							else if(t.getMax()!=special1)
							{
								
								reachset2.addAll(table.get(t.getDest()));
							}
					}
				if(reachset2.size()!=oldsize)
					flag=true;
			}
			
		}
		return table;
	}
	private static Hashtable<State,Set<State>> calreach(Automaton a)
	{
		 Hashtable<State,Set<State>> table=new  Hashtable<State,Set<State>>();
		Set<State> states=a.getStates();
		Hashtable<State,Set<State>> reachspecial2set=special2reach(a);
		for (State s : states)
		{
			table.put(s, new HashSet<State>());
		}
		for (State s : states)
		{
			Set<State> reachset2=table.get(s);
			int oldsize=reachset2.size();
			for (Transition t : s.getTransitions())		
				{	
						if(t.getMax()==t.getMin()&&t.getMax()==special1)
							reachset2.addAll(reachspecial2set.get(t.getDest()));
				}
			//reachset2.remove(s);
		}
		return table;
	}
	private static void addepsilon(State from, State to)
	{
		//System.out.println(to);
		if (to.isAccept())
			from.setAccept(true);
		for (Transition t : to.getTransitions())
		{
				from.addTransition(t);
		}


	}
	private static void addrealepsilon(State from, State to)
	{
		//System.out.println(to);
		Transition t =new Transition(epsilon,epsilon,to);
		from.addTransition(t);
	}
	private static void trmoveepsilon(State from, State to)
	{
		//System.out.println(to);
		if (to.isAccept())
			from.setAccept(true);
		for (Transition t : to.getTransitions())
		{
				from.addTransition(t);
		}
	}
	private static boolean toShadow(State a, Transition tr)
	{
		//this needs optimization
		boolean sp1=false,sp2=false;
		State b=tr.getDest();
		if(tr.getMax()==special2)
			sp2=true;
		else if(tr.getMax()==special1)
			sp1=true;
		for (Transition st : b.getTransitions())
		{
			if(st.getMax()==special1 && st.getDest()==a)
				sp1=true;
			if(st.getMax()==special2 && st.getDest()==a)
				sp2=true;
		}
		return sp1&&sp2;
	}
	private static Automaton clean(Automaton a)
	{
		Set<State> states = a.getStates();
		HashMap<State, State> m = new HashMap<State, State>();
		Automaton r=new Automaton();
		for (State s : states)
			m.put(s, new State());
		for (State s : states) {
			State p = m.get(s);
			p.setAccept(s.isAccept());
			if (s == a.getInitialState())
				r.setInitialState(p);
			for (Transition st : s.getTransitions())
			{
				if(!toShadow(s,st))
					p.addTransition(new Transition(st.getMin(), st.getMax(),m.get(st.getDest())));		
			}

		}
		return r;
	}
	private static Automaton removeepsilon(Automaton a)
	{
		Set<State> states = a.getStates();
		boolean flag=true;
		while(flag)
		{
			flag=false;
			for (State s : states) {
				Set<Transition> remove=new HashSet<Transition>();
				Set<State> epset=new HashSet<State>();
				Set<Transition> tset=s.getTransitions();
				for (Transition st : s.getTransitions())
				{
					//System.out.println(st.getMin());
					if(st.getMin()==epsilon)
					{
						//System.out.println("oh epsilon");
						State target=st.getDest();
						epset.add(target);
						remove.add(st);
						flag=true;
					}
				
				}
				for(State t:epset)
					addepsilon(s,t);
				tset.removeAll(remove);
			
			}
		}
		return a;
	}
	public static  Automaton replace(Automaton na0, Automaton na1, Automaton na2)
	{
		Automaton a0=na0.intersection(makeFilter());
		Automaton a1=na1.intersection(makeFilter());
		Automaton a2=na2.intersection(makeFilter());
		Automaton t;
		//step1
		Automaton M1=extendtarget(a0);
		//step2
		Automaton M2=ConstructM2(a1);
		//step3
		Automaton M3tmp=M1.intersection(M2);
		Automaton M3=clean(M3tmp);

		Hashtable<State,Set<State>> table=calreach(M3);
		//step4


			Set<State> states = M3.getStates();
			

		boolean delete=a2.isEmpty()||a2.isEmptyString();
		for (State s : states) {

			Set<State> reachset=table.get(s);
			//System.out.println(s+" aa"+reachset);
			//tmp.setStateNumbers(tmp.getStates());

			for (State reach:reachset)
			{

				if(delete)
					addrealepsilon(s,reach);
				else
				{
					Automaton insert=a2.clone();
					State initinsert=insert.getInitialState();
					Set<State> Fsi=insert.getAcceptStates();
					addrealepsilon(s,initinsert);
					for(State fs:Fsi)
					{
						fs.setAccept(false);
						addrealepsilon(fs,reach);
					}
				}

			}

		}
		M3.minimize();
		M3.determinize();
		Automaton tmp = new Automaton();
		states = M3.getStates();
		HashMap<State, State> m = new HashMap<State, State>();
		for (State s : states)
			m.put(s, new State());
		for (State s : states) {
			State p=m.get(s);
			p.setAccept(s.isAccept());
			if (s == M3.getInitialState())
				tmp.setInitialState(p);
			for (Transition st : s.getTransitions())
			{
				if(st.getMax()!=special1&& st.getMax()!=special2)
					p.addTransition(new Transition(st.getMin(), st.getMax(),m.get(st.getDest())));		
			}
		}
		removeepsilon(tmp);
		tmp.minimize();
		tmp.determinize();
		return tmp;
	}
	private static boolean overlapping(Transition st1, Transition st2)
	{
		if(st1.getMax()<=st2.getMax()&&st1.getMax()>=st2.getMin())
			return true;
		if(st1.getMin()<=st2.getMax()&&st1.getMin()>=st2.getMin())
			return true;
		
		
		if(st1.getMax()>=st2.getMax()&&st1.getMin()<=st2.getMin())
			return true;

		return false;
	}
	//widen opreation in Bultan's paper, automaton will be minimized and determinized
	public static Automaton Widen(Automaton na1, Automaton na2)
	{
		if(na1.equals(na2))
		{
			return na1;
		}
		Automaton a1=na1.intersection(makeFilter());
		Automaton a2=na2.intersection(makeFilter());
		a1.minimize();
		a1.determinize();
		a2.minimize();
		a1.determinize();
		Automaton ca1=a1.clone();
		Automaton ca2=a2.clone();
		


		//compute the sets that includes all states that meet the first condition in
		//Bultan's equal condition in widen operation.
		Hashtable<State,Set<State>> equalset1=new Hashtable<State,Set<State>>();
		Set<State> states1=ca1.getStates();
		Set<State> states2=ca2.getStates();
		//System.out.println("Widenning");
		for(State s:states1)
		{
			Set<State> tmp=new HashSet<State>();
			tmp.add(s);
			equalset1.put(s, tmp);
		}
		for(State s:states2)
		{
			Set<State> tmp=new HashSet<State>();
			tmp.add(s);
			equalset1.put(s, tmp);
		}
		//System.out.println("Widen 405");

		for(State s1:states1)
			for(State s2:states2)
			{
				Set<State> set1=equalset1.get(s1);
				Set<State> set2=equalset1.get(s2);

				if(set1.contains(s2)||set2.contains(s1))
				{
					continue;
				}
				Automaton tmp11=new Automaton();
				Automaton tmp12=new Automaton();
				tmp11.setInitialState(s1);
				tmp12.setInitialState(s2);
				Automaton tmp1=tmp11.clone();
				Automaton tmp2=tmp12.clone();
				if(tmp1.equals(tmp2))
				{
					set1.addAll(set2);
					for(State s3:set1)
						equalset1.put(s3, set1);
				}
				
				
			}
		//System.out.println("Widen 432");

		Hashtable<State,Set<State>> equalset2=new Hashtable<State,Set<State>>();
		//compute the sets that includes all states that meet the second condition in
		//Bultan's equal condition in widen operation.
		for(State s:states1)
		{
			Set<State> tmp=new HashSet<State>();
			tmp.add(s);
			equalset2.put(s, tmp);
		}
		for(State s:states2)
		{
			Set<State> tmp=new HashSet<State>();
			tmp.add(s);
			equalset2.put(s, tmp);
		}
		State init1=ca1.getInitialState();
		State init2=ca2.getInitialState();
		Queue<Set<State>> worklist=new LinkedList<Set<State>>();
		//Set<VisitedSet> visited=new HashSet<VisitedSet>();
		Set<State> visited=new HashSet<State>();
		Set<State> set1=equalset2.get(init1);
		Set<State> set2=equalset2.get(init2);
		set1.addAll(set2);
		worklist.add(set1);
		int iteration=0;
		int maxstates=states1.size()+states2.size();
		while(!worklist.isEmpty())
		{
			iteration++;
			//System.out.println("here");
			//System.out.println("Widen 461 "+iteration+" "+visited.size()+" "+maxstates);
			//System.out.println("Widen 461 "+visited.size());


			Set<State> current=worklist.poll();
			int oldsize;
			for(State s:current)
				visited.add(s);
			//System.out.println("Widen 461 "+iteration+" "+visited.size());

			//visited.add(new VisitedSet(current));
			for(State s:current)
				equalset2.put(s, current);
			Hashtable<Transition,Set<State>> tmptable=new Hashtable<Transition,Set<State>>();
			for(State s:current)
				for (Transition st : s.getTransitions())
				{
					Set<State> tmp= equalset2.get(st.getDest());
					if(tmp==null)
						System.out.println("error null");
					tmptable.put(st, tmp);
				}
			Set<Transition> transet=tmptable.keySet();
			for (Transition st1 : transet)
				for (Transition st2 : transet)
				{
					if(overlapping(st1,st2) && st1!=st2)
					{
						Set<State> tmpst1=tmptable.get(st1);
						Set<State> tmpst2=tmptable.get(st2);
						tmpst1.addAll(tmpst2);
						tmptable.put(st1, tmpst1);
						tmptable.put(st2, tmpst1);
					}
				}
			for (Set<State> tmpst1 : tmptable.values())
			{
				if(!visited.containsAll(tmpst1))
				{
					for(State s:tmpst1)
						visited.add(s);
					worklist.add(tmpst1);
				}
				/*VisitedSet cup=new VisitedSet(tmpst1);
				if(!visited.contains(cup))
				{
					worklist.add(tmpst1);
				}*/
			}
			
					
		}
		//System.out.println("Widen 499");

		Hashtable<State,Set<State>> equalset=new Hashtable<State,Set<State>>();
		for(State s:states1)
		{
			Set<State> tmp=new HashSet<State>();
			tmp.add(s);
			equalset.put(s, tmp);
		}
		for(State s:states2)
		{
			Set<State> tmp=new HashSet<State>();
			tmp.add(s);
			equalset.put(s, tmp);
		}
		for(State s1:equalset.keySet())
		{
			Set<State> tmp1=equalset1.get(s1);
			Set<State> tmp2=equalset2.get(s1);
			Set<State> tmp=equalset.get(s1);

			for(State s2:equalset.keySet())
			{
				if(tmp1.contains(s2)||tmp2.contains(s2))
					tmp.add(s2);
			}
			equalset.put(s1, tmp);
		}
		
		Automaton r=new Automaton();
		for(State s1:equalset.keySet())
		{
			Set<State> tmp=equalset.get(s1);
			for(State s2:tmp)
			{
				addepsilon(s1,s2);
			}
				

		}
		r.setInitialState(ca1.getInitialState());
		int oldnum=0;
		int newnum=r.getNumberOfStates();
		while(newnum!=oldnum)
		{
			r.minimize();
			r.setDeterministic(false);
			r.determinize();
			oldnum=newnum;
			newnum=r.getNumberOfStates();
		}

		//r.minimize();

		
		return r;
		
	}
	public static void print() throws FileNotFoundException
	{
		//System.out.println(special1);
		/*RegExp exp=new RegExp("-?[0-9]+");
		RegExp exp1=new RegExp("-?[0-9]+\\.[0-9]+");
		RegExp exp2=new RegExp("-?[0-9]+\\.[0-9]+E-?[0-9]+");
		//Automaton a=Automaton.makeString("ab").union(Automaton.makeEmptyString());
		Automaton a=exp2.toAutomaton().union(exp.toAutomaton());
		 a=a.union(exp1.toAutomaton());
		Automaton a1=(Automaton.makeString("abab"));
		a1=a1.union(Automaton.makeEmptyString());
		a1=a1.union(Automaton.makeString("ab"));
		a1.determinize();
		Automaton a2=Widen(a,a1);*/
		PrintWriter pw=new PrintWriter("/home/dingli/graph1.gv");
		String seperator=""+ExtendedOperation.parameter_seperator;
		String input=seperator+"parameter0"+seperator;
		Automaton a1=Automaton.makeString("hellow"+input);
		Automaton a2=Automaton.makeString("hellow"+input+input);
		Automaton a4=ExtendedOperation.Widen(a1, a2);
		Automaton a5=ExtendedOperation.Widen(a2, a4);
		//a4.determinize();
		//a4.minimize();
		pw.println(a5.toDot());
		/*Hashtable<State,Set<State>> table=calreach(M3);
		
		for (State s : M3.getStates()) {

			Set<State> reachset=table.get(s);
			System.out.println(s+" aa"+reachset);
		}*/
		pw.close();
		
		
	}

}
