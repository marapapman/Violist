#!/usr/bin/python
import sys
if len(sys.argv) != 2:
	print "Usage: wrapforIf.py templatefile"
	sys.exit(1);
templatname=sys.argv[1]
print templatname
targetname=templatname.replace(".temp","")
casename=targetname+"NestedLoop"
outfile = open(casename+".java", 'w')
template = open(templatname, 'r')

content=template.read()
content=content.replace("CaseID",casename)
content=content.replace("//begin","for(i=0;i<3;i++){\n")
content=content.replace("//middle","for(j=0;j<3;j++){\n")
content=content.replace("//quarter","}\n")
content=content.replace("//end","}\n")
outfile.write(content)
template.close();
outfile.close();
