#! /bin/bash
for file in TestCases/*
do
folder=`basename $file`;
echo $folder;
#java -jar -Xms2048m -Xmx4096m  JSATester.jar ./libs/rt.jar ./libs/android--1/android.jar $file ./result/$folder;
#cp /home/dingli/workspace/JSAtester/target/classes/LoggerLib/Logger.class $file/LoggerLib/Logger.class
#java -jar -Xms2048m -Xmx4096m  Automatontest.jar  ./result/$folder/$folder.automaton ./result/$folder/$folder.gt ./result/$folder/$folder.pr;
#cd ./result/$folder;
#pwd;
#dot -Tpng $folder.dot -o $folder.png;
#cd ../..;
#cd $file

#cd ../..;
done
#mkdir /home/dingli/JSAChecker/result/$1
#java -jar -Xms2048m -Xmx4096m  JSATester.jar ./libs/rt.jar ./libs/android--1/android.jar ./TestCases/$1 ./result/$1
cd TestCases/BranchLoop
for i in {1..256}
do
java BranchLoop >> ../../result/BranchLoop/BranchLoop.gt;
done
