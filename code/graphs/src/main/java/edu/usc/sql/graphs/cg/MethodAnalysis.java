/**
 * 
 */
package edu.usc.sql.graphs.cg;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InvokeInstruction;

import soot.SootMethod;
import at.ac.tuwien.infosys.www.pixy.conversion.TacFunction;

/**
 * @author wan
 *
 */
public class MethodAnalysis implements MethodAnalysisInterface {

	public String getCanonicalName(TacFunction f) {
		// TODO Auto-generated method stub
		return f.getName();
	}

	@Override
	public String getCanonicalName(Method m) {
		// TODO Auto-generated method stub
		return m.getName();
	}

	@Override
	public String getCanonicalName(InvokeInstruction in, ConstantPoolGen cpg) {
		// TODO Auto-generated method stub
		return in.getMethodName(cpg);
	}

	@Override
	public String getCanoicalName(SootMethod sm) {
		// TODO Auto-generated method stub
		return sm.toString();
	}

}
