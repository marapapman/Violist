#! /bin/bash
if [ $# -ne 1 ]
then
 echo "usage: instrumenter app"
 exit;
fi
rm -r classes
filename=${1%.apk}
#java -jar soot.jar -android-jars /home/dingli/JSAChecker/libs -src-prec apk -f J -process-dir ${filename}.apk -allow-phantom-refs

mv sootOutput classes
java -jar -Xms2048m -Xmx4096m  JSATester.jar /home/dingli/JSAChecker/libs/rt.jar /home/dingli/JSAChecker/libs/android--1/android.jar /home/dingli/JSAChecker/classes /home/dingli/JSAChecker/result/${filename}
rm -r classes


