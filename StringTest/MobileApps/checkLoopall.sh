#! /bin/bash
for f in GoodApps/*
do
cp $f ./;
fname=`basename $f`;
./CheckLoop.sh $fname;
rm $fname;
done
