#!/usr/bin/python
import sys
if len(sys.argv) != 2:
	print "Usage: wrapforIf.py templatefile"
	sys.exit(1);
templatname=sys.argv[1]
print templatname

targetname=templatname.replace(".temp","")
casename=targetname+"Simple"
outfile = open(casename+".java", 'w')
template = open(templatname, 'r')

content=template.read()
content=content.replace("CaseID",casename)
outfile.write(content)
template.close();
outfile.close();
