#! /bin/bash
for f in GoodApps/*
do
cp $f ./;
fname=`basename $f`;
timeout 1h ./instrumentor.sh $fname;
rm $fname;
done
