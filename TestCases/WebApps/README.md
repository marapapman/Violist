# Test cases
The web app needs to be processed before analysis. At this moment, the Violist cannot specify hotsopts. It only analysis the second parameter of Logger.reportString(String, String). The code of this function can be seen in our source code. All the tools in this folder is used in our project, we instrument the mobile app and add the Logger.reportString(String, String) for every non-constant string variables.

See the script "./instrument.sh", please adjust the path to the jar files accordingly. These jar files are included in weblib folder. The instrumented classes will be generated in sootOutput folder. Please add them into the war file and replace the old classes. Remember to add the Loggerlib.Logger class from our source code as well

Logger.reportString(String, String) is the hotsopts we instrumented. the first parameter should be an unique ID for the variable, the second parameter is the varaible under analysis. Make sure you have it in your app
