This folder contains the scripts to process and collect the groundtruth of mobile or Java apps during execution. These code are in nodeJS, it will be very clear to you how to use if you read the code.
The ResultParser folder is to generate the groundtruth database. You run the instrumented app (refer to TestCases/MobileApps) and dump the logcat output. Then run the scripts in ResultParser.
The grounttruth folder is to check and compare data of the analysis and query the database, check the code
