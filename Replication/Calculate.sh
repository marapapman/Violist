#! /bin/bash
if [ "$#" -ne 2 ] || ! [ -d "$1" ]; then
  echo "Usage: $0 Android/Java appname"
  exit 1
fi
curpath=`pwd`
if [ $1 = "Java" ]
then
java -jar Calculator.jar $curpath/$1/$2/StringOutput $curpath/$1/$2/Automatons $curpath/$1/$2/JSAResult $curpath/$1/$2/groundtruth
else
java -jar AndroidCalculator.jar $curpath/$1/$2/StringOutput $curpath/$1/$2/Automatons  $curpath/$1/$2/groundtruth
fi



