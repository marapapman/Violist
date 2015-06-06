#! /bin/bash
if [ "$#" -ne 2 ] || ! [ -d "$1" ]; then
  echo "Usage: $0 Java appname"
  exit 1
fi
curpath=`pwd`

rm -r $curpath/$1/$2/MethodSummary
mkdir $curpath/$1/$2/MethodSummary
rm -r $curpath/$1/$2/Output
mkdir $curpath/$1/$2/Output
rm -r $curpath/$1/$2/StringOutput

if [ $1 = 'Java' ];
then
java -jar Violist_Java.jar $curpath/rt.jar $curpath/$1/$2 /classlist.txt;
else
echo "Wait"
fi
mv $curpath/$1/$2/Output $curpath/$1/$2/StringOutput



