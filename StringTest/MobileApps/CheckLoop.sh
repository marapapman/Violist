#! /bin/bash
if [ $# -ne 1 ]
then
 echo "usage: instrumenter app"
 exit;
fi
currentpath=`pwd`
echo $currentpath
filename=${1%.apk}
rm -r sootOutput
java -jar soot.jar -android-jars $currentpath/libs -src-prec apk -f c -process-dir ${filename}.apk -allow-phantom-refs
mv sootOutput 
java -jar LoopChecker.jar $currentpath/libs/android--1/android.jar $currentpath/sootOutput ./LoopResult/$filename.txt
rm -r sootOutput
