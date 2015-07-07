package usc.sql.string;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import SootEvironment.AndroidApp;
import SootEvironment.JavaApp;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.internal.ImmediateBox;
import usc.sql.ir.ConstantString;
import usc.sql.ir.Expression;
import usc.sql.ir.ExternalPara;
import usc.sql.ir.InterRe;
import usc.sql.ir.InternalVar;
import usc.sql.ir.T;
import usc.sql.ir.Variable;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;
import edu.usc.sql.graphs.cdg.Dominator;
import edu.usc.sql.graphs.cdg.PostDominator;
import edu.usc.sql.graphs.cfg.BuildCFGs;
import edu.usc.sql.graphs.cfg.CFGInterface;

public class JavaAndroid {
	
	
	public JavaAndroid(String rtjar,String appfolder,String classlist,String apk)
	{		
		InterpretCheckerAndroid(rtjar,appfolder+apk,appfolder+classlist,
					appfolder+"/MethodSummary/",appfolder+"/Output/");
		
	}
	public JavaAndroid(String rtjar,String appfolder,String classlist)
	{
		InterpretCheckerJava(rtjar,appfolder,appfolder+classlist,
				appfolder+"/MethodSummary/",appfolder+"/Output/");
	}


	private void InterpretCheckerAndroid(String arg0,String arg1,String arg2,String summaryFolder,String wfolder)
	{
		//"/home/yingjun/Documents/StringAnalysis/MethodSummary/"
		//"Usage: rt.jar app_folder classlist.txt"
		AndroidApp App=new AndroidApp(arg0,arg1,arg2);
		Map<String,Map<String,Set<Variable>>> targetMap = new HashMap<>();
    	Map<String,Set<NodeInterface>> paraMap = new HashMap<>();
    	Map<String,Set<String>> fieldMap = new HashMap<>();
		Map<String,Translator> tMap = new HashMap<>();
		long totalTranslate = 0,totalInterpret = 0;
		
		
		long t1,t2;
    	for(CFGInterface cfg:App.getCallgraph().getRTOInterface())
    	{
    		
    		String signature=cfg.getSignature();
    		
    		//if(signature.contains("com.google.ads"))
    		//	continue;
    		//System.out.println(signature);
    		
    		if(signature.equals("<LoggerLib.Logger: void <clinit>()>")||signature.equals("<LoggerLib.Logger: void reportString(java.lang.String,java.lang.String)>"))
    		continue;
    		
    		//field																	def missing						

    		
 

    		
      		//for(int i=1;i<=loopCount;i++)
    		//{  		
    		
    		
    		t1 = System.currentTimeMillis();
    		
    		LayerRegion lll = new LayerRegion(null);
    		ReachingDefinition rd = new ReachingDefinition(cfg.getAllNodes(), cfg.getAllEdges(),lll.identifyBackEdges(cfg.getAllNodes(),cfg.getAllEdges(), cfg.getEntryNode()));	   		
    
    		
    		LayerRegion lr = new LayerRegion(cfg);
    	
    		//System.out.println(signature);
    		Translator t = new Translator(rd, lr,signature,summaryFolder);
    	
    		tMap.put(signature, t);
    		paraMap.putAll(t.getParaMap());
    		
    		for(Entry<String,Set<String>> en: t.getFieldMap().entrySet())
    		{
    			if(fieldMap.containsKey(en.getKey()))
    				fieldMap.get(en.getKey()).addAll(en.getValue());
    			else
    				fieldMap.put(en.getKey(), en.getValue());
    		}
    		
    		
    		 		
    		if(t.getTargetLines().isEmpty())
    			continue;
    		
    		//Set<String> value = new HashSet<>();
    	
    	
    		
    		//Interpreter intp = new Interpreter(t,loopCount);
    		
    		//label set<IR>
    		Map<String,Set<Variable>> labelIR = new HashMap<>();
    		
    		for(String labelwithnum:t.getTargetLines().keySet())
    		{
    			
    			Set<Variable> targetIR = new HashSet<>();
    			for(String line: t.getTargetLines().get(labelwithnum))
    				if(t.getTranslatedIR(line)!=null)
    					targetIR.addAll(t.getTranslatedIR(line));

    			labelIR.put(labelwithnum, targetIR);
    		}
    		

    		
    		
    		if(!targetMap.containsKey(signature))
    			targetMap.put(signature, labelIR);
    	 		

    		t2 = System.currentTimeMillis();
    		totalTranslate += t2-t1;
    	}
    	int count = 0;
    	for(Entry<String,Map<String,Set<Variable>>> enout: targetMap.entrySet())
    	{
    		String signature = enout.getKey();
    		int i1 = signature.indexOf("<"),i2 = signature.indexOf(":");
    		
    		
    		//System.out.println("\n"+signature);
    		

    		
    		for(Entry<String,Set<Variable>> en:enout.getValue().entrySet())
    		{
	    		t1 = System.currentTimeMillis();
	    		Set<Variable> newIR = replaceExternal(en.getValue(),signature,paraMap,tMap,App);
	    		t2 = System.currentTimeMillis();
	    		totalTranslate += t2-t1;
	    
	    		
	    		t1 = System.currentTimeMillis();
	    		
	    		
				Interpreter intp = new Interpreter(newIR,fieldMap,3);
				Set<String> value = new HashSet<>();
				value.addAll(intp.getValueForIR());
	    		

	    		//add const label
	    		
	    		if(tMap.get(signature).getLabelConstant().get(en.getKey())!=null)
	    		{
	    			value.add(tMap.get(signature).getLabelConstant().get(en.getKey()).replaceAll("\"", ""));
	    		}
	    		
    			t2 = System.currentTimeMillis();
    			
    			totalInterpret += t2-t1;
	    	//	System.out.println("Label: "+en.getKey());
	    	//	System.out.println("Output: "+value);
	    		
	    		if(!value.isEmpty())
	    		{System.out.println(signature+"\n"+value);
	    		/*try
	    		{
	    			
	    			
	    			
	    			BufferedWriter bw = new BufferedWriter(new FileWriter(wfolder+en.getKey().replaceAll("\"", "")+".txt",true));
	    			for(String s:value)
	    			{
	    				
	    				bw.write(s);
	    				bw.newLine();
	    			}
	    			
	    			bw.flush();
	    			bw.close();
	    		}
	    		catch(IOException e)
	    		{
	    			e.printStackTrace();
	    		}*/
	    		}

	    	}
    	}
    	System.out.println("Total Trans: "+ totalTranslate);
    	System.out.println("Total Interp: "+ totalInterpret);
	}
	private void InterpretCheckerJava(String arg0,String arg1,String arg2,String summaryFolder,String wfolder)
	{
		//"/home/yingjun/Documents/StringAnalysis/MethodSummary/"
		//"Usage: rt.jar app_folder classlist.txt"

		JavaApp App;
		if(arg1.contains("bookstore"))
		{

			App=new JavaApp(arg0,arg1,arg2,"void _jspService(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)");
		}
		else{
			App=new JavaApp(arg0,arg1,arg2,"void main(java.lang.String[])");
		}
		Map<String,Map<String,Set<Variable>>> targetMap = new HashMap<>();
    	Map<String,Set<NodeInterface>> paraMap = new HashMap<>();
    	Map<String,Set<String>> fieldMap = new HashMap<>();
		Map<String,Translator> tMap = new HashMap<>();
		long totalTranslate = 0,totalInterpret = 0;
		
		
		long t1,t2;


    	for(CFGInterface cfg:App.getCallgraph().getRTOInterface())
    	{
    		//System.out.println(cfg.getSignature());
    		String signature=cfg.getSignature();
    		
    	//	if(!signature.contains("SubstringOfNull"))
    	//		continue;
    		
    		
    		if(signature.equals("<LoggerLib.Logger: void <clinit>()>")||signature.equals("<LoggerLib.Logger: void reportString(java.lang.String,java.lang.String)>"))
    		continue;
    		
    		//field																	def missing						

    		

    		
    		
      		//for(int i=1;i<=loopCount;i++)
    		//{  		
    		
    		

    		t1 = 	System.currentTimeMillis();
    		LayerRegion lll = new LayerRegion(null);
    		ReachingDefinition rd = new ReachingDefinition(cfg.getAllNodes(), cfg.getAllEdges(),lll.identifyBackEdges(cfg.getAllNodes(),cfg.getAllEdges(), cfg.getEntryNode()));	   		
    
    		
    		LayerRegion lr = new LayerRegion(cfg);
    	
    		//System.out.println(signature);
    		Translator t = new Translator(rd, lr,signature,summaryFolder);
    	
    		tMap.put(signature, t);
    		paraMap.putAll(t.getParaMap());
    		
    		for(Entry<String,Set<String>> en: t.getFieldMap().entrySet())
    		{
    			if(fieldMap.containsKey(en.getKey()))
    				fieldMap.get(en.getKey()).addAll(en.getValue());
    			else
    				fieldMap.put(en.getKey(), en.getValue());
    		}
    		//fieldMap.putAll(t.getFieldMap());
    		 		
    		if(t.getTargetLines().isEmpty())
    			continue;
    		
    		//Set<String> value = new HashSet<>();
    	
    		
    		//label set<IR>
    		Map<String,Set<Variable>> labelIR = new HashMap<>();
    		
    		for(String labelwithnum:t.getTargetLines().keySet())
    		{
    			Set<Variable> targetIR = new HashSet<>();
    			for(String line: t.getTargetLines().get(labelwithnum))
    				if(t.getTranslatedIR(line)!=null)
    					targetIR.addAll(t.getTranslatedIR(line));
    			
    	
    			//if(targetIR.isEmpty())
    			//	targetIR.add(new ExternalPara("Unknown"));
    			labelIR.put(labelwithnum, targetIR);
    		}
    		

    	
    		
    		if(!targetMap.containsKey(signature))
    			targetMap.put(signature, labelIR);
    	 		
    		t2 = 	System.currentTimeMillis();
    		totalTranslate+=t2-t1;
    	
    	}
    	
	
    	
		
    	
   
		
		
    	for(Entry<String,Map<String,Set<Variable>>> enout: targetMap.entrySet())
    	{
    		String signature = enout.getKey();
    		
    		//System.out.println("\n"+signature);
    		

    		
    		for(Entry<String,Set<Variable>> en:enout.getValue().entrySet())
    		{
    		
    			
    				
	    		t1 = System.currentTimeMillis();
	    		Set<Variable> newIR = replaceExternal(en.getValue(),signature,paraMap,tMap,App);
	    		t2 = System.currentTimeMillis();
	    		totalTranslate += t2-t1;
	    		
	    		
	    		int loopCount = 3;	    			    		
	    		String tempSig = signature.replaceAll("TestCases.", "");
	    		int dot = tempSig.indexOf(".");
	    		String tt = tempSig.substring(dot+1);
	    		
	    		if(tt.contains("Mix")||tt.contains("NestedLoop"))
	    			loopCount = 2;
	    		else
	    			loopCount = 3;
	    		
	    		Set<String> value = new HashSet<>();
	    		
	    		t1 = System.currentTimeMillis();
    			Interpreter intp = new Interpreter(newIR,fieldMap,loopCount);
    			
    			value.addAll(intp.getValueForIR());

	    		
	    		//add const label
	    		
	    		if(tMap.get(signature).getLabelConstant().get(en.getKey())!=null)
	    		{
	    		//	value.add(tMap.get(signature).getLabelConstant().get(en.getKey()).replaceAll("\"", ""));
	    		}
	    		
    			t2 = System.currentTimeMillis();
    			
    			totalInterpret += t2-t1;
	    		
	    		//System.out.println("Label: "+en.getKey());
	    		//System.out.println("Output: "+value);
	    		
	    		if(!emptyOrContainUnknown(value))
	    		{
	    			//System.out.println(en.getKey()+":"+value+";"+value.isEmpty()+value.iterator().next().equals(""));
	    			
		    		try
		    		{
		    			BufferedWriter bw = new BufferedWriter(new FileWriter(wfolder+en.getKey().replaceAll("\"", "")+".txt",true));
		    			//BufferedWriter bw = new BufferedWriter(new FileWriter(wfolder+"output.txt",true));
		    			//bw.write(en.getKey().replaceAll("\"", ""));
		    			//bw.newLine();
		    			for(String s:value)
		    			{
		    				
		    				bw.write(s);
		    				bw.newLine();
		    			}
		    			
		    			bw.flush();
		    			bw.close();
		    		}
		    		catch(IOException e)
		    		{
		    			e.printStackTrace();
		    		}
	    		}

	    
	    	}
    		
    	    

    	}
    	
    	System.out.println("Total Trans: "+ totalTranslate);
    	System.out.println("Total Interp: "+ totalInterpret);
	}
	
	boolean notEmptyAndContainNotUnknown(Set<String> value)
	{
		if(value.isEmpty())
			return false;
		else
		{
			for(String s:value)
				if(!s.contains("(.*)"))
						return true;
			
			return false;
		}
	}
	boolean emptyOrContainUnknown(Set<String> value)
	{
		if(value.isEmpty())
			return true;
		else
		{
			for(String s:value)
				if(s.contains("(.*)"))
					return true;
			return false;
		}
	}
	private Variable copyVar(Variable v)
	{
		
		if(v instanceof InternalVar)
			return new InternalVar(((InternalVar) v).getName(),((InternalVar) v).getK(),((InternalVar) v).getSigma(),((InternalVar) v).getRegionNum(),((InternalVar) v).getLine());
		else if(v instanceof Expression)
		{
			List<List<Variable>> newOperandList = new ArrayList<>();
			for(List<Variable> operandList:((Expression) v).getOperands())
			{
			List<Variable> tempOp = new ArrayList<>();
			for(Variable operand:operandList)
			{
				if(operand instanceof InternalVar)
					tempOp.add(new InternalVar(((InternalVar) operand).getName(),((InternalVar) operand).getK(),((InternalVar) operand).getSigma(),((InternalVar) operand).getRegionNum(),((InternalVar) operand).getLine()));
				else if(operand instanceof Expression)
					tempOp.add(copyVar(operand));
				else if(operand instanceof T)
					tempOp.add(new T(copyVar(((T) operand).getVariable()),((T) operand).getTVarName(),((T) operand).getRegionNumber(),((T) operand).getK(),((T) operand).isFi(),((T) operand).getLine()));
				else
					tempOp.add(operand);
			}
			newOperandList.add(tempOp);
			}
			return new Expression(newOperandList,((Expression) v).getOperation());
		}
		else if(v instanceof T)
		{
			return new T(copyVar(((T) v).getVariable()),((T) v).getTVarName(),((T) v).getRegionNumber(),((T) v).getK(),((T) v).isFi(),((T) v).getLine());
		}

		else
			return v;
	}
	private Set<Variable> replaceExternal(Set<Variable> IRs,String signature,Map<String,Set<NodeInterface>> paraMap,Map<String,Translator> tMap,JavaApp App)
	{
		
		boolean existPara = false;
		for(Variable v:IRs)
		{
			if(containPara(v))
				existPara = true;
		}
		if(!existPara)
			return IRs;
		else
		{
			Set<Variable> vSet = new HashSet<>();
			for(Variable v: IRs)
			{
				if(paraMap.get(signature)==null)
					vSet.add(v);
				else
				{
					if(App.getCallgraph().getParents(signature).isEmpty())
						vSet.add(copyVar(v));
					else
					{
						for(String parentSig:App.getCallgraph().getParents(signature))
						{
							Set<Variable> newIR = new HashSet<>();
							for(NodeInterface n:tMap.get(parentSig).getParaMap().get(signature))
								newIR.addAll(replaceExternal(copyVar(v),n,tMap.get(parentSig)));
							
							Set<Variable> copy = new HashSet<>();
							for(Variable vv:newIR)
								copy.add(copyVar(vv));
							vSet.addAll(replaceExternal(copy,parentSig, paraMap, tMap, App));
						}					
					}
					
				}			
			}
			return vSet;
		}

/*		Set<Variable> vSet = new HashSet<>();
		for(Variable v: IRs)
		{
			if(paraMap.get(signature)==null)
				vSet.add(v);
			else
			{
				for(NodeInterface n:paraMap.get(signature))
				{
	    			Set<Variable> newIR = new HashSet<>();
	    				if(App.getCallgraph().getParents(signature).isEmpty())
	    					newIR.add(copyVar(v));
	    				else
	    				{
	    				String parentSig = App.getCallgraph().getParents(signature).iterator().next();
	    				newIR.addAll(replaceExternal(copyVar(v),n,tMap.get(parentSig)));
	    				}
	    			vSet.addAll(newIR);
	    		}				
			}			
		}
		boolean existPara = false;
		for(Variable v:vSet)
		{
			if(containPara(v))
				existPara = true;
		}
		if(!existPara)
			return vSet;
		else
		{
			if(App.getCallgraph().getParents(signature).isEmpty())
				return vSet;
			else
			{
				String parentSig = App.getCallgraph().getParents(signature).iterator().next();
				if(paraMap.get(parentSig)==null)
					return vSet;
				else
				{
					Set<Variable> copy = new HashSet<>();
					for(Variable vv:vSet)
						copy.add(copyVar(vv));
					Set<Variable> newIR = new HashSet<>();
					newIR.addAll(replaceExternal(copy,parentSig, paraMap, tMap, App));
					return newIR;
				}
			}
		}*/
		
	}
	
	private Set<Variable> replaceExternal(Set<Variable> IRs,String signature,Map<String,Set<NodeInterface>> paraMap,Map<String,Translator> tMap,AndroidApp App)
	{

		boolean existPara = false;
		for(Variable v:IRs)
		{
			if(containPara(v))
				existPara = true;
		}
		if(!existPara)
			return IRs;
		else
		{
			Set<Variable> vSet = new HashSet<>();
			for(Variable v: IRs)
			{
				if(paraMap.get(signature)==null)
					vSet.add(v);
				else
				{
					if(App.getCallgraph().getParents(signature).isEmpty())
						vSet.add(copyVar(v));
					else
					{
						for(String parentSig:App.getCallgraph().getParents(signature))
						{
							Set<Variable> newIR = new HashSet<>();
							for(NodeInterface n:tMap.get(parentSig).getParaMap().get(signature))
								newIR.addAll(replaceExternal(copyVar(v),n,tMap.get(parentSig)));
							
							Set<Variable> copy = new HashSet<>();
							for(Variable vv:newIR)
								copy.add(copyVar(vv));
							vSet.addAll(replaceExternal(copy,parentSig, paraMap, tMap, App));

						}					
					}
					
				}			
			}
			return vSet;
		}

	}
	
	boolean containPara(Variable v)
	{

			if(v instanceof ExternalPara)
			{
				if(((ExternalPara) v).getName().contains("@parameter"))
					return true;
				else
					return false;
			}
			else if(v instanceof Expression)
			{
				for(List<Variable> operandList:((Expression) v).getOperands())
				{
					for(Variable operand: operandList)
					if(containPara(operand))
						return true;
				}
				return false;
			}
			else if(v instanceof T)
			{
			
				return containPara(((T) v).getVariable());
			}
			else
			return false;
	}
	
	
	private Set<Variable> replaceExternal(Variable v,NodeInterface n,Translator t)
	{
		Set<Variable> returnSet = new HashSet<>();
		if(v instanceof ExternalPara)
		{
			if(((ExternalPara) v).getName().contains("@parameter"))
			{
				String tmp = ((ExternalPara) v).getName().split(":")[0].replaceAll("@parameter", "");
				int index = Integer.parseInt(tmp);
				
			//	System.out.println(index +" "+valueBox);
			
				List<ValueBox> valueBox = ((Unit)((Node)n).getActualNode()).getUseBoxes();

				if(!(valueBox.get(0) instanceof ImmediateBox))
					index = index+1;
				
			//	System.out.println(index+"->index-> "+valueBox);
				
				if(index >= valueBox.size())
					returnSet.add(v);
				else 
				{
					String para = valueBox.get(index).getValue().toString();
			
				
				
					
					if(para.contains("\""))
						returnSet.add( new ConstantString(para));
					else if(valueBox.get(index).getValue().getType().toString().equals("int"))
					{
					
						if(!para.contains("i")&&!para.contains("b"))
						returnSet.add( new ConstantString(""+(char)Integer.parseInt(para)));
					}
	
					else
					{
						
						Set<Variable> newIR = new HashSet<>();
	
						//System.out.println(valueBox.toString()+para+t.getRD().getAllDef());
						for(String line:t.getRD().getLineNumForUse(n, para))
						{
							if(t.getTranslatedIR(line)!=null)
							newIR.addAll(t.getTranslatedIR(line));
						}
						
						
						
						returnSet.addAll(newIR);
					}
					
				}
			}
			else
				returnSet.add(v);
			
		}

		else if(v instanceof Expression)
		{
			List<List<Variable>> newOperandList = new ArrayList<>();
			for(List<Variable> operandList:((Expression) v).getOperands())
			{
				List<Variable> tempOperand = new ArrayList<>();
				for(Variable operand:operandList)
				{
					tempOperand.addAll( replaceExternal(operand,n,t));
				}
				newOperandList.add(tempOperand);
			}
 			((Expression) v).setOperands(newOperandList);
 			returnSet.add(v);
		}
		else if(v instanceof T)
		{
			((T) v).setVariable(replaceExternal(((T) v).getVariable(),n,t).iterator().next());
			returnSet.add(v);
		}
		else
			returnSet.add(v);
		
		return returnSet;
	}


}
