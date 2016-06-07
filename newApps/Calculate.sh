#! /bin/bash
curpath=`pwd`
echo App79;
java -jar AndroidCalculator.jar $curpath/App79/StringOutput $curpath/App79/Automatons  $curpath/App79/groundtruth;
echo App183;
java -jar AndroidCalculator.jar $curpath/App183/StringOutput $curpath/App183/Automatons  $curpath/App183/groundtruth;
echo App328;
java -jar AndroidCalculator.jar $curpath/App328/StringOutput $curpath/App328/Automatons  $curpath/App328/groundtruth;
echo App489;
java -jar AndroidCalculator.jar $curpath/App489/StringOutput $curpath/App489/Automatons  $curpath/App489/groundtruth;




