/**
 * Define the related APIs in method analysis
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
public interface MethodAnalysisInterface {
	public String getCanonicalName(TacFunction f);
	//For BCEL
	public String getCanonicalName(Method m);
	public String getCanonicalName(InvokeInstruction in, ConstantPoolGen cpg);
	//For Soot
	public String getCanoicalName(SootMethod sm);
}
