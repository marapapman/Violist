================File Description===================================================
There are two folder of apps:
Java folder includes all our Java apps in our paper. ToyAppTesting is for RQ 1, JSATesting is for RQ 2, bookstore and reflection are two market apps for RQ 3.
Android folder includes three Andorid market apps for RQ 3
There are seven .jar files:
Violist_*.jar are the main executable of Violist
AutomatonGenerator.jar is used in the second phase of FSAI in Violist
Calculator.jar is the executable to culculate numbers in the paper
rt.jar is the library we used
===================================================================================

================Usage Description===================================================
runJavaSSI.sh/runJavaFASI.sh Java ToyAppTesting/JSATesting/bookstore/reflection
runAndroidSSI.sh/runAndroidFASI.sh Android App1/App2/App3

For example, to run the bookstore with SSI, run ./runJavaSSI.sh Java bookstore

The result of runJavaSSI.sh and runAndroidSSI.sh will be in the StringOutput folder under the app.
For example  ./runJavaSSI.sh Java bookstore will generate the result in Java/bookstore/StringOutput

The result of runJavaFASI.sh and runAndroidFASI.sh will be in the folder of Automatons under each app.
For example  ./runJavaSSI.sh Java bookstore will generate the result in Java/bookstore/Automatons. Each result in the
folder there is a .dot file, you can plot the automaton as graph with dotty.
=====================================================================================

================Result Generation===================================================
After you have analyzed an app, you can run the command:
Calculate.sh Android App1/App2/App3
or Calculate.sh Java ToyAppTesting/JSATesting/bookstore/reflection
For example, if you run Calculate.sh Java ToyAppTesting
You will see:
Ave SSI: 0.7735550954399406 1.0 91.0
Ave FASI: 0.8254840969126113 1.0 91.0
Ave JSA: 0.11438426385633757 1.0 91.0
Concat Average SSI: 0.25545343364734996 1.0
Concat Average FASI: 0.2307696307692308 1.0
Concat Average JSA: 0.07969810722563529 1.0
Manip Average SSI: 0.7310897435897437 1.0
Manip Average FASI: 0.8791208791208791 1.0
Manip Average JSA: 4.0769230769230763E-7 1.0
Converge Average SSI: 0.9615384615384616 1.0
Converge Average FASI: 0.9615384615384616 1.0
Converge Average JSA: 0.19474612664655605 1.0
Mix Average SSI: 0.8049450549450549 1.0
Mix Average FASI: 0.8919413919413919 1.0
Mix Average JSA: 4.0769230769230753E-7 1.0


This result is slightly different than the result in our paper due to some improvement in our code




