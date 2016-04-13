#!/usr/bin/python
import sys
if sys.argv.length != 1:
	print "Usage: wrapforIf.py templatefile"
	sys.exit(1);
templatname=sys.argv[1]
targetname=templatname.repalce(".temp",".java")
target = open(targetname, 'w')
template = open(templatname, 'r')

print template.read()
template.close();
targetname.close();
