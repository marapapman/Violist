The mobile app needs to be processed before analysis. At this moment, the Violist cannot specify hotsopts. It only analysis the second parameter of Logger.reportString(String, String). The code of this function can be seen in our source code. All the tools in this folder is used in our project, we instrument the mobile app and add the Logger.reportString(String, String) for every non-constant string variables. 


Use the command "./instrumentor.sh the_apk_you_want.apk", it will generate the instrumented apk in the InstrumentedApk folder (There are some there already). 


Logger.reportString(String, String) is the hotsopts we instrumented. the first parameter should be an unique ID for the variable, the second parameter is the varaible under analysis. Make sure you have it in your app

