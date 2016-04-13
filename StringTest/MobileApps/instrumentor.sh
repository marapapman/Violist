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
rm -r oriclasses
rm -r insclasses
rm -r resource
java -jar soot.jar -android-jars $currentpath/libs -src-prec apk -f J -process-dir ${filename}.apk -allow-phantom-refs
mv sootOutput oriclasses
java -jar LoggerInstrumenter.jar -cp $currentpath/libs/rt.jar:$currentpath/libs/android--1/android.jar:$currentpath/javalib -process-dir $currentpath/oriclasses -f J -allow-phantom-refs
mv sootOutput insclasses
echo "Code Instrumented"
#java -jar soot.jar -cp /home/dingli/AppChecker/libs/rt.jar:/home/dingli/AppChecker/libs/android--1/android.jar:./javalib/  -src-prec J -f J -process-dir ./javalib -allow-phantom-refs
#mv sootOutput/* insclasses
#rm -r sootOutput
cp jimplelib/* insclasses
echo "Library Procesing Instrumented"
java -jar soot.jar -cp $currentpath/libs/rt.jar:$currentpath/libs/android--1/android.jar -src-prec J -f dex -process-dir insclasses -allow-phantom-refs
echo "Dex File Generated"

#for new version of apktools
#for old version:
#apktool d -s -r ${filename}.apk resource
#cp sootOutput/classes.dex resource/classes.dex 
#apktool b resource temp.apk
apktool d -s -r ${filename}.apk 
cp sootOutput/classes.dex ${filename}/classes.dex 
apktool b ${filename} temp.apk
cp ${filename}/dist/${filename}.apk ./temp.apk
#############################################
jarsigner -sigalg SHA1withRSA -digestalg SHA1 -verbose -keystore ./wendy.keystore -storepass USCDING -signedjar ${filename}.instrumented.apk temp.apk wendy.keystore;
mv ${filename}.instrumented.apk InstrumentedApps/${filename}.instrumented.apk
mv InstrumentLog.txt InstrumentLogs/${filename}
rm  temp.apk
rm -r sootOutput
rm -r insclasses
rm -r ${filename}
