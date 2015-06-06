#! /bin/bash
if [ "$#" -ne 2 ] || ! [ -d "$1" ]; then
  echo "Usage: $0 Android appname"
  exit 1
fi
curpath=`pwd`

rm -r $curpath/$1/$2/MethodSummary
mkdir $curpath/$1/$2/MethodSummary
rm -r $curpath/$1/$2/Output
mkdir $curpath/$1/$2/Output
rm -r $curpath/$1/$2/StringOutput

if [ $2 = 'App1' ];
then
java -jar Violist_Android.jar $curpath/Android $curpath/Android/App1 /a2z.Mobile.DevConnections.txt /a2z.Mobile.DevConnections.instrumented.apk
fi
if [ $2 = 'App2' ];
then
java -jar Violist_Android.jar $curpath/Android $curpath/Android/App2 /add.me.fast.txt /add.me.fast.instrumented.apk
fi
if [ $2 = 'App3' ];
then
java -jar Violist_Android.jar $curpath/Android $curpath/Android/App3 /appinventor.ai_jpicer.PipefitterHandbook.txt /appinventor.ai_jpicer.PipefitterHandbook.instrumented.apk
fi
mv $curpath/$1/$2/Output $curpath/$1/$2/StringOutput



