package usc.sql.ir;

import java.util.HashSet;
import java.util.Set;

public class InternalVar extends Variable {

	String name;
	int k;
	boolean sigma = false;
	int regionNum = -1;
	Set<String> value = new HashSet<>();
	String line;
	
	public InternalVar(String name)
	{
		this.name = name;
	}
	public InternalVar(String name,String line)
	{
		this.name = name;
		this.line = line;
	}
	public InternalVar(String name,int k,boolean sigma,int regionNum,String line)
	{
		this.name = name;
		this.k = k;
		this.sigma = sigma;
		this.regionNum = regionNum;
		this.line = line;
	}	
	public String getLine()
	{
		return line;
	}
	public int getRegionNum() {
		return regionNum;
	}
	public void setRegionNum(int regionNum) {
		this.regionNum = regionNum;
	}
	public String getName()
	{
		return name;
	}
	public void setK(int k)
	{
		this.k = k;
	}
	public int getK()
	{
		return k;
	}
	public void setSigma(boolean sigma)
	{
		this.sigma = sigma;
	}
	public boolean getSigma()
	{
		return sigma;
	}
	
	public void setInitValue(Set<String> value)
	{
		this.value.clear();
		this.value.addAll(value);
	}
	
	public Set<String> getInitValue()
	{
		return value;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString()
	{
		if(sigma)
			return "\""+"σ "+name+" "+ regionNum +" k="+k+" "+line+"\"";
		else
			return "\""+name+" k="+k+" "+line+"\"";
	}
	/*
	@Override
	public String toString()
	{
			return "\""+name+"\"";
	}*/
}
