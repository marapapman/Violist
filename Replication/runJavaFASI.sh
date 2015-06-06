#! /bin/bash
if [ "$#" -ne 2 ] || ! [ -d "$1" ]; then
  echo "Usage: $0 Java appname"
  exit 1
fi

curpath=`pwd`
rm -r $curpath/$1/$2/MethodSummary
mkdir $curpath/$1/$2/MethodSummary
rm -r $curpath/$1/$2/AutoOutput
rm -r  $curpath/$1/$2/Output
mkdir $curpath/$1/$2/Output
rm -r $curpath/$1/$2/Automatons
mkdir $curpath/$1/$2/Automatons

java -jar Violist_Java_Widen.jar $curpath/rt.jar $curpath/$1/$2 /classlist.txt

mv $curpath/$1/$2/Output $curpath/$1/$2/AutoOutput
java -jar AutomatonGenerator.jar $curpath/$1/$2/AutoOutput $curpath/$1/$2/Automatons



